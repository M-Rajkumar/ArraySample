package com.sample.program.sample_array_functions.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sample.program.sample_array_functions.Custom.MyTextViewRegular;
import com.sample.program.sample_array_functions.R;

import java.util.ArrayList;

class DrawerSingleAdapter extends RecyclerView.Adapter<DrawerSingleAdapter.ViewHolder>{
    private Context context;
    private ArrayList<DrawerItemDomain> list;

    private SidemenuInterface sidemenuInterface;

    DrawerSingleAdapter(ArrayList<DrawerItemDomain> list, Context context, SidemenuInterface sidemenuInterface) {
        this.context=context;
        this.list=list;
        this.sidemenuInterface=sidemenuInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerSingleAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawersingleitemlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.imv.setImageResource(list.get(position).getIcon());
        holder.text.setText(list.get(position).getName());

        if(position==list.size()-1){
            holder.line.setVisibility(View.GONE);
        }

        holder.sidemenusinglelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sidemenuInterface.clickedsideitem(list.get(position).getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        MyTextViewRegular text;
        View line;
        LinearLayout sidemenusinglelayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imv=itemView.findViewById(R.id.draweritemicom);
            text=itemView.findViewById(R.id.draweritemname);
            line=itemView.findViewById(R.id.linebelow);
            sidemenusinglelayout=itemView.findViewById(R.id.sidemenusinglelayout);
        }
    }
}
