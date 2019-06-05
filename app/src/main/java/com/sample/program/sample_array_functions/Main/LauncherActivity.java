package com.sample.program.sample_array_functions.Main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sample.program.sample_array_functions.Activity.BaseActivity;
import com.sample.program.sample_array_functions.R;

import java.util.ArrayList;

public class LauncherActivity extends BaseActivity implements View.OnClickListener,SidemenuInterface {

    DrawerLayout drawer;
    Toolbar toolbar;
    RecyclerView drawerrecycler;
    ArrayList<DrawerGroupDomain> grouplist;
    ImageView navicon, iv_back;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initview();


        clicklisteners();
        drawerfuntion("", "", "");




    }

    private void initview() {
        setSupportActionBar(toolbar);

         fab = findViewById(R.id.fab);
        navicon = findViewById(R.id.navicon);
        drawer = findViewById(R.id.drawer_layout);
        drawerrecycler = findViewById(R.id.drawer_recycler);
    }

    private void clicklisteners() {
        navicon.setOnClickListener(this);
        fab.setOnClickListener(this);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }




    @SuppressLint("SetTextI18n")
    private void drawerfuntion(String photourl, String firstName, String lastName) {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setDrawerRecycler();


        View my = findViewById(R.id.my);



        String imgletr;
        String firstletter;
        if (!firstName.equals("")) {
            firstletter = String.valueOf(firstName.charAt(0));
        } else {
            firstletter = "";
        }
        if (lastName != null && !lastName.equals("")) {
            String lastletter = String.valueOf(lastName.charAt(0));
            imgletr = firstletter + lastletter;
        } else {
            imgletr = firstletter;
        }




    }

    private void setDrawerRecycler() {
        drawerrecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        drawerrecycler.setLayoutManager(manager);
        groupsetfuntion();

    }

    private void groupsetfuntion() {

        grouplist = new ArrayList<>();

        ArrayList<DrawerItemDomain> sec1 = new ArrayList<>();
        sec1.add(new DrawerItemDomain(R.drawable.profile, "Profile"));
//        sec1.add(new DrawerItemDomain(R.drawable.my_report, "My Report"));
        grouplist.add(new DrawerGroupDomain(1, sec1));

        ArrayList<DrawerItemDomain> sec2 = new ArrayList<>();
        sec2.add(new DrawerItemDomain(R.drawable.settings, "Settings"));
//        sec2.add(new DrawerItemDomain(R.drawable.sidenotification, "Notifications"));
        grouplist.add(new DrawerGroupDomain(2, sec2));

        ArrayList<DrawerItemDomain> sec3 = new ArrayList<>();
       /* sec3.add(new DrawerItemDomain(R.drawable.help_support, "Help & Support"));
        sec3.add(new DrawerItemDomain(R.drawable.legal_and_privacy, "Legal & Privacy"));*/
        sec3.add(new DrawerItemDomain(R.drawable.logot, "Logout"));
        grouplist.add(new DrawerGroupDomain(3, sec3));

        DrawerAdapter adapter = new DrawerAdapter(this, grouplist, LauncherActivity.this);
        drawerrecycler.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.navicon:
                drawer.openDrawer(GravityCompat.START);
                break;

            case R.id.fab:
                break;

        }

    }

    @Override
    public void clickedsideitem(String s) {

    }
}
