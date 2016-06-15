package com.zyj010.huaba.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zyj010.huaba.R;
import com.zyj010.huaba.adapter.video_course_adpter;
import com.zyj010.huaba.http.NetworkWrapper;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.utils.Toasts;

import java.io.Serializable;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zyj010 on 2016/5/23 0023.
 */
public class VideoCoursesActivity extends Activity implements AdapterView.OnItemClickListener{
    private ImageView back;
    private ListView list_courses;
    private List<Course> mycourses;
    private video_course_adpter adpter;
    private List<Video> videos;
 private Long id=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_courses);
        init();

    }

    private void init() {
        back= (ImageView) findViewById(R.id.back_video_courses);
        list_courses= (ListView) findViewById(R.id.list_videoplay_courses);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list_courses.setOnItemClickListener(this);
        getCourse();
    }

    private void getCourse() {

            Subscriber<List<Course>> subscriber = new Subscriber<List<Course>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"获取课程失败",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(List<Course> courses) {
                   mycourses =courses;
                    adpter=new video_course_adpter(VideoCoursesActivity.this,mycourses);
                    list_courses.setAdapter(adpter);
                    Toasts.show("获取课程成功");
                }
            };
            NetworkWrapper.get().getAllCourses(subscriber);
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       videos=mycourses.get(position).getVideos();
        Intent intent=new Intent(this,VideoPlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("videos",(Serializable)videos);
        bundle.putString("coursename",mycourses.get(position).getCourse_name());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

