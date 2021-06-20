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

import com.shopThuCung.duanshopthucung.DataBase.PasswordDAO_Admin;
import com.shopThuCung.duanshopthucung.Password.Admin;
import com.shopThuCung.duanshopthucung.R;
import com.shopThuCung.duanshopthucung.nhanVien.AddNhanVienActivity;
import com.shopThuCung.duanshopthucung.nhanVien.ListNhanVienActivity;

public class CreateAccAdminActivity extends AppCompatActivity {

    EditText edtId,edtPass, edtRePass;
    PasswordDAO_Admin passwordDAO_admin;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc_admin);

        init();
        Toolbar
                toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void init(){
        edtId = findViewById(R.id.add_Account_Admin_Id);
        edtPass = findViewById(R.id.add_Account_Admin_Password);
        edtRePass = findViewById(R.id.add_Account_Admin_RePassword);
        passwordDAO_admin = new PasswordDAO_Admin(this);
    }

    public void AddAccAdmin(View view) {
        Admin admin = new Admin();
        if (edtId.getText().toString().equals("") || edtPass.getText().toString().equals("") || edtRePass.getText().toString().equals("")){
            Toast.makeText(this,"Cannot create account Admin", Toast.LENGTH_SHORT).show();
        }
       else if(edtRePass.getText().toString().equals(edtPass.getText().toString())){
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kocogi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(CreateAccAdminActivity.this, LoginActivity.class);
                startActivity(intent1);;
                break;
        }
        return true;
    }
}