package com.shopThuCung.duanshopthucung.Bill;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopThuCung.duanshopthucung.DataBase.ProductDAO;
import com.shopThuCung.duanshopthucung.Product.Product;
import com.shopThuCung.duanshopthucung.R;

import java.util.List;

public class ProductOnBillAdapter extends RecyclerView.Adapter<ProductOnBillAdapter.ViewHolder> {
    List<Product> productList;
    ProductDAO productDAO;

    public ProductOnBillAdapter(List<Product> productList) {
        this.productList = productList;
    }



    @NonNull
    @Override
    public ProductOnBillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_san_pham, parent, false);
        productDAO = new ProductDAO(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOnBillAdapter.ViewHolder holder, int position) {
        holder.imgProduct.setImageBitmap(
                getImage(productList.get(position).getProductImage()));
        holder.tvName.setText("Tên : " + productList.get(position).getName());
        holder.tvSize.setText("Cân nặng : " + String.valueOf(productList.get(position).getWeight()));
        holder.tvGender.setText("Loại : " + productList.get(position).getGender());
        holder.tvPrice.setText("Giá tiền :" + String.valueOf(productList.get(position).getPrice()));
        holder.imageButtonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product nd = productList.get(position);
                productList.remove(nd);//xoa trong list
                double price = sumOfProductPrice();
                AddBillActivity.update_counter(String.valueOf(price));
                notifyDataSetChanged();//cap nhat list
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvSize, tvGender;
        ImageButton imageButtonArrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvGia);
            tvGender=itemView.findViewById(R.id.tvGender);
            tvSize = itemView.findViewById(R.id.tvCannang);
            imageButtonArrow = itemView.findViewById(R.id.imageButtonArrow1);
        }
    }
    public double sumOfProductPrice() {
        double totalMoney = 0;
        for (Product product: productList) {
            totalMoney += product.getPrice();;
        }
        return totalMoney;
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public void changeSataset(List<Product> ls){
        this.productList = ls;
        notifyDataSetChanged();
    }
}
