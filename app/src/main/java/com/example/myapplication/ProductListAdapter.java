package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

    public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Products> ProductList;

    public ProductListAdapter(Context context, ArrayList<Products> ProductList) {
        this.context = context;
        this.ProductList = ProductList;
    }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context).load(ProductList.get(position).getImage()).into(holder.imageView);
            holder.TxtViewProductName.setText(ProductList.get(position).getName());
            holder.TxtProductPrice.setText(ProductList.get(position).getPrice());
        }


        @Override
        public long getItemId(int position) {return position;}

        @Override
        public int getItemCount() {return ProductList.size();}

        public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView TxtViewProductName, TxtProductPrice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                TxtViewProductName = (TextView) itemView.findViewById(R.id.TxtViewProductName);
                TxtProductPrice = (TextView) itemView.findViewById(R.id.TxtViewProductPrice);
                imageView = (ImageView) itemView.findViewById(R.id.ImgViewProductImage);
            }
        }


}
