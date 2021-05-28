package com.shopThuCung.duanshopthucung.nhanVien;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder> {
    List<NhanVien> nhanVienList;

    public NhanVienAdapter(List<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    @NonNull
    @Override
    public NhanVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class NhanVienHolder extends RecyclerView.ViewHolder {
        public NhanVienHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
