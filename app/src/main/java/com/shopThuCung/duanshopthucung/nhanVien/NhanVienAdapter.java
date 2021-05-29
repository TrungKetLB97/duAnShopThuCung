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
        TextView tvID;
        TextView tvName;
        TextView tvChucVu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        NhanVien nv = (NhanVien) getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.nhan_vien_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvID = convertView.findViewById(R.id.nhanVien_itemNhanVien_tvId);
            viewHolder.tvName = convertView.findViewById(R.id.nhanVien_itemNhanVien_tvName);
            viewHolder.tvChucVu = convertView.findViewById(R.id.nhanVien_itemNhanVien_tvChucVu);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvID.setText(nv.getIdNV());
        viewHolder.tvName.setText(nv.getTenNV());
        viewHolder.tvChucVu.setText(nv.getChucVuNV());
        return convertView;
    }
}