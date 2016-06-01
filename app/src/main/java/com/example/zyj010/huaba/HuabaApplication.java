package com.example.zyj010.huaba;

import android.app.Application;

import com.example.zyj010.huaba.http.Utils.HttpUtils;
import com.example.zyj010.huaba.manage.AppManager;

/**
 * Created by zyj010 on 2016/4/25 0025.
 */
public class HuabaApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        AppManager.init(this);
    }
}
