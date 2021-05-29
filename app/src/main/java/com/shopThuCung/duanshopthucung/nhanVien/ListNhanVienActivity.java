package com.shopThuCung.duanshopthucung.nhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.DataBase.NhanVienDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListNhanVienActivity extends AppCompatActivity {
    List<NhanVien> nhanVienList;
    NhanVienDAO nhanVienDAO;
    ListView lvNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhan_vien);
        lvNV = findViewById(R.id.lvNV);

        nhanVienList = new ArrayList<NhanVien>();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienList = nhanVienDAO.getAllNVString();

        NhanVienAdapter adapter = new NhanVienAdapter(nhanVienList,this);
        lvNV.setAdapter(adapter);
    }

    public void searchNhanVien(View view) {

    }

    public void addNhanVien(View view) {
        startActivity(new Intent(ListNhanVienActivity.this, addNhanVienActivity.class));
    }
}