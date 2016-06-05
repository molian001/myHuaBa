package com.zyj010.huaba.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zyj010.huaba.R;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;

import java.util.List;

/**
 * Created by zyj010 on 2016/5/22 0022.
 */
public class upload_mycourse_adpter extends BaseAdapter {
    private Context ct;
    private List<Course> courses;
    private List<Video> videos;
    public  upload_mycourse_adpter(Context ct,List<Course> course){
        this.ct=ct;
        this.courses=course;
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
        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_upload_mycourse,null);
            viewHolder=new ViewHolder();
            viewHolder.course_img= (ImageView) convertView.findViewById(R.id.iv_upload_mycourse);
            viewHolder.course_name= (TextView) convertView.findViewById(R.id.tv_upload_mycoursename);
            viewHolder.course_num= (TextView) convertView.findViewById(R.id.tv_upload_mycoursenum);


            convertView.setTag(viewHolder);
            course=courses.get(position);
            videos=course.getVideos();
            if (course.getCourse_img_uri()!=null){
                Glide.with(ct)
                        .load(Uri.parse(course.getCourse_img_uri()))
                        .into(viewHolder.course_img);
            }
            viewHolder.course_name.setText(course.getCourse_name());
            viewHolder.course_num.setText("已上传"+videos.size()+"集");
        }
        else {viewHolder= (ViewHolder) convertView.getTag();
        }






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


    }
}
