package com.shopThuCung.duanshopthucung.Customer.Customer_NV;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ChiTietCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.Customer.CustomerAdapter;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Product.addProductActivity;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity_NV extends AppCompatActivity {

    List<Customer> customerList;
    CustomerDAO customerDAO;
    ListView lvKH;
    Customer customer;
    EditText edtSearchCus;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer_nv);
        lvKH = findViewById(R.id.lvKH_NV);
        edtSearchCus = findViewById(R.id.edtSearchCus_NV);

        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(this);
        customerList = customerDAO.getAllKHString();
        customer = new Customer();

        Toolbar
                toolbar = findViewById(R.id.toolbar_listKH);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        edtSearchCus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khachhang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ListCustomerActivity_NV.this, MainActivity_NV.class);
                startActivity(intent1);;
                break;
            case R.id.addKH:
                Intent intent = new Intent(ListCustomerActivity_NV.this, AddCustomerActivity_NV.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}