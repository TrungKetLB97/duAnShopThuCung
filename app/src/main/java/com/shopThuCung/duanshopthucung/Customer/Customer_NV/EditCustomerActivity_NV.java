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
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ChiTietCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.Customer.EditCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

public class EditCustomerActivity_NV extends AppCompatActivity {

    TextView tvIDKH;
    EditText edtTenKh, edtSdtKH, edtDiaChiKH;
    String idKH, tenKH, sdtKH, diaChiKH;
    CustomerDAO customerDAO;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_nv);
        customerDAO = new CustomerDAO(this);
        init();

        Intent i = getIntent();
        idKH = i.getStringExtra("IdKH");
        tenKH = i.getStringExtra("TenKH");
        sdtKH = i.getStringExtra("SdtKH");
        diaChiKH = i.getStringExtra("DiaChiKH");

        Toolbar
                toolbar = findViewById(R.id.toolbar_edit_KhachHang_nv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvIDKH.setText("ID: "+ idKH);
        edtTenKh.setText(tenKH);
        edtSdtKH.setText(sdtKH);
        edtDiaChiKH.setText(diaChiKH);

    }

    void init(){
        tvIDKH = findViewById(R.id.khachHang_editKhachHang_tvID_NV);
        edtTenKh = findViewById(R.id.khachHang_editKhachHang_edtNameKH_NV);
        edtSdtKH = findViewById(R.id.khachHang_editKhachHang_edtSdtKH_NV);
        edtDiaChiKH = findViewById(R.id.khachHang_editKhachHang_edtDiaChiKH_NV);
    }

    public void editKhachHang(View view) {
        Customer customer = new Customer();
        customer.setIdKH(idKH);
        customer.setTenKH(edtTenKh.getText().toString());
        customer.setSdtKH(edtSdtKH.getText().toString());
        customer.setDiaChiKH(edtDiaChiKH.getText().toString());
        if(customerDAO.updateKH(customer)<0){
            Toast.makeText(this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(EditCustomerActivity_NV.this,ListCustomerActivity_NV.class);
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
                Intent intent1 = new Intent(EditCustomerActivity_NV.this, ListCustomerActivity_NV.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}