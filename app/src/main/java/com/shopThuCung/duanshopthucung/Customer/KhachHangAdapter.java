package com.shopThuCung.duanshopthucung.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.R;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private List<KhachHang> khachHangList;
    private Context context;

    public KhachHangAdapter(List<KhachHang> khachHangList, Context context) {
        this.khachHangList = khachHangList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return khachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvDiaChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        KhachHang khachHang = (KhachHang) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.khach_hang_item,null,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = convertView.findViewById(R.id.khachHang_itemKhachHang_tvId);
            viewHolder.tvName = convertView.findViewById(R.id.khachHang_itemKhachHang_tvName);
            viewHolder.tvDiaChi = convertView.findViewById(R.id.khachHang_itemKhachHang_tvDiaChi);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvId.setText(khachHang.getIdKH());
        viewHolder.tvName.setText(khachHang.getTenKH());
        viewHolder.tvDiaChi.setText(khachHang.getDiaChiKH());
        return convertView;
    }
}