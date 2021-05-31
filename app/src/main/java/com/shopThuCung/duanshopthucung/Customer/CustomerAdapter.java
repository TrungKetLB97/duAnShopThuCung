package com.shopThuCung.duanshopthucung.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.R;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    private List<Customer> customerList;
    private Context context;

    public CustomerAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tvName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Customer customer = (Customer) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_customer_layout,null,false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.khachHang_listCustimer_tvNameKH);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(customer.getTenKH());
        return convertView;
    }
}