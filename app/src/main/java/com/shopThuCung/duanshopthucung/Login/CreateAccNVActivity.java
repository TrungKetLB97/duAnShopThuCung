package com.shopThuCung.duanshopthucung.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_NV;
import com.shopThuCung.duanshopthucung.Password.NV;
import com.shopThuCung.duanshopthucung.R;
import com.shopThuCung.duanshopthucung.nhanVien.AddNhanVienActivity;
import com.shopThuCung.duanshopthucung.nhanVien.ListNhanVienActivity;

public class CreateAccNVActivity extends AppCompatActivity {

    EditText edtId,edtPass,edtRePass;
    PasswordDAO_NV passwordDAO_nv;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc_nv);
        init();

        Toolbar
                toolbar = findViewById(R.id.toolbar_staffs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
            Intent intent = new Intent(CreateAccNVActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Added fail", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kocogi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(CreateAccNVActivity.this, LoginActivity.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}