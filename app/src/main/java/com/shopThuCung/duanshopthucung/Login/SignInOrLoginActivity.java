package com.shopThuCung.duanshopthucung.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shopThuCung.duanshopthucung.R;

public class SignInOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_or_login);
    }

    public void moveBossMain(View view) {
        startActivity(new Intent(SignInOrLoginActivity.this, LoginActivity.class));
    }

    public void moveCreateAccNV(View view) {
        startActivity(new Intent(this, CreateAccNVActivity.class));
    }

    public void moveCreateAccAdmin(View view) {
        startActivity(new Intent(this, CreateAccAdminActivity.class));
    }
}