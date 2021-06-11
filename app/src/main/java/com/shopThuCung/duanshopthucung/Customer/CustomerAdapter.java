package com.shopThuCung.duanshopthucung.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Filter;

import com.shopThuCung.duanshopthucung.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    private List<Customer> customerList;
    private List<Customer> customerListSort;
    private final Context context;
    CustomFilter customFilter;

    public CustomerAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customerList.size();
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
                ArrayList<Customer> filter = new ArrayList<>();
                for (int i=0; i<customerListSort.size();i++){
                    if(customerListSort.get(i).getTenKH().toUpperCase().contains(constraint)){
                        Customer customer1 = new Customer(customerListSort.get(i).getIdKH(),
                                customerListSort.get(i).getTenKH(),
                                customerListSort.get(i).getSdtKH(),
                                customerListSort.get(i).getDiaChiKH());
                        filter.add(customer1);
                    }
                }
                results.count = filter.size();
                results.values=filter;
            }else {
                results.count = customerListSort.size();
                results.values=customerListSort;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customerList = (ArrayList<Customer>) results.values;
            notifyDataSetChanged();
        }
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
        ViewHolder viewHolder;
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