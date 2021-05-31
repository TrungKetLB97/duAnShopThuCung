package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.R;

public class ChiTietCustomerActivity extends AppCompatActivity {

    CustomerDAO customerDAO;
    String idKH, tenKH, sdtKH, diachiKH;
    TextView tvIDKH, tvTenKH, tvSdtKH, tvDiaChiKH;
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
        diachiKH = i.getStringExtra("DiaChiKH");

        tvIDKH.setText(idKH);
        tvIDKH.setText(tenKH);
        tvIDKH.setText(sdtKH);
        tvIDKH.setText(diachiKH);
    }

    void init(){
        tvIDKH = findViewById(R.id.khachHang_detail_tvIDKH);
        tvTenKH = findViewById(R.id.khachHang_detail_tvNameKH);
        tvSdtKH = findViewById(R.id.khachHang_detail_tvSdtKH);
        tvDiaChiKH = findViewById(R.id.khachHang_detail_tvDiaChiKH);
    }

    public void moveEditKH(View view) {
        Intent intent = new Intent(ChiTietCustomerActivity.this, EditCustomerActivity.class);
        intent.putExtra("IdKH", idKH);
        intent.putExtra("TenKH", tenKH);
        intent.putExtra("SdtKH", sdtKH);
        intent.putExtra("DiaChiKH", diachiKH);
        startActivity(intent);
    }

    public void DeleteKH(View view) {
        if(customerDAO.delNV(tvIDKH.getText().toString())<0)
        {
            Toast.makeText(this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
        }
    }
}