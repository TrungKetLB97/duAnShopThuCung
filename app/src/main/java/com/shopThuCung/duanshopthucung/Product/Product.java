package com.shopThuCung.duanshopthucung.Product;

public class Product {

    byte[] productImage;
    String code;
    String name;
    int age;
    double weight;
    String gender;
    String health;
    double price;

    public Product() {
    }

    public Product(byte[] productImage, String code, String name, int age, double weight, String gender, String health, double price) {
        this.productImage = productImage;
        this.code = code;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.health = health;
        this.price = price;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


