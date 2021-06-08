package com.shopThuCung.duanshopthucung.Password;

public class NV {
    private String idNV;
    private String passwordNV;

    public NV(String idNV, String passwordNV) {
        this.idNV = idNV;
        this.passwordNV = passwordNV;
    }

    public NV() {
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getPasswordNV() {
        return passwordNV;
    }

    public void setPasswordNV(String passwordNV) {
        this.passwordNV = passwordNV;
    }
}
