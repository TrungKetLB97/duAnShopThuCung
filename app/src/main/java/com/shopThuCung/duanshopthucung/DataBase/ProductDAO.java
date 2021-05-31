package com.shopThuCung.duanshopthucung.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shopThuCung.duanshopthucung.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public static final String PRODUCT_TABLE_NAME = "product";
    public static final String SQL_PRODUCT = " CREATE TABLE product (" +
            "productID TEXT PRIMARY KEY , " +
            "productImage BLOB ," +
            "productTen TEXT ," +
            "productLoai TEXT ," +
            "productTuoi TEXT ,"+
            "ProductTinhTrangSK TEXT ,"+
            "productGiaTien TEXT ," +
            ");";

    public ProductDAO(final Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Product> getALlProduct(){
        List<Product> listProduct = new ArrayList<>();
        Cursor curcor = db.query(PRODUCT_TABLE_NAME,null,null,null,null,null,null);
        curcor.moveToFirst();
        while (!curcor.isAfterLast()){
            Product product = new Product();
            product.setProductId(curcor.getString(curcor.getColumnIndex("productID")));
            product.setProductImage(curcor.getBlob(curcor.getColumnIndex("productImage")));
            product.setProductTen(curcor.getString(curcor.getColumnIndex("productTen")));
            product.setProductLoai(curcor.getString(curcor.getColumnIndex("productLoai")));
            product.setProductTuoi(curcor.getString(curcor.getColumnIndex("productTuoi")));
            product.setProductTinhTrangSK(curcor.getString(curcor.getColumnIndex("productTinhTrangSK")));
        }
        curcor.close();
        return  listProduct;
    }
}
