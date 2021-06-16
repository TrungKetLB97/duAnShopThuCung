package com.shopThuCung.duanshopthucung.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ChiTietCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ListCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;
import com.shopThuCung.duanshopthucung.nhanVien.ChiTietNhanVienActivity;

public class ChiTietCustomerActivity extends AppCompatActivity {

    TextView tvIDKH, tvTenKH, tvSdtKH, tvDiaChiKH;
    String idKH, tenKH, sdtKH, diaChiKH;
    CustomerDAO customerDAO;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_customer);

        customerDAO = new CustomerDAO(this);
        init();
        Intent i = getIntent();
        idKH = i.getStringExtra("IdKH");
        tenKH = i.getStringExtra("TenKH");
        sdtKH = i.getStringExtra("SdtKH");
        diaChiKH = i.getStringExtra("DiaChiKH");

        Toolbar
                toolbar = findViewById(R.id.toolbar_detail_khachHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvIDKH.setText("ID: " + idKH);
        tvTenKH.setText("Tên: " + tenKH);
        tvSdtKH.setText("SĐT: " + sdtKH);
        tvDiaChiKH.setText("Địa chỉ : " + diaChiKH);

    }

    void init(){
        tvIDKH = findViewById(R.id.khachHang_detail_tvIDKH);
        tvTenKH = findViewById(R.id.khachHang_detail_tvNameKH);
        tvSdtKH = findViewById(R.id.khachHang_detail_tvSdtKH);
        tvDiaChiKH = findViewById(R.id.khachHang_detail_tvDiaChiKH);
    }

    public void moveEditKH(View view) {
        Intent intent = new Intent(ChiTietCustomerActivity.this,EditCustomerActivity.class);
        intent.putExtra("IdKH",idKH);
        intent.putExtra("TenKH",tenKH);
        intent.putExtra("SdtKH",sdtKH);
        intent.putExtra("DiaChiKH",diaChiKH);
        startActivity(intent);
    }

    public void DeleteKH(View view) {
        if(customerDAO.delNV(idKH)<0)
        {
            Toast.makeText(this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ChiTietCustomerActivity.this, ListCustomerActivity.class);
            startActivity(intent);
        }

//        startActivity(new Intent(ChiTietCustomerActivity.this, ListCustomerActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kocogi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ChiTietCustomerActivity.this, ListCustomerActivity.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}