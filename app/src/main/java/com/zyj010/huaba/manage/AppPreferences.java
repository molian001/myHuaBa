package com.zyj010.huaba.manage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.runzii.lib.utils.MyManager;


public class AppPreferences {

    private static final String AUTH = "auth_paintmooc_self";

    private static AppPreferences instance;

    private SharedPreferences sh;

    private SharedPreferences userid;
    private AppPreferences() {
    }

    private AppPreferences(Context ctx) {
        sh = PreferenceManager.getDefaultSharedPreferences(ctx);
        userid=PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences(MyManager.getAppContext());
        }
        return instance;
    }

    public String getAuth() {
        return sh.getString(AUTH, "");
    }
    public long getUserid(){return userid.getLong("userid",0);}

    public boolean setAuth(String auth) {
        return sh.edit().putString(AUTH, auth).commit();
    }
    public boolean setuserid(long id) {
        return sh.edit().putLong("userid", id).commit();
    }

    public boolean clearAll() {
        return sh.edit().clear().commit()&&userid.edit().clear().commit();
    }

}
