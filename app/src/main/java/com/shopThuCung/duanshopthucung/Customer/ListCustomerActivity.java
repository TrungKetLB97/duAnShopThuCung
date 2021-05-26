package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.DataBase.KhachHangDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity extends AppCompatActivity {

    List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    ListView lvKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        lvKH = findViewById(R.id.lvKH);

        khachHangList = new ArrayList<KhachHang>();
        khachHangDAO = new KhachHangDAO(this);
        khachHangList = khachHangDAO.getAllKHString();

        KhachHangAdapter adapter = new KhachHangAdapter(khachHangList,this);
        lvKH.setAdapter(adapter);
    }

    public void addKhachHang(View view) {
        startActivity(new Intent(this,AddCustomerActivity.class));
    }

    public void searchKhachHang(View view) {
    }
}