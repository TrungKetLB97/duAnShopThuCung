package com.shopThuCung.duanshopthucung.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_Admin;
import com.shopThuCung.duanshopthucung.Password.Admin;
import com.shopThuCung.duanshopthucung.R;

public class CreateAccAdminActivity extends AppCompatActivity {

    EditText edtId,edtPass, edtRePass;
    PasswordDAO_Admin passwordDAO_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc_admin);

        init();
    }

    void init(){
        edtId = findViewById(R.id.add_Account_Admin_Id);
        edtPass = findViewById(R.id.add_Account_Admin_Password);
        edtRePass = findViewById(R.id.add_Account_Admin_RePassword);
        passwordDAO_admin = new PasswordDAO_Admin(this);
    }

    public void AddAccAdmin(View view) {
        Admin admin = new Admin();
        if(edtRePass.getText().toString().equals(edtPass.getText().toString())){
            admin.setIdAdmin(edtId.getText().toString());
            admin.setPasswordAdmin(edtPass.getText().toString());
            passwordDAO_admin.createPassAdmin(admin);
            Toast.makeText(this, "Added success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAccAdminActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Added fail", Toast.LENGTH_SHORT).show();
        }
    }
}