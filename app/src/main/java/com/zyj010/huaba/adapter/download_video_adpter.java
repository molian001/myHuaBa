package com.zyj010.huaba.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.ui.AlreadyDownloadedVideoPlayActivity;
import com.zyj010.huaba.ui.AlreadydownloadActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj010 on 2016/5/30 0030.
 */
public class download_video_adpter  extends BaseAdapter{
    private List<Video> videos;
    private Context ct;
    private Course course;
    private int Editable;
    private int SelectAll=0;
    String path= Environment.getExternalStorageDirectory()+"/HuaBa/";
    public  download_video_adpter(Context ct, List<Video> videos, Course coursee,int Edittable,int SelectAll){
        this.videos=videos;
        this.ct=ct;
        this.course=coursee;
        this.Editable=Edittable;
        this.SelectAll=SelectAll;
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
            viewHolder.delete= (ImageButton) convertView.findViewById(R.id.ibtn_downloaded_videos_delete_video);
            viewHolder.play= (Button) convertView.findViewById(R.id.btn_download_video_start);
            convertView.setTag(viewHolder);
            video=videos.get(position);


        }
        else {viewHolder= (ViewHolder) convertView.getTag();
            video=videos.get(position);}

        viewHolder.videoname.setText(video.getVideoname());
        if(Editable==1){
            viewHolder.delete.setVisibility(View.VISIBLE);
        }
        if(SelectAll==1){
            viewHolder.delete.setSelected(true);
            File file=new File(path+course.getCourse_name()+"/"+ video.getVideoname());
            AlreadydownloadActivity.files.add(file);

        }
        if(SelectAll==0){
            viewHolder.delete.setSelected(false);
            File file=new File(path+course.getCourse_name()+"/"+ video.getVideoname());
            AlreadydownloadActivity.files.remove(file);
        }
        final ViewHolder finalViewHolder = viewHolder;
        final Video finalVideo = video;
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalViewHolder.delete.isSelected()==false){
                    finalViewHolder.delete.setSelected(true);
                    File file=new File(path+course.getCourse_name()+"/"+ finalVideo.getVideoname());
                    AlreadydownloadActivity.files.add(file);
                }
                else  {finalViewHolder.delete.setSelected(false);
                    File file=new File(path+course.getCourse_name()+"/"+ finalVideo.getVideoname());
                    AlreadydownloadActivity.files.remove(file);
                };
            }
        });
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ct,AlreadyDownloadedVideoPlayActivity.class);
                intent.putExtra("path",path+course.getCourse_name()+"/"+videos.get(position).getVideoname());
                ct.startActivity(intent);
            }
        });

        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//        for(int i=0;i<viewHolders.size();i++){
//            if (viewHolders.get(i).delete.isSelected()==true){
//                File file=new File(path+course.getCourse_name()+"/"+videos.get(i).getVideoname());
//                if(file.exists()){
//                    file.delete();
//                }
//            }
//        }
//    }


    static class ViewHolder{
        TextView videoname;
        Button play;
        ImageButton delete;
    }

}
