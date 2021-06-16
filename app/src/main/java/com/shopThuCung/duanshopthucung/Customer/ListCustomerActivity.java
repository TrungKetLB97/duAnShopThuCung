package com.shopThuCung.duanshopthucung.Customer;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.Customer.Customer_NV.AddCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ChiTietCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ListCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity extends AppCompatActivity {
    List<Customer> customerList;
    CustomerDAO customerDAO;
    ListView lvKH;
    Customer customer;
    EditText edtSearchCus;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        lvKH = findViewById(R.id.lvKH);
        edtSearchCus = findViewById(R.id.edtSearchCus);

        customerList = new ArrayList<>();
        customerDAO = new CustomerDAO(this);
        customerList = customerDAO.getAllKHString();
        customer = new Customer();

        Toolbar
                toolbar = findViewById(R.id.toolbar_khadmin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        getMenuInflater().inflate(R.menu.kh_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ListCustomerActivity.this, MainActivity.class);
                startActivity(intent1);;
                break;
            case R.id.addKH_ad:
                Intent intent = new Intent(ListCustomerActivity.this, AddCustomerActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}