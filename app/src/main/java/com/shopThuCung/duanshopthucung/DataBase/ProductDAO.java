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
           "IDPet TEXT PRIMARY KEY , " +
            "productImage BLOB , " +
            "namePet TEXT , " +
            "agePet TEXT , " +
            "weightPet TEXT , " +
            "genderPet TEXT , " +
            "healthPet TEXT , " +
            "price TEXT " +
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
        if (curcor.moveToFirst()) {
            while (!curcor.isAfterLast()) {
                Product product = new Product();
                product.setCode(curcor.getString(curcor.getColumnIndex("IDPet")));
                product.setProductImage(curcor.getBlob(curcor.getColumnIndex("productImage")));
                product.setName(curcor.getString(curcor.getColumnIndex("namePet")));
                product.setAge(curcor.getInt(curcor.getColumnIndex("agePet")));
                product.setWeight(curcor.getDouble(curcor.getColumnIndex("weightPet")));
                product.setGender(curcor.getString(curcor.getColumnIndex("genderPet")));
                product.setHealth(curcor.getString(curcor.getColumnIndex("healthPet")));
                product.setPrice(curcor.getDouble(curcor.getColumnIndex("price")));
listProduct.add(product);
                curcor.moveToNext();
            }
            curcor.close();
        }
        return  listProduct;
    }

    public int insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("IDPet", product.getCode());
        values.put("productImage", product.getProductImage());
        values.put("namePet", product.getName());
        values.put("agePet", product.getAge());
        values.put("weightPet", product.getWeight());
        values.put("genderPet", product.getGender());
        values.put("healthPet", product.getHealth());
        values.put("price",product.getPrice());

        try {
            if (db.insert(PRODUCT_TABLE, null, values) < 0) {
                Log.e("aaa","a123");

                return -1;
            }
        } catch (Exception e) {

        }
        Log.e("bbb","a234");
        return 1;
   
    }

    public int updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("IDPet", product.getCode());
        values.put("productImage", product.getProductImage());
        values.put("namePet", product.getName());
        values.put("agePet", product.getAge());
        values.put("weightPet", product.getWeight());
        values.put("genderPet", product.getGender());
        values.put("healthPet", product.getHealth());
        values.put("price",product.getPrice());
        int kq = db.update(PRODUCT_TABLE, values, "IDPet=?", new String[]{product.getCode()});
        if (kq == 0) {
            return -1;
        }
        return 1;
    }
    public int delProduct(String idProduct){
        int kq = db.delete(PRODUCT_TABLE, "IDPet=?", new String[]{idProduct});
        if (kq==0){
            return -1;
        }
        return 1;
    }
}
