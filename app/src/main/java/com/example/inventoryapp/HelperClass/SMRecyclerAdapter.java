package com.example.inventoryapp.HelperClass;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.R;

import java.util.ArrayList;

public class SMRecyclerAdapter extends RecyclerView.Adapter<SMRecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<StockModel> stockModels;

    public SMRecyclerAdapter(Context context, ArrayList<StockModel> stockModels){
        this.context = context;
        this.stockModels = stockModels;
    }

    @NonNull
    @Override
    public SMRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where we inflate the layout (u know, like implement your recycle_item_inout.xml)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_item_inout, parent, false);

        return new SMRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMRecyclerAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the recycler_view layout file
        // based on the position of the recylcer view


        holder.tvStockinfo.setText(stockModels.get(position).getInfostock());
        holder.tvJmlitem.setText(stockModels.get(position).getJmlitem());
        holder.tvItemname.setText(stockModels.get(position).getItemname());
        holder.imageView.setImageResource(stockModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        // how many items u have in total
        return stockModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from our recycler_view layout file
        //like the onCreate Method in MainActivity

        ImageView imageView;
        TextView tvStockinfo, tvJmlitem, tvItemname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IVin);
            tvStockinfo = itemView.findViewById(R.id.TVin);
            tvJmlitem = itemView.findViewById(R.id.TVjmlitem);
            tvItemname = itemView.findViewById(R.id.item);
        }
    }
}
