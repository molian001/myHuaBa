package com.example.zyj010.huaba.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj010 on 2016/4/19 0019.
 */
public class Course implements Serializable{
    String author_name;
    long id;
    String course_labal;
    String courseType;
    String coursename;
    String num_student;
    String thumbnail;
    Bitmap course_background;
    Bitmap course_author_picture;
    List<Video> videos;
    public void setCourse(String author_name1,String course_labal1,String num_student1,Bitmap course_background1,Bitmap course_author_picture1){
        author_name=author_name1;
        course_labal=course_labal1;
        num_student=num_student1;
        course_background=course_background1;
        course_author_picture=course_author_picture1;
    }
    public void setId(long id){
        this.id=id;
    }
    public void setCourse_img_uri(String uri){thumbnail=uri;}

    public void setCourse_name(String course_name) {
        this.coursename = course_name;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String get_authorname(){
        return this.author_name;
    }
    public String get_labal(){
        return this.course_labal;
    }
    public String getNum_student(){
        return this.num_student;
    }
    public Bitmap getCourse_background(){
        return this.course_background;
    }
    public Bitmap getCourse_author_picture(){
        return this.course_author_picture;
    }
    public long getId(){return this.id;}
    public String   getCourse_img_uri(){return thumbnail;}

    public String getCourse_name() {
        return coursename;
    }

    public String getCourseType() {
        return courseType;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
