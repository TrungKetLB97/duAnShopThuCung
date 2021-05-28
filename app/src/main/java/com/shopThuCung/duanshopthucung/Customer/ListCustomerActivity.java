package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shopThuCung.duanshopthucung.R;

public class ListCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
    }

    public void searchKhachHang(View view) {
    }

    public void postKhachHang(View view) {
        startActivity(new Intent(this, AddCustomerActivity.class));
    }
}