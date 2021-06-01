package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity extends AppCompatActivity {
    List<Customer> customerList;
    CustomerDAO customerDAO;
    ListView lvKH;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        lvKH = findViewById(R.id.lvKH);

        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(this);
        customerList = customerDAO.getAllKHString();
        customer = new Customer();

        CustomerAdapter adapter = new CustomerAdapter(customerList,this);
        lvKH.setAdapter(adapter);

        lvKH.setOnItemClickListener((parent, view, position, id) -> {
            customer = (Customer) lvKH.getItemAtPosition(position);
            Intent i = new Intent(ListCustomerActivity.this, ChiTietCustomerActivity.class);
            i.putExtra("IdKH", customer.getIdKH());
            i.putExtra("TenKH", customer.getTenKH());
            i.putExtra("SdtKH", customer.getSdtKH());
            i.putExtra("DiaChiKH", customer.getDiaChiKH());
            startActivity(i);
        });
    }

    public void addKhachHang(View view) {
        startActivity(new Intent(this,AddCustomerActivity.class));
    }
    public void searchKhachHang(View view) {
    }
}