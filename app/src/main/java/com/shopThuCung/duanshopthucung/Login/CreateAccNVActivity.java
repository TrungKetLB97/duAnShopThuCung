package com.shopThuCung.duanshopthucung.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_Admin;
import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_NV;
import com.shopThuCung.duanshopthucung.Password.NV;
import com.shopThuCung.duanshopthucung.R;

public class CreateAccNVActivity extends AppCompatActivity {

    EditText edtId,edtPass,edtRePass;
    PasswordDAO_NV passwordDAO_nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc_nv);
        init();
    }

    void init(){
        edtId = findViewById(R.id.add_Account_NV_Id);
        edtPass = findViewById(R.id.add_Account_NV_Password);
        edtRePass = findViewById(R.id.add_Account_NV_RePassword);
        passwordDAO_nv = new PasswordDAO_NV(this);
    }

    public void AddAccNV(View view) {
        NV nv = new NV();
        if(edtRePass.getText().toString().equals(edtPass.getText().toString())){
            nv.setIdNV(edtId.getText().toString());
            nv.setPasswordNV(edtPass.getText().toString());
            passwordDAO_nv.createPassNV(nv);
            Toast.makeText(this, "Added success", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Added fail", Toast.LENGTH_SHORT).show();
        }
    }
}