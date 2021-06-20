package com.shopThuCung.duanshopthucung.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_Admin;
import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_NV;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtId,edtPass;
    PasswordDAO_Admin passwordDAO_admin;
    PasswordDAO_NV passwordDAO_nv;
    String idLog, passLog;


    boolean varAd, varNv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtId = findViewById(R.id.edt_IdAdmin);
        edtPass = findViewById(R.id.edt_Pass_Admin);
        passwordDAO_admin = new PasswordDAO_Admin(LoginActivity.this);
        passwordDAO_nv = new PasswordDAO_NV(LoginActivity.this);
    }

    public int validateLogin(){
        idLog = edtId.getText().toString();
        passLog = edtPass.getText().toString();
        if(idLog.equals("") || passLog.equals("")){
            Toast.makeText(this,"Cannot Login",Toast.LENGTH_SHORT).show();
        }
        varNv = passwordDAO_nv.checkUser(idLog,passLog);
        varAd = passwordDAO_admin.checkUser(idLog,passLog);

        int a = 0;
        if(varAd) {
            a = 1;
        }
        if(varNv){
            a = 2;
        }
        return a;
    }

    public void Login(View view) {
        int login = validateLogin();
        switch (login){
            case 1:
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case 2:
                Intent intent = new Intent(LoginActivity.this, MainActivity_NV.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "id or password is incorrect, try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveCreateAccNV(View view) {
        startActivity(new Intent(this, CreateAccNVActivity.class));
    }

    public void moveCreateAccAdmin(View view) {
        startActivity(new Intent(this, CreateAccAdminActivity.class));
    }
}