package com.shopThuCung.duanshopthucung.Product.Product_NV;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shopThuCung.duanshopthucung.DataBase.ProductDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class addProductActivity_NV extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgProduct;
    private EditText add_Product_edtCode,
            add_Product_edtName,
            add_Product_edtTuoi,
            add_Product_edtCanNang,
            add_Product_edtGioiTinh
            , add_Product_edtSucKhoe,
            add_Product_edtGiatien;
    private ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_nv);


        toolbar = findViewById(R.id.toolbar_edit_product_nv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgProduct = findViewById(R.id.add_Product_Picture);
        add_Product_edtCode = findViewById(R.id.add_Product_edtCodeNV);
        add_Product_edtName = findViewById(R.id.add_Product_edtNameNV);
        add_Product_edtTuoi = findViewById(R.id.add_Product_edtTuoiNV);
        add_Product_edtCanNang = findViewById(R.id.add_Product_edtCanNangNV);
        add_Product_edtGioiTinh = findViewById(R.id.add_Product_edtGioiTinhNV);
        add_Product_edtSucKhoe = findViewById(R.id.add_Product_edtSucKhoeNV);
        add_Product_edtGiatien = findViewById(R.id.add_Product_edtGiaNV);
    }

    public void postProductPicture1(View view) {
        Dialog dialog = new Dialog(addProductActivity_NV.this);
        dialog.setContentView(R.layout.themanh);
        Button button1 = dialog.findViewById(R.id.btnCamera);
        Button button2 = dialog.findViewById(R.id.btnStorage);
        dialog.show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(addProductActivity_NV.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 888);
                } else {
                    ActivityCompat.requestPermissions(addProductActivity_NV.this, new String[]{Manifest.permission.CAMERA}, 999);
                }
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1);
                dialog.dismiss();
            }
        });
    }


    public void postProductNV(View view) {
        dao = new ProductDAO(this);

        try {
            // Get image from ImageView
            imgProduct.invalidate();
            BitmapDrawable drawable = (BitmapDrawable) imgProduct.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            // convert bitmap to byteArray  --> imageView
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
//            if (bitmap != null && !bitmap.isRecycled()) {
//                bitmap.recycle();
//                bitmap = null;
//            }
            // get data from EditText
            String productCode = add_Product_edtCode.getText().toString().trim();
            String productName = add_Product_edtName.getText().toString().trim();
            int productAge = Integer.parseInt(add_Product_edtTuoi.getText().toString().trim());
            double productWeight= Double.parseDouble(add_Product_edtCanNang.getText().toString().trim());
            String productGender = add_Product_edtGioiTinh.getText().toString().trim();
            String productHealth = add_Product_edtSucKhoe.getText().toString().trim();
            double productPrice = Double.parseDouble(add_Product_edtGiatien.getText().toString().trim());

            Product product = new Product(byteArray, productCode, productName, productAge, productWeight, productGender, productHealth, productPrice);
//                      dao.insertProduct(product);
            if (add_Product_edtName.getText().toString().isEmpty()||
                    add_Product_edtGioiTinh.getText().toString().isEmpty()||
                    add_Product_edtGiatien.getText().toString().isEmpty()||
                    add_Product_edtCanNang.getText().toString().isEmpty()){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_that_bai);
                Button btn = dialog.findViewById(R.id.btnThemThatBai);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }else {
                if (dao.insertProduct(product) > 0) {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.them_thanh_cong);
                    Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                    dialog.show();
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(addProductActivity_NV.this, ListProductActivity_NV.class));
                            dialog.dismiss();

                        }
                    });
                } else {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.them_that_bai);
                    Button btn = dialog.findViewById(R.id.btnThemThatBai);
                    dialog.show();
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgProduct.setImageBitmap(bitmap);
        }
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgProduct.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 888);
        } else {
            Toast.makeText(this, "Can't open camera!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent1 = new Intent(addProductActivity_NV.this, MainActivity_NV.class); // close this activity and return to preview activity (if there is any)
            startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }
}