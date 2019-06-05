package com.sample.program.sample_array_functions.Main;

import java.util.ArrayList;

public class DrawerGroupDomain {

    private int id;
    private ArrayList<DrawerItemDomain> list;

    public DrawerGroupDomain(int id, ArrayList<DrawerItemDomain> list) {
        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DrawerItemDomain> getList() {
        return list;
    }

    public void setList(ArrayList<DrawerItemDomain> list) {
        this.list = list;
    }
}
