package com.shopThuCung.duanshopthucung.Customer.Customer_NV;

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

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerActivity_NV extends AppCompatActivity {

    EditText edtId, edtName, edtSdt, edtDiaChi;
    List<Customer> customerList;
    CustomerDAO customerDAO;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer_nv);

        init();
        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(AddCustomerActivity_NV.this);
        customerList = customerDAO.getAllKHString();

        toolbar = findViewById(R.id.toolbar_add_khachHang_NV);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void init(){
        edtId = findViewById(R.id.khachHang_addKhachHang_edtIDKH_NV);
        edtName = findViewById(R.id.khachHang_addKhachHang_edtNameKH_NV);
        edtSdt = findViewById(R.id.khachHang_addKhachHang_edtSdt_NV);
        edtDiaChi = findViewById(R.id.khachHang_addKhachHang_edtDiaChi_NV);
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
            Intent i = new Intent(AddCustomerActivity_NV.this, ListCustomerActivity_NV.class);
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
                Intent intent1 = new Intent(AddCustomerActivity_NV.this, ListCustomerActivity_NV.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}