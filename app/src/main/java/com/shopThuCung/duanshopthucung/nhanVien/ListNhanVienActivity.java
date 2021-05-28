package com.shopThuCung.duanshopthucung.nhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shopThuCung.duanshopthucung.R;

public class ListNhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhan_vien);
    }

    public void searchNhanVien(View view) {
    }

    public void postNhanVien(View view) {
        startActivity(new Intent(this, addNhanVienActivity.class));
    }
}