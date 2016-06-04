package com.zyj010.huaba.model;

import android.graphics.Bitmap;

/**
 * Created by zyj010 on 2016/5/4 0004.
 */
public class Knowledge {
    private Bitmap knowledge_background;
    private String lable;
    private String theme;

    public void setKnowledge(String lable, String theme, Bitmap knowledge_background) {
        this.knowledge_background = knowledge_background;
        this.lable = lable;
        this.theme = theme;
    }

    public String getLable() {
        return lable;
    }

    public String getTheme() {
        return theme;
    }

    public Bitmap getKnowledge_background() {
        return knowledge_background;
    }
}
