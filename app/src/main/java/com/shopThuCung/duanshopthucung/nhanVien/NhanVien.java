package com.shopThuCung.duanshopthucung.nhanVien;

public class NhanVien {
    private String idNV;
    private String tenNV;
    private String chucVuNV;
    private String sdtNV;
    private String ngaySinhNV;
    private String diaChiNV;

    public NhanVien() {

    }

    public NhanVien(String idNV, String tenNV, String chucVuNV, String sdtNV, String ngaySinhNV, String diaChiNV) {
        this.idNV = idNV;
        this.tenNV = tenNV;
        this.chucVuNV = chucVuNV;
        this.sdtNV = sdtNV;
        this.ngaySinhNV = ngaySinhNV;
        this.diaChiNV = diaChiNV;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVuNV() {
        return chucVuNV;
    }

    public void setChucVuNV(String chucVuNV) {
        this.chucVuNV = chucVuNV;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getNgaySinhNV() {
        return ngaySinhNV;
    }

    public void setNgaySinhNV(String ngaySinhNV) {
        this.ngaySinhNV = ngaySinhNV;
    }

    public String getDiaChiNV() {
        return diaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        this.diaChiNV = diaChiNV;
    }
}
