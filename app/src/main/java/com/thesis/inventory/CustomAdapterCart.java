package com.thesis.inventory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterCart extends RecyclerView.Adapter<ViewHolderCart> {

    CartActivity cartActivity;
    List<Model> modelList;
    Context context;

    public CustomAdapterCart(CartActivity cartActivity, List<Model> modelList/*, Context context*/) {
        this.cartActivity = cartActivity;
        this.modelList = modelList;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.check_model_layout, viewGroup,false);

        ViewHolderCart viewHolder = new ViewHolderCart(itemView);

        viewHolder.setOnClickListener(new ViewHolderCart.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String id = modelList.get(position).getTimestamp();
                String title = modelList.get(position).getTitle();
                String des = modelList.get(position).getDescription();
                String pric = modelList.get(position).getPrice();
                String quan = modelList.get(position).getQuantity();
                String bar = modelList.get(position).getBarcode();

                Intent intent = new Intent(cartActivity,CartUpdateActivity.class);
                intent.putExtra("cId", id);
                intent.putExtra("cBar", bar);
                intent.putExtra("cTitle", title);
                intent.putExtra("cDes", des);
                intent.putExtra("cPrice", pric);
                intent.putExtra("cQuantity", quan);

                cartActivity.startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, final int position) {


            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart holder, int position) {

        holder.titlerv.setText(modelList.get(position).getTitle());
        holder.pricerv.setText("$"+modelList.get(position).getPrice());
        holder.quantityrv.setText(modelList.get(position).getQuantity()+" Unit(s)");
        holder.timestamp.setText(modelList.get(position).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
