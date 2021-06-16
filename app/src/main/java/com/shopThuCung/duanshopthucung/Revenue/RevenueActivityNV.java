package com.shopThuCung.duanshopthucung.Revenue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.shopThuCung.duanshopthucung.DataBase.BillDAO;
import com.shopThuCung.duanshopthucung.MainActivity;
import com.shopThuCung.duanshopthucung.MainActivity_NV;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;

public class RevenueActivityNV extends AppCompatActivity {
    private Toolbar toolbar;
    private PieChart chartDayRevenue,chartMonthRevenue,chartYearRevenue;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_nv);

        toolbar = findViewById(R.id.toolbardoanhthuNV);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        billDAO = new BillDAO(this);
        double doanhThu = billDAO.getDoanhThuTheoNgay();
        double doanhthu1 = billDAO.getDoanhThuTheoThang();
        double doanhThu2 = billDAO.getDoanhThuTheoNam();

        // piechart revenue

        chartDayRevenue = findViewById(R.id.chartDayRevenueNV);
        chartMonthRevenue = findViewById(R.id.chartMonthRevenueNV);
        chartYearRevenue = findViewById(R.id.chartYearRevenueNV);

        //data ngay
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry((float) doanhThu, "Thu Về"));
        visitors.add(new PieEntry((float) bundle.getDouble("MT"),"Mục Tiêu"));
        PieDataSet pieDataSet = new PieDataSet(visitors,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        chartDayRevenue.setData(pieData);
        chartDayRevenue.getDescription().setEnabled(false);
        chartDayRevenue.animate();

        // data thang
        ArrayList<PieEntry> visitors1 = new ArrayList<>();
        visitors1.add(new PieEntry((float) doanhthu1, "Thu Về"));
        visitors1.add(new PieEntry((float) bundle.getDouble("MT")*30,"Mục Tiêu"));
        PieDataSet pieDataSet1 = new PieDataSet(visitors1,"");
        pieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet1.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet1.setValueTextSize(16f);
        PieData pieData1 = new PieData(pieDataSet1);
        chartMonthRevenue.setData(pieData1);
        chartMonthRevenue.getDescription().setEnabled(false);
        chartMonthRevenue.animate();

        //data nam
        ArrayList<PieEntry> visitors2 = new ArrayList<>();
        visitors2.add(new PieEntry((float) doanhThu2, "Thu Về"));
        visitors2.add(new PieEntry((float) bundle.getDouble("MT")*30*12,"Mục Tiêu"));
        PieDataSet pieDataSet2 = new PieDataSet(visitors2,"");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextColor(Color.parseColor("#000000"));
        pieDataSet2.setValueTextSize(16f);
        PieData pieData2 = new PieData(pieDataSet2);
        chartYearRevenue.setData(pieData2);
        chartYearRevenue.getDescription().setEnabled(false);
        chartYearRevenue.animate();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(RevenueActivityNV.this , MainActivity_NV.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}