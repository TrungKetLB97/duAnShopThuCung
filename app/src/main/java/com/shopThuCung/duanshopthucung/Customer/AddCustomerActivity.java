package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.KhachHangDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerActivity extends AppCompatActivity {

    public EditText edtId, edtName, edtSdt, edtDiaChi;
    List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        edtId = findViewById(R.id.khachHang_addKH_edtId);
        edtName = findViewById(R.id.khachHang_addKH_edtName);
        edtSdt = findViewById(R.id.khachHang_addKH_edtSdt);
        edtDiaChi = findViewById(R.id.khachHang_addKH_edtDiaChi);

        khachHangList = new ArrayList<KhachHang>();
        khachHangDAO = new KhachHangDAO(this);
        khachHangList = khachHangDAO.getAllKHString();
    }

    public void addKhachHang(View view) {
        KhachHang khachHang = new KhachHang();
        khachHang.setIdKH(edtId.getText().toString());
        khachHang.setTenKH(edtName.getText().toString());
        khachHang.setSdtKH(edtSdt.getText().toString());
        khachHang.setDiaChiKH(edtDiaChi.getText().toString());
        if (khachHangDAO.insertKH(khachHang)<0){
            Toast.makeText(this, "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
        }
    }
}