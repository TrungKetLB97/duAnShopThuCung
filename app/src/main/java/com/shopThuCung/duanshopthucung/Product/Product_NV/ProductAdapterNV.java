package com.shopThuCung.duanshopthucung.Product.Product_NV;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopThuCung.duanshopthucung.Bill.AddBillActivity;
import com.shopThuCung.duanshopthucung.Bill.Bill;
import com.shopThuCung.duanshopthucung.Bill.Bill_NV.AddBillActivity_NV;
import com.shopThuCung.duanshopthucung.DataBase.ProductDAO;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.Product.ProductAdapter;
import com.shopThuCung.duanshopthucung.Product.editProductActivity;
import com.shopThuCung.duanshopthucung.R;


import java.util.ArrayList;
import java.util.List;

public class ProductAdapterNV extends RecyclerView.Adapter<ProductAdapterNV.ViewHolder> {
    private List<Product> productList;
    private List<Product> productListSort;
    private OnItemClickListener listener;
    private ProductDAO dao;
    private Filter PFilter;
    private ItemClickListener itemClickListener;

    public ProductAdapterNV(List<Product> productList, ItemClickListener itemClickListener){
        this.productList = productList;
        this.itemClickListener = itemClickListener;
    }

    CustomFilter customFilter;
    @Override
    public int getItemCount() {
        return productList.size();
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
                ArrayList<Product> filter = new ArrayList<>();
                for (int i=0; i<productListSort.size();i++){
                    if(productListSort.get(i).getName().toUpperCase().contains(constraint)){
                        Product product1 = new Product(productListSort.get(i).getProductImage(),
                                productListSort.get(i).getCode(),
                                productListSort.get(i).getName(),
                                productListSort.get(i).getAge(),
                                productListSort.get(i).getWeight(),
                                productListSort.get(i).getGender(),
                                productListSort.get(i).getHealth(),
                                productListSort.get(i).getPrice())
                                ;
                        filter.add(product1);
                    }
                }
                results.count = filter.size();
                results.values=filter;
            }else {
                results.count = productListSort.size();
                results.values=productListSort;
            }
            return results;
        }



        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList = (ArrayList<Product>) results.values;
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface ItemClickListener{
        void onItemClick(Product product);
    }
    public ProductAdapterNV(List<Product> productList) {
        this.productList = productList;
        this.productListSort = productList;
    }

    @NonNull
    @Override
    public ProductAdapterNV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product_layout_nv, parent, false);
        dao = new ProductDAO(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterNV.ViewHolder holder, int position) {

        holder.imgProduct.setImageBitmap(
                getImage(productList.get(position).getProductImage()));
        holder.tvName.setText("Tên : " + productList.get(position).getName());
        holder.tvWeigh.setText("Cân nặng : " + productList.get(position).getWeight() + " kg");
        holder.tvProduct.setText("Loại : " + productList.get(position).getGender());
        holder.tvPrice.setText("Giá : " + productList.get(position).getPrice() + " VND");
        holder.imageButtonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AddBillActivity_NV.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDPet", productList.get(position).getCode());
                bundle.putByteArray("productImage", productList.get(position).getProductImage());
                bundle.putString("namePet", productList.get(position).getName());
                bundle.putInt("agePet", productList.get(position).getAge());
                bundle.putDouble("weightPet", productList.get(position).getWeight());
                bundle.putString("genderPet", productList.get(position).getGender());
                bundle.putString("healthPet", productList.get(position).getHealth());
                bundle.putDouble("price", productList.get(position).getPrice());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), editProductActivity_NV.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDPet", productList.get(position).getCode());
                bundle.putByteArray("productImage", productList.get(position).getProductImage());
                bundle.putString("namePet", productList.get(position).getName());
                bundle.putInt("agePet", productList.get(position).getAge());
                bundle.putDouble("weightPet", productList.get(position).getWeight());
                bundle.putString("genderPet", productList.get(position).getGender());
                bundle.putString("healthPet", productList.get(position).getHealth());
                bundle.putDouble("price", productList.get(position).getPrice());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvWeigh, tvProduct;
        ImageButton imageButtonArrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvWeigh = itemView.findViewById(R.id.tvWeight);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            imageButtonArrow = itemView.findViewById(R.id.imageButtonArrow);

        }

    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
