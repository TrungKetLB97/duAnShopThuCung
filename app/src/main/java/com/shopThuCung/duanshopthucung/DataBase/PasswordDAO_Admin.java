package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shopThuCung.duanshopthucung.Password.Admin;

public class PasswordDAO_Admin {
    public static final String TABLE_NAME = "PasswordAdmin";
    public static final String SQL_TABLE_Password_Admin = "CREATE TABLE PasswordAdmin ( " +
            "idPassAd text primary key, " +
            "password text)";
    public DataBaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public PasswordDAO_Admin(Context context) {
        databaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        this.context = context;
    }

    public boolean checkUser(String idAd, String passAd){
        String[] cols = { "idPassAd" };
        String sel = "idPassAd =? and password =?";
        String[] selArg = {idAd, passAd};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,cols,sel,selArg,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        if(count >0){
            return true;
        }
        else{
            return false;
        }
    }

    public void createPassAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put("idPassAd", admin.getIdAdmin());
        values.put("password", admin.getPasswordAdmin());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
    }

    public int changePass(Admin admin){
        ContentValues values = new ContentValues();
        values.put("password", admin.getPasswordAdmin());
        if(sqLiteDatabase.update(TABLE_NAME,values,"idPassAd =?", new String[]{admin.getIdAdmin()})<0){
            return -1;
        }
        else {
            return 1;
        }
    }
}
