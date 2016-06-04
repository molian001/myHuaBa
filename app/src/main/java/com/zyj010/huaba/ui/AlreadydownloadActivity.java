package com.zyj010.huaba.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.adapter.download_mycourse_adpter;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj010 on 2016/5/30 0030.
 */
public class AlreadydownloadActivity extends Activity {
    private List<Course> courses=new ArrayList<Course>();
    private List<Video> videos=new ArrayList<Video>();
    private ListView list_courses;
    private download_mycourse_adpter adpter;
    String path= Environment.getExternalStorageDirectory()+"/HuaBa/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_havedownloaded);
        init();
    }

    private void init() {
        list_courses= (ListView) findViewById(R.id.list_downloaded_courses);
        getCourses();
        adpter=new download_mycourse_adpter(this,courses);
        list_courses.setAdapter(adpter);
    }



    private void getCourses() {

        File[] files = new File(path).listFiles();
        for(int i=0;i<files.length;i++){
            Course course=new Course();
            course.setCourse_name(files[i].getName());
            courses.add(course);
        }
    }
}
