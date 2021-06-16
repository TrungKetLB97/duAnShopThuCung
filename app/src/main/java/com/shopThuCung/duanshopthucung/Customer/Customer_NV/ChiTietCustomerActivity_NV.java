package com.shopThuCung.duanshopthucung.Customer.Customer_NV;

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

import com.shopThuCung.duanshopthucung.Customer.ChiTietCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.EditCustomerActivity;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

public class ChiTietCustomerActivity_NV extends AppCompatActivity {

    TextView tvIDKH, tvTenKH, tvSdtKH, tvDiaChiKH;
    String idKH, tenKH, sdtKH, diaChiKH;
    CustomerDAO customerDAO;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_customer_nv);

        customerDAO = new CustomerDAO(this);
        init();
        Intent i = getIntent();
        idKH = i.getStringExtra("IdKH");
        tenKH = i.getStringExtra("TenKH");
        sdtKH = i.getStringExtra("SdtKH");
        diaChiKH = i.getStringExtra("DiaChiKH");

        Toolbar
                toolbar = findViewById(R.id.toolbar_detail_khachHang_nv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvIDKH.setText("ID : "+idKH);
        tvTenKH.setText("Tên : "+tenKH);
        tvSdtKH.setText("SĐT : "+sdtKH);
        tvDiaChiKH.setText("Địa Chỉ : "+diaChiKH);
    }

    void init(){
        tvIDKH = findViewById(R.id.khachHang_detail_tvIDKH_NV);
        tvTenKH = findViewById(R.id.khachHang_detail_tvNameKH_NV);
        tvSdtKH = findViewById(R.id.khachHang_detail_tvSdtKH_NV);
        tvDiaChiKH = findViewById(R.id.khachHang_detail_tvDiaChiKH_NV);
    }

    public void moveEditKH(View view) {
        Intent intent = new Intent(ChiTietCustomerActivity_NV.this, EditCustomerActivity_NV.class);
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
            Intent i = new Intent(ChiTietCustomerActivity_NV.this, ListCustomerActivity_NV.class);
            startActivity(i);
        }
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
                Intent intent1 = new Intent(ChiTietCustomerActivity_NV.this, ListCustomerActivity_NV.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}