package com.shopThuCung.duanshopthucung.Customer;

public class Customer {
    private String idKH;
    private String tenKH;
    private String sdtKH;
    private String diaChiKH;

    public Customer(String idKH, String tenKH, String sdtKH, String diaChiKH) {
        this.idKH = idKH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
    }

    public Customer() {
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }
}
