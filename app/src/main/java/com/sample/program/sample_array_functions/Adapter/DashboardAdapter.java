package com.sample.program.sample_array_functions.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewholder> {

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        public MyViewholder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
