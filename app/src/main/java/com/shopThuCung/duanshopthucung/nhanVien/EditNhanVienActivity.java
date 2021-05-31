package com.shopThuCung.duanshopthucung.nhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.NhanVienDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class EditNhanVienActivity extends AppCompatActivity {

    EditText edtName, edtChucVu, edtSdt, edtDiaChi, edtNgaySinh;
    TextView tvIDNV;
    NhanVienDAO nhanVienDAO;
    List<NhanVien> nhanVienList;
    String idNV, tenNV, chucVuNV, sdtNV, diaChiNV, ngaySinhNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_vien);

        init();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienList = new ArrayList<>();

        Intent i = getIntent();
        idNV = i.getStringExtra("IdNV");
        tenNV = i.getStringExtra("TenNV");
        chucVuNV = i.getStringExtra("ChucVuNV");
        sdtNV = i.getStringExtra("SdtNV");
        diaChiNV = i.getStringExtra("DiaChiNV");
        ngaySinhNV = i.getStringExtra("NgaySinhNV");

        tvIDNV.setText("ID: "+idNV);
        edtName.setText(tenNV);
        edtChucVu.setText(chucVuNV);
        edtSdt.setText(sdtNV);
        edtDiaChi.setText(diaChiNV);
        edtNgaySinh.setText(ngaySinhNV);
    }
    public void init(){
        tvIDNV = findViewById(R.id.nhanVien_editNhanVien_txtID);
        edtName = findViewById(R.id.nhanVien_editNhanVien_edtName);
        edtChucVu = findViewById(R.id.nhanVien_editNhanVien_edtChucVu);
        edtSdt = findViewById(R.id.nhanVien_editNhanVien_edtSdt);
        edtDiaChi = findViewById(R.id.nhanVien_editNhanVien_edtDiaChi);
        edtNgaySinh = findViewById(R.id.nhanVien_editNhanVien_edtNgaySinh);
    }

    public void editNhanVien(View view){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setIdNV(idNV);
        nhanVien.setTenNV(edtName.getText().toString());
        nhanVien.setChucVuNV(edtChucVu.getText().toString());
        nhanVien.setSdtNV(edtSdt.getText().toString());
        nhanVien.setDiaChiNV(edtDiaChi.getText().toString());
        nhanVien.setNgaySinhNV(edtNgaySinh.getText().toString());
        if(nhanVienDAO.updateNV(nhanVien)<0){
            Toast.makeText(this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
        }
    }
}