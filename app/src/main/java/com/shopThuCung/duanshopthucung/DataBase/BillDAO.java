package com.shopThuCung.duanshopthucung.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BillDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String BILL_TABLE_NAME = "bill";
    public static final String SQL_Bill = "CREATE TABLE BILL (" +
            "ID text PRIMARY KEY," +
            "tenSP text ,"+
            "thanhTien text ,"+
            "tenKhachHang text ,"+
            ");";
    public BillDAO (final Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }
}
