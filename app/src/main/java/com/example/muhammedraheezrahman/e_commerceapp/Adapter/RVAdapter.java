package com.example.muhammedraheezrahman.e_commerceapp.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muhammedraheezrahman.e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {

    TextView productNameTv,oldPriceTv,newPriceTv;


    public ViewHolder(View itemView) {
        super(itemView);
        productNameTv = (TextView) itemView.findViewById(R.id.productName);
        oldPriceTv = (TextView) itemView.findViewById(R.id.oldPrice);
        newPriceTv = (TextView) itemView.findViewById(R.id.newPrice);
    }
}

public class RVAdapter  extends RecyclerView.Adapter<ViewHolder> {
    public Context context;
    public List<String> listSTrings = new ArrayList<>();
    public RVAdapter(Context context,List<String> listSTrings) {
        this.context = context;
        this.listSTrings = listSTrings;

    }

    public RVAdapter() {

    }

    public void addToList(List<String> s){

        this.listSTrings.addAll(s);
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
        String s = listSTrings.get(position);
        holder.productNameTv.setText(String.valueOf(s));
        holder.oldPriceTv.setPaintFlags(holder.productNameTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return this.listSTrings.size();
    }
}
