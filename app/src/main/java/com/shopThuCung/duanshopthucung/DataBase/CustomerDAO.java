package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shopThuCung.duanshopthucung.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static final String SQL_TABLE_KhachHang = "CREATE TABLE KhachHang ( " +
            "idKH text primary key, tenKH text, sdtKh text, diaChiKH text)";
    public static final String TABLE_NAME = "KhachHang";
    public DataBaseHelper databaseHelper;
    public SQLiteDatabase sqLiteDatabase;
    private Context context;

    public CustomerDAO(Context context) {
        this.context = context;
        databaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public int insertKH(Customer customer){
        ContentValues values = new ContentValues();
        values.put("idKH", customer.getIdKH());
        values.put("tenKH", customer.getTenKH());
        values.put("sdtKH", customer.getSdtKH());
        values.put("diaChiKH", customer.getDiaChiKH());
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    public int updateKH(Customer customer){
        ContentValues values = new ContentValues();
        values.put("idKH", customer.getIdKH());
        values.put("tenKH", customer.getTenKH());
        values.put("sdtKH", customer.getSdtKH());
        values.put("diaChiKH", customer.getDiaChiKH());
        if(sqLiteDatabase.update(TABLE_NAME,values,"idKH =?", new String[]{customer.getIdKH()})<0){
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
    public List<Customer> getAllKHString(){
        List<Customer> list = new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Customer customer = new Customer();
            customer.setIdKH(cursor.getString(0));
            customer.setTenKH(cursor.getString(1));
            customer.setSdtKH(cursor.getString(2));
            customer.setDiaChiKH(cursor.getString(3));
            list.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
