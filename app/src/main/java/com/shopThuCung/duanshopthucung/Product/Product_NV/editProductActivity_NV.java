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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.shopThuCung.duanshopthucung.DataBase.ProductDAO;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.Product.editProductActivity;
import com.shopThuCung.duanshopthucung.R;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class editProductActivity_NV extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgProduct;
    EditText edtProductName, edtProductAge, edtProductWeight, edtProductGender, edtProductHealth, edtGia ;
    ProductDAO dao;
    byte[] productImage;
    String  productName, productGender, productHealth, productCode;
    int productAge;
    double productPrice, productWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_nv);

        // Toolbar
        toolbar = findViewById(R.id.toolbar_edit_product_nv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgProduct = findViewById(R.id.editProduct_Picture);
        edtProductName = findViewById(R.id.editProduct_edtName);
        edtProductAge = findViewById(R.id.editProduct_edtTuoi);
        edtProductWeight = findViewById(R.id.editProduct_edtCanNang);
        edtProductGender = findViewById(R.id.editProduct_edtGioiTinh);
        edtProductHealth = findViewById(R.id.editProduct_edtSucKhoe);
        edtGia = findViewById(R.id.editProduct_edtGia);

        Bundle bundle = getIntent().getExtras();
        productCode = bundle.getString("IDPet");
        productImage = bundle.getByteArray("productImage");
        productName = bundle.getString("namePet");
        productAge = bundle.getInt("agePet");
        productWeight = bundle.getDouble("weightPet");
        productGender = bundle.getString("genderPet");
        productHealth = bundle.getString("healthPet");
        productPrice = bundle.getDouble("price");


        imgProduct.setImageBitmap(getImage(productImage));
        edtProductName.setText(productName);
        edtProductAge.setText(String.valueOf(productAge));
        edtProductWeight.setText(String.valueOf(productWeight));
        edtProductGender.setText(productGender);
        edtProductHealth.setText(productHealth);
        edtGia.setText(String.valueOf(productPrice));
    }


    public void editProduct(View view) {
        dao = new ProductDAO(this);
        byte[] productImage = convertImageToByteArray();
        String productName = edtProductName.getText().toString().trim();
        double productPrice = Double.parseDouble(edtGia.getText().toString().trim());
        double productWeight =Double.parseDouble( edtProductWeight.getText().toString().trim());
        Product product = new Product(productImage, productCode, productName, productAge,  productWeight, productGender,productHealth,productPrice);
        if (edtProductName.getText().length()==0 || edtGia.getText().length()==0
                ||  edtProductWeight.getText().length()==0){
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
            if (dao.updateProduct(product) > 0){
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_thanh_cong);
                Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(editProductActivity_NV.this, ListProductActivity_NV.class));
                        dialog.dismiss();
                    }
                });
            }else {
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
    }

    public void addProductPicture1(View view) {
        Dialog dialog = new Dialog(editProductActivity_NV.this);
        dialog.setContentView(R.layout.themanh);
        Button button1 = dialog.findViewById(R.id.btnCamera);
        Button button2 = dialog.findViewById(R.id.btnStorage);
        dialog.show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(editProductActivity_NV.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 888);
                } else {
                    ActivityCompat.requestPermissions(editProductActivity_NV.this, new String[]{Manifest.permission.CAMERA}, 999);
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

    public byte[] convertImageToByteArray() {
        // Get image from ImageView
        imgProduct.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) imgProduct.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        // bitmap.recycle();
        return byteArray;
    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.deleteProduct:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.xoa);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                Button btnDongY = dialog.findViewById(R.id.btnDongY);
                dialog.show();
                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao = new ProductDAO(editProductActivity_NV.this);
                        if (dao.delProduct(productCode) <= 0){
                            startActivity(new Intent(editProductActivity_NV.this, ListProductActivity_NV.class));
                        }else {
                            dialog.setContentView(R.layout.xoa_thanh_cong);
                            Button btnOK = dialog.findViewById(R.id.ok);
                            dialog.show();
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(editProductActivity_NV.this, ListProductActivity_NV.class));
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
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

    public void delProduct(View view) {

    }
}