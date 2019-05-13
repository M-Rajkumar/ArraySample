package com.sample.program.sample_array_functions;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sample.program.sample_array_functions.Custom.MyTextViewBold;
import com.sample.program.sample_array_functions.Custom.MyTextViewMedium;

import java.util.ArrayList;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.Viewholder> {
    ArrayList txtone_array,txttwo_array;
    ArrayList<tutorial_domain>tutorial_domains;


    private Context context;

    // ArrayList using model view constructor calling method

  /*  public TutorialAdapter(ArrayList<tutorial_domain>tdomain, Context context) {
       this.tutorial_domains=tdomain;
        this.context = context;
    }*/


    // ArrayList constructor calling method

    public TutorialAdapter(ArrayList txtone_array, ArrayList txttwo_array, Context context) {
        this.txtone_array = txtone_array;
        this.txttwo_array = txttwo_array;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       return new TutorialAdapter. Viewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.widget_layout_temp,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {

        //Using array sample

        viewholder.one.setText((String) txtone_array.get(i));
        viewholder.two.setText((String) txttwo_array.get(i));

      /* //Using arraylist sample

       viewholder.one.setText(tutorial_domains.get(i).getFirstname());
       viewholder.two.setText(tutorial_domains.get(i).getSecondName());*/
      if(i%2==0){
          viewholder.ll_test.setBackgroundColor(Color.parseColor("#389895"));
      }else {
          viewholder.ll_test.setBackgroundColor(Color.parseColor("#fc5b5b"));
      }



       /* viewholder.one.setTextColor(Color.parseColor(tutorial_domains.get(i).getColorcode()));
        viewholder.two.setTextColor(Color.parseColor(tutorial_domains.get(i).getColorcode()));*/



        //viewholder.ll_test.setBackgroundColor(Color.parseColor(tutorial_domains.get(i).getColorcode()));





    }

    @Override
    public int getItemCount() {
       if(txtone_array!=null){
           return txtone_array.size();
       }else {
           return 0;
       }
    }

    class Viewholder extends RecyclerView.ViewHolder {

        MyTextViewBold one;
        MyTextViewMedium two;
        LinearLayout ll_test;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            one=itemView.findViewById(R.id.txt_one);
            two=itemView.findViewById(R.id.txt_two);
            ll_test=itemView.findViewById(R.id.ll_test);
        }
    }
}
