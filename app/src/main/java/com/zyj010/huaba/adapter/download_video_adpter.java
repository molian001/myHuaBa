package com.zyj010.huaba.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.ui.activity_download_videoplay;

import java.util.List;

/**
 * Created by zyj010 on 2016/5/30 0030.
 */
public class download_video_adpter  extends BaseAdapter{
    private List<Video> videos;
    private Context ct;
    private Course course;
    String path= Environment.getExternalStorageDirectory()+"/HuaBa/";
    public  download_video_adpter(Context ct, List<Video> videos, Course coursee){
        this.videos=videos;
        this.ct=ct;
        this.course=coursee;
    }
    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        Video video=null;

        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_downloaded_video,null);
            viewHolder=new ViewHolder();

            viewHolder.videoname= (TextView) convertView.findViewById(R.id.download_vedio_inf);

            viewHolder.play= (Button) convertView.findViewById(R.id.btn_download_video_start);
            convertView.setTag(viewHolder);
            video=videos.get(position);


        }
        else {viewHolder= (ViewHolder) convertView.getTag();
            video=videos.get(position);}

        viewHolder.videoname.setText(video.getVideoname());
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ct,activity_download_videoplay.class);
                intent.putExtra("path",path+course.getCourse_name()+"/"+videos.get(position).getVideoname());
                ct.startActivity(intent);
            }
        });

        return convertView;
    }


    static class ViewHolder{
        TextView videoname;
        Button play;

    }
}
