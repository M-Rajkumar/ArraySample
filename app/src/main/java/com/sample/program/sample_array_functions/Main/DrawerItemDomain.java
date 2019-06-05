package com.sample.program.sample_array_functions.Main;

public class DrawerItemDomain {
    int icon;
    String name;


    public DrawerItemDomain(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public DrawerItemDomain() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
