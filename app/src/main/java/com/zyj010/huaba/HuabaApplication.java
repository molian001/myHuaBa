package com.zyj010.huaba;

import com.runzii.lib.MyApplication;
import com.zyj010.huaba.manage.AppManager;

/**
 * Created by zyj010 on 2016/4/25 0025.
 */
public class HuabaApplication extends MyApplication {
    @Override
    public void onCreate(){
        super.onCreate();

        AppManager.init(this);
    }
}
