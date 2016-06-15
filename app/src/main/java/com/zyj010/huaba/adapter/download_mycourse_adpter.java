package com.zyj010.huaba.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.ui.AlreadydownloadActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj010 on 2016/5/30 0030.
 */
public class download_mycourse_adpter extends BaseAdapter {
    private Context ct;
    private List<Course> courses;
    String path= Environment.getExternalStorageDirectory()+"/HuaBa/";
    private int Editable=0;
    private int SelectAll=0;
    private download_video_adpter adpter;


    public  download_mycourse_adpter(Context ct, List<Course> course, int Editable, int SelectAll){
        this.ct=ct;
        this.courses=course;
        this.Editable=Editable;
        this.SelectAll=SelectAll;

    }
    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
         Course course=null;
        List<Video> videos=new ArrayList<Video>();

        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_downloadedcourse,null);
            viewHolder=new ViewHolder();
            viewHolder.course_img= (ImageView) convertView.findViewById(R.id.iv_downloadvideo_course);
            viewHolder.course_name= (TextView) convertView.findViewById(R.id.tv_downloadvideo_coursename);
            viewHolder.course_num= (TextView) convertView.findViewById(R.id.tv_downloadvideo_mycoursenum);
            viewHolder.show_videos= (ImageButton) convertView.findViewById(R.id.btn_show_videos);
            viewHolder.list_videos= (ListView) convertView.findViewById(R.id.list_downloaded_video);
            viewHolder.delete= (ImageButton) convertView.findViewById(R.id.ibtn_downloaded_videos_delete_courese);
            convertView.setTag(viewHolder);
            course=courses.get(position);

            File[] files = new File(path+course.getCourse_name()+"/").listFiles();
            for(int i=0;i<files.length;i++){
                Video video=new Video();
                video.setVideoname(files[i].getName());
                videos.add(video);
            }
            adpter=new download_video_adpter(ct,videos,course,Editable,SelectAll);
            viewHolder.list_videos.setAdapter(adpter);
            if(Editable==1){
                viewHolder.delete.setVisibility(View.VISIBLE);
                adpter=new download_video_adpter(ct,videos,course,Editable,SelectAll);
                viewHolder.list_videos.setAdapter(adpter);
            }

            viewHolder.course_name.setText(course.getCourse_name());
            viewHolder.course_num.setText("已缓存"+videos.size()+"集");
            final ViewHolder finalViewHolder = viewHolder;
            final Course finalCourse = course;
            if(SelectAll==1){
                viewHolder.delete.setSelected(true);
                adpter=new download_video_adpter(ct,videos, finalCourse,Editable,SelectAll);
            }
            if(SelectAll==0){
                viewHolder.delete.setSelected(false);
                adpter=new download_video_adpter(ct,videos, finalCourse,Editable,SelectAll);
                viewHolder.list_videos.setAdapter(adpter);
            }
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalViewHolder.delete.isSelected()==false){
                        finalViewHolder.delete.setSelected(true);
                        SelectAll=1;
                        adpter=new download_video_adpter(ct,videos, finalCourse,Editable,SelectAll);
                        finalViewHolder.list_videos.setAdapter(adpter);
                    }
                    else  {finalViewHolder.delete.setSelected(false);
                        SelectAll=0;
                        adpter=new download_video_adpter(ct,videos, finalCourse,Editable,SelectAll);
                        finalViewHolder.list_videos.setAdapter(adpter);
                    }
                }
            });

            View listItem =adpter.getView(0, null, viewHolder.list_videos);
            listItem.measure(0, 0);
            int total=listItem.getMeasuredHeight();

            total=total*adpter.getCount();
            ViewGroup.LayoutParams params= viewHolder.list_videos.getLayoutParams();
            params.height=total+( viewHolder.list_videos.getDividerHeight()*(adpter.getCount()-1));
            viewHolder.list_videos.setLayoutParams(params);

        }
        else {viewHolder= (ViewHolder) convertView.getTag();
        }


//        if (course.getCourse_img_uri()!=null){
//            Glide.with(ct)
//                    .load(Uri.parse(course.getCourse_img_uri()))
//                    .into(viewHolder.course_img);
//
//        }


        final ViewHolder finalViewHolder = viewHolder;


        viewHolder.show_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()==false){
              v.setSelected(true);
                finalViewHolder.list_videos.setVisibility(View.VISIBLE);}
                else{ v.setSelected(false);
                finalViewHolder.list_videos.setVisibility(View.GONE);
                }
            }


        });
        return convertView;
    }

//    private Bitmap getBitmap(Course course) {
//        try {
//            Bitmap bitmap= MediaStore.Images.Media.getBitmap(ct.getContentResolver(), Uri.parse(course.getCourse_img_uri()));
//            return bitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

//    }



    static class ViewHolder{

        ImageView course_img;
        TextView course_name;
        TextView course_num;
        ImageButton show_videos;
        ListView list_videos;
        ImageButton delete;

    }
}
