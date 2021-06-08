package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shopThuCung.duanshopthucung.Password.NV;

public class PasswordDAO_NV {
    public static final String TABLE_NAME = "PasswordNV";
    public static final String SQL_TABLE_Password_NV = "CREATE TABLE PasswordNV ( " +
            "idNVLog text primary key, " +
            "password text)";
    public DataBaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public PasswordDAO_NV(Context context) {
        databaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        this.context = context;
    }

    public boolean checkUser(String idNV, String passNV){
        String[] cols = { "idNVLog" };
        String sel = "idNVLog =? and password =?";
        String[] selArg = {idNV, passNV};
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

    public void createPassNV(NV nv){
        ContentValues values = new ContentValues();
        values.put("idNVLog", nv.getIdNV());
        values.put("password", nv.getPasswordNV());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
    }

}
