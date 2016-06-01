package com.example.zyj010.huaba.model;

import android.graphics.Bitmap;

/**
 * Created by zyj010 on 2016/5/4 0004.
 */
public class Drawer {
    String name;
    Bitmap avater;
    public void setDrawer(String name,Bitmap avater){
        this.name=name;
        this.avater=avater;
    }
    public String getName(){
        return name;
    }
    public Bitmap getAvater(){
        return avater;
    }
}
