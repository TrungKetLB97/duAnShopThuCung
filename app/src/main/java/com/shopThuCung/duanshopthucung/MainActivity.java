package com.shopThuCung.duanshopthucung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.nhanVien.ListNhanVienActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void thuCung(View view) {
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(intent);
    }

    public void nhanVien(View view) {
        Intent intent = new Intent(MainActivity.this, ListNhanVienActivity.class);
        startActivity(intent);
    }

    public void khachHang(View view) {
        startActivity(new Intent(MainActivity.this, ListCustomerActivity.class));
    }

    public void hoaDon(View view) {
    }

    public void thonhKe(View view) {
    }
}