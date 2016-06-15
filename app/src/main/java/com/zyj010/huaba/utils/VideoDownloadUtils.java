package com.zyj010.huaba.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zyj010 on 2016/6/15 0015.
 */
public class VideoDownloadUtils {
    Handler handler;
    String  urlStr;
    File file;

    public int getPrecent() {
        return precent;
    }

    int precent=0;

    public VideoDownloadUtils(Handler handler, String urlStr, File file) {
        this.handler=handler;
        this.urlStr=urlStr;
        this.file=file;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public void startDownload(){
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                handler.sendMessage(handler.obtainMessage(0,"start"));
                try {
                /*
                 * 通过URL取得HttpURLConnection
                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
                 * <uses-permission android:name="android.permission.INTERNET" />
                 */

                    URL url = new URL(urlStr);
                    URLConnection conn = url.openConnection();
                    int contentLength = conn.getContentLength();

                    //取得inputStream，并将流中的信息写入SDCard

                /*
                 * 写前准备
                 * 1.在AndroidMainfest.xml中进行权限配置
                 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                 * 取得写入SDCard的权限
                 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
                 * 3.检查要保存的文件上是否已经存在
                 * 4.不存在，新建文件夹，新建文件
                 * 5.将input流中的信息写入SDCard
                 * 6.关闭流
                 */



                    InputStream input = conn.getInputStream();


                    OutputStream output = new FileOutputStream(file);
                    //读取大文件
                    byte[] buffer = new byte[1024];
                    int len;
                    int Count = 0;
                    while ((len = input.read(buffer)) != -1) {
                        output.write(buffer, 0, len);
                        Count=Count+len;

                        precent=Count*100/contentLength;
                        Bundle bundle = new Bundle();
                        bundle.putInt("progress",precent);
                        handler.sendMessage(handler.obtainMessage(2,bundle));
                        output.flush();
                    }
                    input.close();
                    if (output != null) {
                        output.close();
                    }

                    Toasts.show("下载完成");
                    handler.sendMessage(handler.obtainMessage(1,"下载结束"));
                    Looper.loop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
