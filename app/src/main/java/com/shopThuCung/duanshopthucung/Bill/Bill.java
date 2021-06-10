package com.shopThuCung.duanshopthucung.Bill;

public class Bill {
    String maHoaDon;
    String tenKhachHang;
    String tenSanPham;
    double tongTien;
    String date;

    public Bill() {
    }

    public Bill(String maHoaDon, String tenKhachHang, String tenSanPham, double tongTien, String date) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.tenSanPham = tenSanPham;
        this.tongTien = tongTien;
        this.date = date;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}