package com.shopThuCung.duanshopthucung;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.shopThuCung.duanshopthucung.Bill.ListBillActivity;
import com.shopThuCung.duanshopthucung.Bill.ListBillActivity;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.Login.LoginActivity;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Revenue.RevenueActivity;
import com.shopThuCung.duanshopthucung.nhanVien.ListNhanVienActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void thuCung(View view) {
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(intent);
    }

    public void nhanVien(View view) {
        Intent intent = new Intent(MainActivity.this, ListNhanVienActivity.class);
        startActivity(intent);
    }

    public void khachHang(View view) {
        Intent intent = new Intent(MainActivity.this, ListCustomerActivity.class);
        startActivity(intent);
    }

    public void hoaDon(View view) {
        Intent intent = new Intent(MainActivity.this, ListBillActivity.class);
        startActivity(intent);
    }

    public void thonhKe(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.muc_tieu_layout);
        EditText editText = dialog.findViewById(R.id.tvMucTieu);
        Button button = dialog.findViewById(R.id.btnTT);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RevenueActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("MT", Double.parseDouble(editText.getText().toString()));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        dialog.show();
    }

    public void logout(View view) {
        Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}