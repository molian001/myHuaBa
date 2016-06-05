package com.zyj010.huaba.model;

import android.graphics.Bitmap;

/**
 * Created by zyj010 on 2016/5/3 0003.
 */
public class Found {
    private String lable;
    private String lable2;
    private String time;
    private Bitmap found_background;

    public void setFound(String lable, String lable2, String time, Bitmap found_background) {
        this.lable = lable;
        this.lable2 = lable2;
        this.time = time;
        this.found_background = found_background;
    }

    public String getLable() {
        return lable;
    }

    public String getLable2() {
        return lable2;
    }

    public String getTime() {
        return time;
    }

    public Bitmap getFound_background() {
        return found_background;
    }
}
