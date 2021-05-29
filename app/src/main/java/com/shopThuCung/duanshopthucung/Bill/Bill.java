package com.shopThuCung.duanshopthucung.Bill;

public class Bill {
    String id;
    String tenSP;
    String thanhTien;
    String tenKhachHang;
    String date;

    public Bill() {
    }

    public Bill(String id, String tenSP, String thanhTien, String tenKhachHang) {
        this.id = id;
        this.tenSP = tenSP;
        this.thanhTien = thanhTien;
        this.tenKhachHang = tenKhachHang;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
