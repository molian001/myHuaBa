package com.example.zyj010.huaba.model;

import java.io.Serializable;

/**
 * Created by zyj010 on 2016/5/24 0024.
 */
public class Video implements Serializable{
    long id;
    String  videoname;

    public long getId() {
        return id;
    }

    public String getVideoname() {
        return videoname;
    }
    public void setVideoname(String videoname){this.videoname=videoname;}
}
