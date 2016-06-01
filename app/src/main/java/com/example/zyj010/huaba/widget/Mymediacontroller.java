package com.example.zyj010.huaba.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.zyj010.huaba.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by zyj010 on 2016/5/12 0012.
 */
public class Mymediacontroller extends MediaController {
    private ImageButton img_size;
    private Context context;
    private VideoView videoView;
    private Activity activity;


    public Mymediacontroller(Context context, VideoView videoView , Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;

    }

    @Override
    protected View makeControllerView() {
        View v=((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("mymediacontroller", "layout", getContext().getPackageName()), this);
        img_size=(ImageButton) v.findViewById(getResources().getIdentifier("size", "id", context.getPackageName()));

        img_size.setOnClickListener(sizeOnclickListener);

        return v;
    }
    private View.OnClickListener sizeOnclickListener = new View.OnClickListener() {
        public void onClick(View v) {

            RelativeLayout view= (RelativeLayout) activity.findViewById(R.id.actionbar_videoplay);
            RelativeLayout rall= (RelativeLayout) activity.findViewById(R.id.rall_introduce);
            RelativeLayout rall2= (RelativeLayout) activity.findViewById(R.id.rall_listandcomment);
            RelativeLayout video= (RelativeLayout) activity.findViewById(R.id.video_rlly);
            if(rall.getVisibility()==View.VISIBLE){

                view.setVisibility(View.GONE);
                rall.setVisibility(View.GONE);
                rall2.setVisibility(View.GONE);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                videoView.setVideoLayout(1,0);

            }
            else {
                view.setVisibility(View.VISIBLE);
                rall.setVisibility(View.VISIBLE);
                rall2.setVisibility(View.VISIBLE);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        };

}
