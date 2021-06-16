package com.shopThuCung.duanshopthucung.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.Customer.Customer_NV.AddCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ListCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerActivity extends AppCompatActivity {
    EditText edtId, edtName, edtSdt, edtDiaChi;
    List<Customer> customerList;
    CustomerDAO customerDAO;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();
        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(AddCustomerActivity.this);
        customerList = customerDAO.getAllKHString();

        toolbar = findViewById(R.id.toolbar_add_khachHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void init(){
        edtId = findViewById(R.id.khachHang_addKhachHang_edtIDKH);
        edtName = findViewById(R.id.khachHang_addKhachHang_edtNameKH);
        edtSdt = findViewById(R.id.khachHang_addKhachHang_edtSdt);
        edtDiaChi = findViewById(R.id.khachHang_addKhachHang_edtDiaChi);
    }

    public void addKhachHang(View view) {
        Customer customer = new Customer();
        customer.setIdKH(edtId.getText().toString());
        customer.setTenKH(edtName.getText().toString());
        customer.setSdtKH(edtSdt.getText().toString());
        customer.setDiaChiKH(edtDiaChi.getText().toString());
        if (customerDAO.insertKH(customer)<0){
            Toast.makeText(this, "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(AddCustomerActivity.this, ListCustomerActivity.class));
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
                Intent intent1 = new Intent(AddCustomerActivity.this, ListCustomerActivity.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}