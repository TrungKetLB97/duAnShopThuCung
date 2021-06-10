package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.shopThuCung.duanshopthucung.DataBase.DataBaseHelper;
import com.shopThuCung.duanshopthucung.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public static final String PRODUCT_TABLE= "Product";
    public static final String SQL_PRODUCT = " CREATE TABLE Product(" +
           "CodePet TEXT PRIMARY KEY , " +
            "productImage BLOB , " +
            "NamePet TEXT , " +
            "AgePet TEXT , " +
            "WeightPet TEXT , " +
            "GenderPet TEXT , " +
            "HealthPet TEXT , " +
            "Price TEXT " +
            ")";

    public ProductDAO(final Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Product> getALlProduct(){
        List<Product> listProduct = new ArrayList<>();
        String SELECT = "SELECT * FROM Product";

        SQLiteDatabase sqLiteDatabase = this.helper.getReadableDatabase();
        Cursor curcor = sqLiteDatabase.rawQuery( SELECT , null);
        curcor.moveToFirst();
        if (curcor.getCount() > 0) {
            while (curcor.moveToNext()) {
                Product product = new Product();
                product.setCode(curcor.getString(curcor.getColumnIndex("CodePet")));
                product.setProductImage(curcor.getBlob(curcor.getColumnIndex("productImage")));
                product.setName(curcor.getString(curcor.getColumnIndex("NamePet")));
                product.setAge(curcor.getInt(curcor.getColumnIndex("AgePet")));
                product.setWeight(curcor.getDouble(curcor.getColumnIndex("WeightPet")));
                product.setGender(curcor.getString(curcor.getColumnIndex("GenderPet")));
                product.setHealth(curcor.getString(curcor.getColumnIndex("HealthPet")));
                product.setPrice(curcor.getDouble(curcor.getColumnIndex("Price")));
listProduct.add(product);
            }
            curcor.close();
        }
        return  listProduct;
    }

    public int insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("CodePet", product.getCode());
        values.put("productImage", product.getProductImage());
        values.put("NamePet", product.getName());
        values.put("AgePet", product.getAge());
        values.put("WeightPet", product.getWeight());
        values.put("GenderPet", product.getGender());
        values.put("HealthPet", product.getHealth());
        values.put("Price",product.getPrice());

        try {
            if (db.insert(PRODUCT_TABLE, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
   
    }

    public int updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("CodePet", product.getCode());
        values.put("productImage", product.getProductImage());
        values.put("NamePet", product.getName());
        values.put("AgePet", product.getAge());
        values.put("WeightPet", product.getWeight());
        values.put("GenderPet", product.getGender());
        values.put("HealthPet", product.getHealth());
        values.put("Price",product.getPrice());
        int kq = db.update(PRODUCT_TABLE, values, "CodePet=?", new String[]{product.getCode()});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }
    public int delProduct(String idProduct){
        int kq = db.delete(PRODUCT_TABLE, "CodePet=?", new String[]{idProduct});
        if (kq==0){
            return -1;
        }
        return 1;
    }
}
