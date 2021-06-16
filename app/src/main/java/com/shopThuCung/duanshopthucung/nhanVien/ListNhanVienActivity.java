package com.shopThuCung.duanshopthucung.nhanVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.DataBase.NhanVienDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class ListNhanVienActivity extends AppCompatActivity {
    List<NhanVien> nhanVienList;
    NhanVienDAO nhanVienDAO;
    ListView lvNV;
    NhanVien nhanVien;
    EditText edtSearchNV;
    int pos=0;
    NhanVienAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhan_vien);

        lvNV = findViewById(R.id.lvNV);
        edtSearchNV = findViewById(R.id.edtSearhNV);

        nhanVienList = new ArrayList<>();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienList = nhanVienDAO.getAllNVString();
        nhanVien = new NhanVien();

        Toolbar toolbar = findViewById(R.id.toolbar_listnv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter = new NhanVienAdapter(nhanVienList,this);
        lvNV.setAdapter(adapter);

        lvNV.setOnItemClickListener((parent, view, position, id) -> {
            nhanVien = (NhanVien) lvNV.getItemAtPosition(position);
            pos=position;
            Intent i = new Intent(ListNhanVienActivity.this,ChiTietNhanVienActivity.class);
            i.putExtra("IdNV",nhanVien.getIdNV());
            i.putExtra("TenNV",nhanVien.getTenNV());
            i.putExtra("ChucVuNV",nhanVien.getChucVuNV());
            i.putExtra("SdtNV",nhanVien.getSdtNV());
            i.putExtra("DiaChiNV",nhanVien.getDiaChiNV());
            i.putExtra("NgaySinhNV",nhanVien.getNgaySinhNV());
            startActivity(i);
        });


        edtSearchNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

//    public void addNhanVien(View view) {
//        startActivity(new Intent(ListNhanVienActivity.this, AddNhanVienActivity.class));
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.nhanvien, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ListNhanVienActivity.this, MainActivity.class);
                startActivity(intent1);;
                break;
            case R.id.addNv:
                Intent intent = new Intent(ListNhanVienActivity.this, AddNhanVienActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}