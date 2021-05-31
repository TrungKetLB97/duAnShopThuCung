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
    NhanVien nhanVien;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhan_vien);

        lvNV = findViewById(R.id.lvNV);

        nhanVienList = new ArrayList<NhanVien>();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienList = nhanVienDAO.getAllNVString();
        nhanVien = new NhanVien();

        NhanVienAdapter adapter = new NhanVienAdapter(nhanVienList,this);
        lvNV.setAdapter(adapter);

        lvNV.setOnItemClickListener((parent, view, position, id) -> {
            nhanVien = (NhanVien) lvNV.getItemAtPosition(position);
            pos=position;
            Intent i = new Intent(ListNhanVienActivity.this,ChiTietNhanVienActivity.class);
            i.putExtra("IdNV",nhanVien.getIdNV());
            i.putExtra("TenNV",nhanVien.getTenNV());
            i.putExtra("ChucVuNV",nhanVien.getChucVuNV());
            i.putExtra("SdtNV",nhanVien.getSdtNV());
            i.putExtra("DiaChiNV",nhanVien.getDiaChiNV());
            i.putExtra("NgaySinhNV",nhanVien.getNgaySinhNV());
            startActivity(i);
        });
    }

    public void searchNhanVien(View view) {

    }

    public void addNhanVien(View view) {
        startActivity(new Intent(ListNhanVienActivity.this, AddNhanVienActivity.class));
    }
}