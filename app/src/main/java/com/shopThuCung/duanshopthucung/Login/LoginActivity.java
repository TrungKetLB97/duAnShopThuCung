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
    Button btnLoginNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtId = findViewById(R.id.edt_IdAdmin);
        edtPass = findViewById(R.id.edt_Pass_Admin);
        btnLoginNV = findViewById(R.id.btnLoginNV);
        passwordDAO_admin = new PasswordDAO_Admin(LoginActivity.this);
        passwordDAO_nv = new PasswordDAO_NV(LoginActivity.this);


        //btnLoginNV.setOnClickListener(v ->
          //      validateLoginNV(edtId.getText().toString(),edtPass.getText().toString()));
    }

    public void validateLoginAdmin(String idAd, String passAd){
        boolean varAd = passwordDAO_admin.checkUser(idAd,passAd);
        boolean varNv = passwordDAO_nv.checkUser(idAd,passAd);
        if(varAd){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else if(varNv){
            Intent i = new Intent(LoginActivity.this, MainActivity_NV.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "id or password is incorrect, try again", Toast.LENGTH_SHORT).show();
        }
    }

//    public void validateLoginNV(String idAd, String passAd){
//        else{
//            Toast.makeText(this, "id or password is incorrect, try again", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void LoginAdmin(View view) {
        validateLoginAdmin(edtId.getText().toString(),edtPass.getText().toString());
    }
}