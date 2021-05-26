package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shopThuCung.duanshopthucung.Customer.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    public static final String SQL_TABLE_KhachHang = "CREATE TABLE KhachHang ( idKH text primary key, tenKH text, sdtKh text, diaChiKH text)";
    public static final String TABLE_NAME = "KhachHang";
    public DataBaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public KhachHangDAO(Context context) {
        this.context = context;
        databaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public int insertKH(KhachHang khachHang){
        ContentValues values = new ContentValues();
        values.put("idKH", khachHang.getIdKH());
        values.put("tenKH", khachHang.getTenKH());
        values.put("sdtKH", khachHang.getSdtKH());
        values.put("diaChiKH", khachHang.getDiaChiKH());
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    public int updateKH(KhachHang khachHang){
        ContentValues values = new ContentValues();
        values.put("idKH", khachHang.getIdKH());
        values.put("tenKH", khachHang.getTenKH());
        values.put("sdtKH", khachHang.getSdtKH());
        values.put("diaChiKH", khachHang.getDiaChiKH());
        if(sqLiteDatabase.update(TABLE_NAME,values,"idNV =?", new String[]{khachHang.getIdKH()})<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    public int delNV(String idKH){
        if (sqLiteDatabase.delete(TABLE_NAME, "idKH=?",new String[]{idKH})<0){
            return -1;
        }
        return 1;
    }

    //doc dữ liệu
    public List<KhachHang> getAllKHString(){
        List<KhachHang> list = new ArrayList<KhachHang>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            KhachHang khachHang = new KhachHang();
            khachHang.setIdKH(cursor.getString(0));
            khachHang.setTenKH(cursor.getString(1));
            khachHang.setSdtKH(cursor.getString(2));
            khachHang.setDiaChiKH(cursor.getString(3));
            list.add(khachHang);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
