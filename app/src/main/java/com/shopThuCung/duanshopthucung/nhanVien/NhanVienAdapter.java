package com.shopThuCung.duanshopthucung.nhanVien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.Customer.Customer;
import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private List<NhanVien> nhanVienList;
    private List<NhanVien> nhanVienListSort;
    private Context context;
    CustomFilter customFilter;

    public NhanVienAdapter(List<NhanVien> nhanVienList, Context context) {
        this.nhanVienList = nhanVienList;
        this.context = context;
        this.nhanVienListSort = nhanVienList;
    }


    public Filter getFilter() {
        if (customFilter == null){
            customFilter = new CustomFilter();
        }
        return customFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint!=null || constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<NhanVien> filter = new ArrayList<>();
                for (int i=0; i < nhanVienListSort.size(); i++){
                    if(nhanVienListSort.get(i).getTenNV().toUpperCase().contains(constraint)){
                        NhanVien nhanVien1 = new NhanVien(
                                nhanVienListSort.get(i).getIdNV(),
                                nhanVienListSort.get(i).getTenNV(),
                                nhanVienListSort.get(i).getChucVuNV(),
                                nhanVienListSort.get(i).getSdtNV(),
                                nhanVienListSort.get(i).getDiaChiNV(),
                                nhanVienListSort.get(i).getNgaySinhNV());
                        filter.add(nhanVien1);
                    }
                }
                results.count = filter.size();
                results.values=filter;
            }else {
                results.count = nhanVienListSort.size();
                results.values = nhanVienListSort;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            nhanVienList = (ArrayList<NhanVien>) results.values;
            notifyDataSetChanged();
        }
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