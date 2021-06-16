package com.shopThuCung.duanshopthucung.Bill.Bill_NV;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.Bill.AddBillActivity;
import com.shopThuCung.duanshopthucung.Bill.Bill;
import com.shopThuCung.duanshopthucung.Bill.ListBillActivity;
import com.shopThuCung.duanshopthucung.Bill.ProductOnBillAdapter;
import com.shopThuCung.duanshopthucung.Customer.AddCustomerActivity;
import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.Customer.Customer_NV.AddCustomerActivity_NV;
import com.shopThuCung.duanshopthucung.DataBase.BillDAO;
import com.shopThuCung.duanshopthucung.DataBase.CustomerDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.Product.ListProductActivity;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.Product.Product_NV.ListProductActivity_NV;
import com.shopThuCung.duanshopthucung.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class AddBillActivity_NV extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText editTextNgayTaoDon;
    private Spinner spinnerCustomer;
    private List<Customer> customersList;
    public static List<Product> productList = new ArrayList<>();
    private List<String> customerNameList;
    private Toolbar toolbar;
    private RecyclerView rvSanPhamDaChon;
    ProductOnBillAdapterNV productOnBillAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    CustomerDAO customerDAO;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_nv);

        Log.e("TAG", "onCreate");
        toolbar = findViewById(R.id.toolbaraddbill_nv);
        setSupportActionBar(toolbar);

        tvTongTien = findViewById(R.id.tvTongTien);
        editTextNgayTaoDon = findViewById(R.id.edNgayTaoDOn);
        spinnerCustomer = findViewById(R.id.spinnerCustomer);
        rvSanPhamDaChon = findViewById(R.id.rvSanPhamDaChon);

        customersList = new ArrayList();
        customerNameList = new ArrayList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Get customer from spinner
        getCustomer();

        // RecycleView
        rvSanPhamDaChon = findViewById(R.id.rvSanPhamDaChon);
        rvSanPhamDaChon.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvSanPhamDaChon.setLayoutManager(layoutManager);
        getProduct();
        productOnBillAdapter = new ProductOnBillAdapterNV(productList);
        rvSanPhamDaChon.setAdapter(productOnBillAdapter);
    }

    public static TextView tvTongTien;

    public static void update_counter(String value) {
        try {
            tvTongTien.setText(value + "VND");
        } catch (Exception ex) {
            Log.d("Exception", "Exception of type" + ex.getMessage());
        }
    }

    public void addHoaDon(View view) {
        if (sumOfProductPrice() <= 0 || editTextNgayTaoDon.getText().toString().isEmpty()) {
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
        } else {
            billDAO = new BillDAO(this);
            Bill bill = new Bill();
            bill.setMaHoaDon("HD" + new Random().nextInt(9999));
            bill.setTenKhachHang(spinnerCustomer.getSelectedItem().toString());
            bill.setTongTien(Double.parseDouble(String.valueOf(sumOfProductPrice())));
            bill.setDate(editTextNgayTaoDon.getText().toString());
//        billDAO.insertBill(bill);
//        Toast.makeText(this, "Successfully!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(AddBillActivity.this,ListBillActivity.class));
            if (billDAO.insertBill(bill) > 0) {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.them_thanh_cong);
                Button btn = dialog.findViewById(R.id.btnThemThanhCong);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(AddBillActivity_NV.this, ListBillActivity_NV.class));
                        dialog.dismiss();
                        restart();
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
    }


    public void getCustomer() {
        customerDAO = new CustomerDAO(this);
        customersList.addAll(customerDAO.getAllKHString());
        for (Customer customer : customersList) {
            customerNameList.add(customer.getTenKH());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, customerNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomer.setAdapter(adapter);

    }

    public void getProduct() {
        Log.e("TAG", "getProduct");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Product product = new Product();
//            product.setProductImage(bundle.getByteArray("image"));
            product.setProductImage(bundle.getByteArray("productImage"));
            product.setCode(bundle.getString("IDPet"));
            product.setName(bundle.getString("namePet"));
            product.setAge(bundle.getInt("agePet"));
            product.setWeight( bundle.getDouble("weightPet"));
            product.setGender(bundle.getString("genderPet"));
            product.setHealth(bundle.getString("healthPet"));
            product.setPrice(bundle.getDouble("price"));
            productList.add(product);
            tvTongTien.setText(String.valueOf(sumOfProductPrice()));
        }
    }

    public double sumOfProductPrice() {
        double totalMoney = 0;
        for (Product product : productList) {
            totalMoney += product.getPrice();
            ;
        }
        return totalMoney;
    }

    public void ngayTaoDon(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {
        editTextNgayTaoDon.setText(sdf.format(calendar.getTime()));
    }

    public void taoHoaDon(View view) {
    }

    public void themSPMua(View view) {
        Intent intent = new Intent(AddBillActivity_NV.this, ListProductActivity_NV.class);
        startActivity(intent);
    }

    public void themKH(View view) {
        Intent intent = new Intent(AddBillActivity_NV.this, AddCustomerActivity_NV.class);
        startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            restart();
            Intent intent = new Intent(AddBillActivity_NV.this, MainActivity_NV.class);
            startActivity(intent);// close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);

    }

    public void restart() {
        productList.clear();
    }
}