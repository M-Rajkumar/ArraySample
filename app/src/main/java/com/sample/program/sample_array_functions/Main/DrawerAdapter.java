package com.sample.program.sample_array_functions.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.program.sample_array_functions.R;

import java.util.ArrayList;

class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DrawerGroupDomain> list;

    private SidemenuInterface sidemenuInterface;

    DrawerAdapter(Context context, ArrayList<DrawerGroupDomain> list,
                  SidemenuInterface sidemenuInterface) {
        this.context=context;
        this.list=list;
        this.sidemenuInterface=sidemenuInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.draweritemlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.recycler.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        holder.recycler.setLayoutManager(manager);

        DrawerSingleAdapter adapter=new DrawerSingleAdapter(list.get(position).getList(),context,sidemenuInterface);

        holder.recycler.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }else
            return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycler;
        ViewHolder(View itemView) {
            super(itemView);
            recycler=itemView.findViewById(R.id.drawer_item_recycler);
        }
    }
}
