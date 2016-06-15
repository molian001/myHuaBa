package com.zyj010.huaba.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.zyj010.huaba.R;

/**
 * Created by zyj010 on 2016/4/13 0013.
 */
public class VideoActivity extends Activity implements View.OnClickListener{
//    private VideoView videoview;
//    private MediaController mediaco;
//
//    private static AsyncHttpClient httpClient=new AsyncHttpClient();
    private ImageButton upload;
    private ImageButton vedio;
    private ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.activity_video);
        init();

        }
//        videoview.setMediaController(mediaco);
//        mediaco.setMediaPlayer(videoview);
//        videoview.start();
//        videoview.requestFocus();


    private void init() {
//        mediaco=new MediaController(this);
//        videoview=(VideoView)findViewById(R.id.vedio_play);
//
//        httpClient.get("http://192.168.1.103:8080/getvideo", new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Toast.makeText(getApplicationContext(),"与服务器连接失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//
//
//                videoview.setVideoURI(Uri.parse(responseString));
//
//            }
//        });
        upload= (ImageButton) findViewById(R.id.btn_upload);
        vedio= (ImageButton) findViewById(R.id.btn_vedio);
        back= (ImageButton) findViewById(R.id.btn_vedio_back);
        upload.setOnClickListener(this);
        vedio.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==upload){
            Intent intent=new Intent(this,UploadActivity.class);
            startActivity(intent);
        }
        if(v==vedio){
            Intent intent=new Intent(this,VideoCoursesActivity.class);
            startActivity(intent);
        }
        if(v==back){
            finish();
        }
    }
}
