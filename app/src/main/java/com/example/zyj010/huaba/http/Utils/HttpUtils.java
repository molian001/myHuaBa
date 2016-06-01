package com.example.zyj010.huaba.http.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.zyj010.huaba.http.Model.loginDate;
import com.example.zyj010.huaba.http.Model.registDate;
import com.example.zyj010.huaba.http.httpConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by zyj010 on 2016/4/25 0025.
 */
public class HttpUtils {
    private static AsyncHttpClient asyncHttpClient;
    private static SyncHttpClient syncHttpClient;
    private static ConnectivityManager connectivityManager;

    public static void init(Context context){
        asyncHttpClient=new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        asyncHttpClient.setCookieStore(cookieStore);
        asyncHttpClient.setEnableRedirects(true,true,true);

        syncHttpClient=new SyncHttpClient();
        syncHttpClient.setEnableRedirects(true,true,true);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    public static void MakeAPICALL(@Nullable Object date,Context context, @Nullable RequestParams params,final HttpCallback callback){
        if(asyncHttpClient==null){
            init(context);
        }
        if(connectivityManager!=null){
            boolean hasnet=false;

            NetworkInfo[] networkInfo=connectivityManager.getAllNetworkInfo();

            for(int i=0;i<networkInfo.length;i++){
                if(networkInfo[i].getState()==NetworkInfo.State.CONNECTED){
                    hasnet=true;
                }
            }
            if(!hasnet){
                callback.onFailed("请检查网络连接");
                return;
            }
        }
        try {
            if(params!=null){
                Regist(context,params,callback);
            }
            else {
                Toast.makeText(context,"没启动2",Toast.LENGTH_SHORT).show();
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void Regist( Context context,RequestParams params, final HttpCallback callback)throws UnsupportedEncodingException {
        asyncHttpClient.post(httpConstants.RegistUrl,params,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
               callback.onError(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(null);
            }
        });
    }

}
