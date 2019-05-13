package com.sample.program.sample_array_functions;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    Activity mActivity;

    ArrayList txtone_array, txttwo_array;

    ArrayList<tutorial_domain> tutorial_domains;

    tutorial_domain tutorial_model;


    RecyclerView recycler;
    TutorialAdapter mTutorialadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        mActivity = this;
        initview();

        recyclerinit();
        addDatas();
    }


    private void initview() {

        recycler = findViewById(R.id.recycler);

    }

    private void recyclerinit() {
        txtone_array = new ArrayList();
        txttwo_array = new ArrayList();


        recycler.setHasFixedSize(true);

        //LayoutManager ListView
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));

        /*//LayoutManager Gridview
        recycler.setLayoutManager(new GridLayoutManager(mActivity, 4));*/


        /*//Using model calling constructor
        mTutorialadapter = new TutorialAdapter(tutorial_domains, mActivity);*/


        /*//Using domains example
        mTutorialadapter = new TutorialAdapter(tutorial_domains, mActivity);*/

        //Using sample array example
        mTutorialadapter = new TutorialAdapter(txtone_array, txttwo_array, mActivity);

        recycler.setAdapter(mTutorialadapter);

    }

    private void addDatas() {

/*===========================================================

        Single Arraylist without using model sample

===========================================================*/


        txtone_array.add("arun");
        txtone_array.add("web");
        txtone_array.add("developer");

        txttwo_array.add("chinna");
        txttwo_array.add("node");
        txttwo_array.add("developer_lead");

        txtone_array.add("sdfs");
        txtone_array.add("wesddfsb");
        txtone_array.add("devsgfeloper");

        txttwo_array.add("safdasdf");
        txttwo_array.add("trte");
        txttwo_array.add("jsswertewtr");





/*===========================================================

        Arraylist using model sample

===========================================================*/

       /* tutorial_domains=new ArrayList<>();

        tutorial_model=new tutorial_domain("arun","dev","#04cfc7");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("chinna","node","#04cfc7");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("anand","android","#04cfc7");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("raj","j-and","#04cfc7");
        tutorial_domains.add(tutorial_model);


        tutorial_model=new tutorial_domain("arun","dev","#FFF9B102");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("chinna","node","#FFF9B102");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("anand","android","#FFF9B102");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("raj","j-and","#FFF9B102");
        tutorial_domains.add(tutorial_model);



        tutorial_model=new tutorial_domain("arun","dev","#389895");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("chinna","node","#389895");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("anand","android","#389895");
        tutorial_domains.add(tutorial_model);

        tutorial_model=new tutorial_domain("raj","j-and","#389895");
        tutorial_domains.add(tutorial_model);*/


    }

    @Override
    public void onClick(View v) {

    }
}
