package com.shopThuCung.duanshopthucung.Product.Product_NV;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopThuCung.duanshopthucung.DataBase.ProductDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.Product.ProductAdapter;
import com.shopThuCung.duanshopthucung.Product.addProductActivity;
import com.shopThuCung.duanshopthucung.R;


import java.util.ArrayList;
import java.util.List;

public class ListProductActivity_NV extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvProduct;
    ProductAdapterNV adapter;
    List<Product> productList;
    ProductDAO dao;
EditText searchPNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_nv);
    searchPNV = findViewById(R.id.searchProduct_nv);
        Toolbar toolbar = findViewById(R.id.toolbar_nv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productList = new ArrayList<>();
        dao = new ProductDAO(this);
        productList.addAll(dao.getALlProduct());

        // RecycleView
        rvProduct = findViewById(R.id.thuCung_rvThuCung_nv);
        rvProduct.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProduct.setLayoutManager(layoutManager);
        adapter = new ProductAdapterNV(productList);
        rvProduct.setAdapter(adapter);

        // ContextMenu
        registerForContextMenu(rvProduct);
//        rvProduct.addOnItemTouchListener(new);

        searchPNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(ListProductActivity_NV.this, MainActivity_NV.class);
                startActivity(intent1);;
                break;
            case R.id.themSanPham:
                Intent intent = new Intent(ListProductActivity_NV.this, addProductActivity_NV.class);
                startActivity(intent);
                break;

        }
        return true;
    }

    public void addThuCung(View view) {
    }
}