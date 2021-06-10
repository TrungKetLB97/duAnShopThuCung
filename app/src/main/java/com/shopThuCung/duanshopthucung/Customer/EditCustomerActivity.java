package com.shopThuCung.duanshopthucung.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;

public class EditCustomerActivity extends AppCompatActivity {

    TextView tvIDKH;
    EditText edtTenKh, edtSdtKH, edtDiaChiKH;
    String idKH, tenKH, sdtKH, diaChiKH;
    CustomerDAO customerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        customerDAO = new CustomerDAO(this);
        init();

        Intent i = getIntent();
        idKH = i.getStringExtra("IdKH");
        tenKH = i.getStringExtra("TenKH");
        sdtKH = i.getStringExtra("SdtKH");
        diaChiKH = i.getStringExtra("DiaChiKH");

        tvIDKH.setText("ID: "+ idKH);
        edtTenKh.setText(tenKH);
        edtSdtKH.setText(sdtKH);
        edtDiaChiKH.setText(diaChiKH);

    }

    void init(){
        tvIDKH = findViewById(R.id.khachHang_editKhachHang_tvID);
        edtTenKh = findViewById(R.id.khachHang_editKhachHang_edtNameKH);
        edtSdtKH = findViewById(R.id.khachHang_editKhachHang_edtSdtKH);
        edtDiaChiKH = findViewById(R.id.khachHang_editKhachHang_edtDiaChiKH);
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
        }
        startActivity(new Intent(EditCustomerActivity.this, MainActivity.class));
    }


}