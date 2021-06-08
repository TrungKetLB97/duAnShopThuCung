package com.shopThuCung.duanshopthucung.Password;

public class Admin {
    private String idAdmin;
    private String passwordAdmin;

    public Admin(String idAdmin, String passwordAdmin) {
        this.idAdmin = idAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public Admin() {
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }
}
