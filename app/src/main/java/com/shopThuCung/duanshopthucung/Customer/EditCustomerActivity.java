package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.R;

import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;

public class EditCustomerActivity extends AppCompatActivity {

    EditText edtTenKH, edtSdtKH, edtDiaChiKH;
    TextView tvIDKH;
    CustomerDAO customerDAO;
    List<Customer> customerList;
    String idKH, tenKH, sdtKH, diaChiKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        customerDAO = new CustomerDAO(this);
        init();
        customerList = new ArrayList<>();

        Intent i = getIntent();
        idKH = i.getStringExtra("IdKH");
        tenKH = i.getStringExtra("TenKH");
        sdtKH = i.getStringExtra("SdtKH");
        diaChiKH = i.getStringExtra("DiaChiKH");

        tvIDKH.setText("ID: "+ idKH);
        edtTenKH.setText(tenKH);
        edtSdtKH.setText(sdtKH);
        edtDiaChiKH.setText(diaChiKH);

    }

    void init(){
        edtTenKH = findViewById(R.id.khachHang_editKhachHang_edtNameKH);
        edtSdtKH = findViewById(R.id.khachHang_editKhachHang_edtSdtKH);
        edtDiaChiKH = findViewById(R.id.khachHang_editKhachHang_edtDiaChiKH);
    }

    public void editKhachHang(View view) {
        Customer customer = new Customer();
        customer.setIdKH(idKH);
        customer.setTenKH(edtTenKH.getText().toString());
        customer.setSdtKH(edtSdtKH.getText().toString());
        customer.setDiaChiKH(edtDiaChiKH.getText().toString());
        if(customerDAO.updateKH(customer)<0){
            Toast.makeText(this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
        }
    }


}