package com.zyj010.huaba.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.R;

import java.util.List;

/**
 * Created by zyj010 on 2016/4/20 0020.
 */
public class course_adpter extends BaseAdapter {
    private Context ct;
    private List<Course> courses;
    public  course_adpter(Context ct,List<Course> course){
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
        TextView author_name = null;
        TextView course_labal = null;
        TextView num_student = null;
        RelativeLayout course_background = null;
        ImageView course_author_picture = null;
        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_course,null);
            author_name= (TextView) convertView.findViewById(R.id.course_auther_name);
            course_labal= (TextView) convertView.findViewById(R.id.course_lable);
            num_student= (TextView) convertView.findViewById(R.id.num_studyingpeople);
            course_background= (RelativeLayout) convertView.findViewById(R.id.course_picture);
            course_author_picture= (ImageView) convertView.findViewById(R.id.course_auther_picture);


            Course course=courses.get(position);
            String author_name1;
            String course_labal1;
            String num_student1;
            Bitmap course_background1;
            Bitmap course_author_picture1;
            author_name1=course.get_authorname();
            course_labal1=course.get_labal();
            num_student1=course.getNum_student();
            course_background1=course.getCourse_background();
            course_author_picture1=course.getCourse_author_picture();
            author_name.setText(author_name1);
            course_labal.setText(course_labal1);
            num_student.setText(num_student1+"正在学习");
            BitmapDrawable bd= new BitmapDrawable(course_background1);
            course_background.setBackground(bd);
            course_author_picture.setImageBitmap(course_author_picture1);}


        return  convertView;
    }
}
