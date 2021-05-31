package com.shopThuCung.duanshopthucung.nhanVien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.R;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private List<NhanVien> nhanVienList;
    private Context context;

    public NhanVienAdapter(List<NhanVien> nhanVienList, Context context) {
        this.nhanVienList = nhanVienList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView tvName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        NhanVien nv = (NhanVien) getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_nhan_vien_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.nhanVien_listNhanVien_tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(nv.getTenNV());
        return convertView;
    }
}