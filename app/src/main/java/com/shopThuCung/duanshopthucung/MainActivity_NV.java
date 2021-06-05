package com.shopThuCung.duanshopthucung;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shopThuCung.duanshopthucung.Bill.ListBillActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.ListCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.Customer.ListCustomerActivity;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Revenue.RevenueActivity;
import com.shopThuCung.duanshopthucung.nhanVien.ListNhanVienActivity;

public class MainActivity_NV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nv);
    }

    public void thuCung_NV(View view) {
        Intent intent = new Intent(MainActivity_NV.this, ListProductActivity.class);
        startActivity(intent);
    }

    public void customer_NV(View view) {
        Intent intent = new Intent(MainActivity_NV.this, ListCustomerActivity_NV.class);
        startActivity(intent);
    }

    public void hoaDon_NV(View view) {
        Intent intent = new Intent(MainActivity_NV.this, ListBillActivity.class);
        startActivity(intent);
    }

    public void thongKe_NV(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.muc_tieu_layout);
        EditText editText = dialog.findViewById(R.id.tvMucTieu);
        Button button = dialog.findViewById(R.id.btnTT);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_NV.this, RevenueActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("MT", Double.parseDouble(editText.getText().toString()));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        dialog.show();
    }
}