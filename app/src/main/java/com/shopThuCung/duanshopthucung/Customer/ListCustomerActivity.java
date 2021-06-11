package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
    EditText edtSearchCus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        lvKH = findViewById(R.id.lvKH_NV);
//        edtSearchCus = findViewById(R.id.edtSearchCus_NV);

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

    public void addKhachHang(View view) {
        startActivity(new Intent(this,AddCustomerActivity.class));
    }
}