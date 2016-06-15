package com.zyj010.huaba.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.adapter.download_mycourse_adpter;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.utils.Toasts;

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
    private ImageView back;
    private TextView edit;
    private LinearLayout select_delete;
    private int Editable=0;
    private int SelectAll=0;
    private TextView selectall;
    private TextView delete;
    public static List<File> files=new ArrayList<File>();
    String path= Environment.getExternalStorageDirectory()+"/HuaBa/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_havedownloaded);
        init();
    }

    private void init() {
        list_courses= (ListView) findViewById(R.id.list_downloaded_courses);
        back= (ImageView) findViewById(R.id.back_havedownloaded);
        edit= (TextView) findViewById(R.id.iv_downloaded_edit);
        select_delete= (LinearLayout) findViewById(R.id.lnl_edit);
        selectall= (TextView) findViewById(R.id.tv_downloaded_selectall);
        delete= (TextView) findViewById(R.id.tv_downloaded_delete);
        getCourses();
        adpter=new download_mycourse_adpter(AlreadydownloadActivity.this,courses,Editable,SelectAll);
        list_courses.setAdapter(adpter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 SelectAll= SelectAll==0?1:0;
                files.clear();
                adpter=new download_mycourse_adpter(AlreadydownloadActivity.this,courses,Editable,SelectAll);
                list_courses.setAdapter(adpter);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                files.clear();
                if(select_delete.getVisibility()==View.GONE)
                {select_delete.setVisibility(View.VISIBLE);}
                else select_delete.setVisibility(View.GONE);
                if(Editable==1){
                    Editable=0;
                }
                else Editable=1;
                adpter=new download_mycourse_adpter(AlreadydownloadActivity.this,courses,Editable,SelectAll);
                list_courses.setAdapter(adpter);
            }
        });
delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){

        for(int i=0;i<files.size();i++){
            files.get(i).delete();
        }
        Toasts.show("删除成功");
        select_delete.setVisibility(View.GONE);
        files.clear();
        Editable=0;
        SelectAll=0;
        courses.clear();
        getCourses();
        adpter=new download_mycourse_adpter(AlreadydownloadActivity.this,courses,Editable,SelectAll);
        list_courses.setAdapter(adpter);
    }
});

    }



    private void getCourses() {

        File[] files = new File(path).listFiles();
        for(int i=0;i<files.length;i++){
          if  (files[i].listFiles().length==0){
              files[i].delete();
              continue;
          };
            Course course=new Course();
            course.setCourse_name(files[i].getName());
            courses.add(course);
        }
    }
}
