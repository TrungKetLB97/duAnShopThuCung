package com.shopThuCung.duanshopthucung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shopThuCung.duanshopthucung.Bill.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private final SQLiteDatabase db;
    private final SQLiteOpenHelper helper;
    public final static String BILL_TABLE_NAME = "bill";
    public static final String SQL_Bill = "CREATE TABLE BILL (" +
            "ID text PRIMARY KEY," +
            "tenSP text ,"+
            "thanhTien text ,"+
            "tenKhachHang text ,"+
            "date text," +
            ");";
    public BillDAO (final Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Bill> getAllBill(){
        List<Bill> billList = new ArrayList<>();
        Cursor cursor = db.query(BillDAO.BILL_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false ){
            Bill bill = new Bill();
            bill.setId(cursor.getString(cursor.getColumnIndex("ID")));
            bill.setTenSP(cursor.getString(cursor.getColumnIndex("tenSP")));
            bill.setTenKhachHang(cursor.getString(cursor.getColumnIndex("tenKhachHang")));
            bill.setThanhTien(cursor.getString(cursor.getColumnIndex("thanhTien")));
            bill.setDate(cursor.getString(cursor.getColumnIndex("date")));
            billList.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return billList;
    }

    public int insertBill(Bill bill){
        ContentValues values = new ContentValues();
        values.put("ID",bill.getId());
        values.put("tenSP",bill.getTenSP());
        values.put("tenKhachHang",bill.getTenKhachHang());
        values.put("thanhTien", bill.getThanhTien());
        values.put("date",bill.getDate());
        try{
            if(db.insert(BillDAO.BILL_TABLE_NAME,null,values) <0){
                return -1;
            }
        }catch(Exception e){

        }
        return 1;
    }
    public int updateBill(Bill bill){
        ContentValues values = new ContentValues();
        values.put("ID",bill.getId());
        values.put("tenSP", bill.getTenSP());
        values.put("tenKhachHang",bill.getTenKhachHang());
        values.put("thanhTien",bill.getThanhTien());
        int kq = db.update(BillDAO.BILL_TABLE_NAME,values,"id=?", new String[]{bill.getId()});
        if (kq == 0 ){
            return -1;
        }
        return 1;
    }
    public int deleteBill(String billID){
        int kq = db.delete(BillDAO.BILL_TABLE_NAME,"ID=?", new String[]{billID});
        if (kq == 0){
            return -1;
        }
        return 1;
    }
    public double getDoanhThuTheoNgay(){
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where bill.date = date('now')";
        Cursor cursor = db.rawQuery(SQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where strftime('%m',bill.date) = strftime('%m','now')";
        Cursor cursor = db.rawQuery(SQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
        String SQL = "SELECT SUM(totalMoney) FROM bill where strftime('%Y',bill.date) = strftime('%Y','now')";
        Cursor cursor = db.rawQuery(SQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
}
