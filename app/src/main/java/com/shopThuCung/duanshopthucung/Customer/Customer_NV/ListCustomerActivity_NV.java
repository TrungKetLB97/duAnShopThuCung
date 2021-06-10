package com.shopThuCung.duanshopthucung.Customer.Customer_NV;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ChiTietCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.Customer.CustomerAdapter;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity_NV extends AppCompatActivity {

    List<Customer> customerList;
    CustomerDAO customerDAO;
    ListView lvKH;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer_nv);
        lvKH = findViewById(R.id.lvKH_NV);

        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(this);
        customerList = customerDAO.getAllKHString();
        customer = new Customer();

        CustomerAdapter adapter = new CustomerAdapter(customerList,this);
        lvKH.setAdapter(adapter);

        lvKH.setOnItemClickListener((parent, view, position, id) -> {
            customer = (Customer) lvKH.getItemAtPosition(position);
            Intent i = new Intent(ListCustomerActivity_NV.this, ChiTietCustomerActivity_NV.class);
            i.putExtra("IdKH", customer.getIdKH());
            i.putExtra("TenKH", customer.getTenKH());
            i.putExtra("SdtKH", customer.getSdtKH());
            i.putExtra("DiaChiKH", customer.getDiaChiKH());
            startActivity(i);
        });
    }

    public void addKhachHang(View view) {
        startActivity(new Intent(this, AddCustomerActivity_NV.class));
    }
    public void searchKhachHang(View view) {
    }
}