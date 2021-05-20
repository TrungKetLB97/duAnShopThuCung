package com.shopThuCung.duanshopthucung.Product;

public class Product {
    byte[] productImage;
    String productId;
    String productTen;
    String productLoai;
    String productTuoi;
    String productTinhTrangSK;
    String productGiaTien;

    public Product() {
    }

    public Product(byte[] productImage, String productId, String productTen, String productLoai, String productTuoi, String productTinhTrangSK, String productGiaTien) {
        this.productImage = productImage;
        this.productId = productId;
        this.productTen = productTen;
        this.productLoai = productLoai;
        this.productTuoi = productTuoi;
        this.productTinhTrangSK = productTinhTrangSK;
        this.productGiaTien = productGiaTien;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTen() {
        return productTen;
    }

    public void setProductTen(String productTen) {
        this.productTen = productTen;
    }

    public String getProductLoai() {
        return productLoai;
    }

    public void setProductLoai(String productLoai) {
        this.productLoai = productLoai;
    }

    public String getProductTuoi() {
        return productTuoi;
    }

    public void setProductTuoi(String productTuoi) {
        this.productTuoi = productTuoi;
    }

    public String getProductTinhTrangSK() {
        return productTinhTrangSK;
    }

    public void setProductTinhTrangSK(String productTinhTrangSK) {
        this.productTinhTrangSK = productTinhTrangSK;
    }

    public String getProductGiaTien() {
        return productGiaTien;
    }

    public void setProductGiaTien(String productGiaTien) {
        this.productGiaTien = productGiaTien;
    }
}

