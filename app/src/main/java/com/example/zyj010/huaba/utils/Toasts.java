package com.example.zyj010.huaba.utils;

import android.widget.Toast;

import com.example.zyj010.huaba.manage.AppManager;


/**
 * Toast类，无需多说
 * Created by Wouldyou on 2015/5/29.
 */
public class Toasts {

    public static void show(String msg) {
        Toast.makeText(AppManager.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(int msg) {
        Toast.makeText(AppManager.getAppContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }
}
