package com.runzii.lib.utils;

import android.widget.Toast;


/**
 * Toast类，无需多说
 * Created by Wouldyou on 2015/5/29.
 */
public class Toasts {

    public static void show(String msg) {
        Toast.makeText(MyManager.getAppContext(), msg, Toast.LENGTH_LONG).show();
    }
    public static void show(int msg) {
        Toast.makeText(MyManager.getAppContext(), String.valueOf(msg), Toast.LENGTH_LONG).show();
    }
}
