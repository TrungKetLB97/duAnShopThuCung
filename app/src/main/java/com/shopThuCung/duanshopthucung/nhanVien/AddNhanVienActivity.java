package com.shopThuCung.duanshopthucung.nhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.NhanVienDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class AddNhanVienActivity extends AppCompatActivity {
    public EditText edtId, edtName, edtChucVu, edtSdt, edtDiaChi, edtNgaySinh;
    NhanVienDAO nhanVienDAO;
    List<NhanVien> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhan_vien);
        init();

        nhanVienDAO = new NhanVienDAO(this);
        list = new ArrayList<>();
        list = nhanVienDAO.getAllNVString();
    }

    public void init(){
        edtId = findViewById(R.id.nhanVien_addNV_edtId);
        edtName = findViewById(R.id.nhanVien_addNV_edtName);
        edtChucVu = findViewById(R.id.nhanVien_addNV_edtChucVu);
        edtSdt = findViewById(R.id.nhanVien_addNV_edtSdt);
        edtDiaChi = findViewById(R.id.nhanVien_addNV_edtDiaChi);
        edtNgaySinh = findViewById(R.id.nhanVien_addNV_edtNgaySinh);
    }

    public void addNhanVien(View view) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setIdNV(edtId.getText().toString());
        nhanVien.setTenNV(edtName.getText().toString());
        nhanVien.setChucVuNV(edtChucVu.getText().toString());
        nhanVien.setSdtNV(edtSdt.getText().toString());
        nhanVien.setDiaChiNV(edtDiaChi.getText().toString());
        nhanVien.setNgaySinhNV(edtNgaySinh.getText().toString());
        if(nhanVienDAO.insertNV(nhanVien)<0){
            Toast.makeText(this, "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
        }
    }
}