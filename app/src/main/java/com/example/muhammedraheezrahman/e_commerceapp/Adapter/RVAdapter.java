package com.example.muhammedraheezrahman.e_commerceapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muhammedraheezrahman.e_commerceapp.Model.ProductModel;
import com.example.muhammedraheezrahman.e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {

    TextView productNameTv,oldPriceTv,newPriceTv;

    ImageView productThumbanil;

    public ViewHolder(View itemView) {
        super(itemView);
        productNameTv = (TextView) itemView.findViewById(R.id.productName);
        oldPriceTv = (TextView) itemView.findViewById(R.id.oldPrice);
        newPriceTv = (TextView) itemView.findViewById(R.id.newPrice);
        productThumbanil = (ImageView) itemView.findViewById(R.id.productImage);
    }
}

public class RVAdapter  extends RecyclerView.Adapter<ViewHolder> {
    public Context context;
//    public List<String> listSTrings = new ArrayList<>();
    public List<ProductModel> listProduct = new ArrayList<>();

    public RVAdapter(Context context) {
        this.context = context;
    }

    public RVAdapter(Context context, List<ProductModel> listProducts) {
        this.context = context;
        this.listProduct = listProducts;

    }

    public RVAdapter() {

    }

//    public void addToList(List<String> s){
//
//        this.listSTrings.addAll(s);
//        notifyDataSetChanged();
//    }
    public void addToProductList(List<ProductModel> list){
        this.listProduct.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel pm;
        pm = listProduct.get(position);

        holder.productNameTv.setText(String.valueOf(pm.getProductName()));
        holder.oldPriceTv.setText(String.valueOf(pm.getOldPrice()));
        holder.oldPriceTv.setPaintFlags(holder.productNameTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.newPriceTv.setText(String.valueOf(pm.getNewPrice()));
        Glide.with(context).load(pm.getThumbNail()).into(holder.productThumbanil);

    }

    @Override
    public int getItemCount() {
        return this.listProduct.size();
    }
}
