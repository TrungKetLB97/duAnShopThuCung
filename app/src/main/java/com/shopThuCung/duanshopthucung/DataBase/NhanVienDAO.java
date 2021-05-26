package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shopThuCung.duanshopthucung.nhanVien.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public static final String SQL_TABLE_NhanVien = "CREATE TABLE NhanVien ( idNV text primary key, tenNV text, sdtNV text, chucVuNV text, diaChiNV text, ngaySinhNV text)";
    public static final String TABLE_NAME = "NhanVien";
    public DataBaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public NhanVienDAO(Context context) {
        this.context = context;
        databaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public int insertNV(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("idNV", nv.getIdNV());
        values.put("tenNV", nv.getTenNV());
        values.put("sdtNV", nv.getSdtNV());
        values.put("chucVuNV", nv.getChucVuNV());
        values.put("diaChiNV", nv.getDiaChiNV());
        values.put("ngaySinhNV", nv.getNgaySinhNV());
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    public int updateNV(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("idNV", nv.getIdNV());
        values.put("tenNV", nv.getTenNV());
        values.put("sdtNV", nv.getSdtNV());
        values.put("chucVuNV", nv.getChucVuNV());
        values.put("diaChiNV", nv.getDiaChiNV());
        values.put("ngaySinhNV", nv.getNgaySinhNV());
        if(sqLiteDatabase.update(TABLE_NAME,values,"idNV =?", new String[]{nv.getIdNV()})<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    public int delNV(String idNV){
        if (sqLiteDatabase.delete(TABLE_NAME, "idNV=?",new String[]{idNV})<0){
            return -1;
        }
        return 1;
    }

    //doc dữ liệu
    public List<NhanVien> getAllNVString(){
        List<NhanVien> list = new ArrayList<NhanVien>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setIdNV(cursor.getString(0));
            nhanVien.setTenNV(cursor.getString(1));
            nhanVien.setSdtNV(cursor.getString(2));
            nhanVien.setChucVuNV(cursor.getString(3));
            nhanVien.setDiaChiNV(cursor.getString(4));
            nhanVien.setNgaySinhNV(cursor.getString(5));
            list.add(nhanVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
