package com.shopThuCung.duanshopthucung.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "shopThuCung.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductDAO.SQL_PRODUCT);
        db.execSQL(BillDAO.SQL_Bill);
        db.execSQL(NhanVienDAO.SQL_TABLE_NhanVien);
        db.execSQL(CustomerDAO.SQL_TABLE_Customer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductDAO.PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BillDAO.BILL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NhanVienDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CustomerDAO.TABLE_NAME);
    }
}
