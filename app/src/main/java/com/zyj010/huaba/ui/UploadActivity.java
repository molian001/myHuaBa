package com.zyj010.huaba.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zyj010.huaba.R;
import com.zyj010.huaba.adapter.upload_mycourse_adpter;
import com.zyj010.huaba.http.NetworkWrapper;
import com.zyj010.huaba.manage.AppPreferences;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.User;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zyj010 on 2016/5/21 0021.
 */
public class UploadActivity extends Activity implements View.OnClickListener ,AdapterView.OnItemClickListener {
    private ImageView back;
    private TextView add;
    private ListView list_mycourse;
    private upload_mycourse_adpter adpter;
    private List<Course> my_courses=null;
    private String couresename=null;
    private long  userid;
    private Course course=new Course();
    private String courseType;
    private Long Id;


    public static Uri uri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_mycourse);
        init();
    }

    private void init() {
        list_mycourse= (ListView) findViewById(R.id.list_upload_mycourse);
        back= (ImageView) findViewById(R.id.back_upload_mycourse);
        add= (TextView) findViewById(R.id.add_mycourse);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
        list_mycourse.setOnItemClickListener(this);
        getMycourses();


    }

    private void  getMycourses() {
        Subscriber<User> subscriber=new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();

            }

            @Override
            public void onNext(User user) {
            Id=user.getId();
                Toast.makeText(getApplicationContext(),""+Id,Toast.LENGTH_LONG).show();
                getCoureses();
            }
        };
        NetworkWrapper.get().getmyid(subscriber,AppPreferences.getInstance().getAuth());
    }

    private void getCoureses() {

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
                my_courses=courses;
                adpter=new upload_mycourse_adpter(UploadActivity.this,my_courses);
                list_mycourse.setAdapter(adpter);
            }
        };
        NetworkWrapper.get().getCourses(subscriber,Id,"");
    }

    @Override
    public void onClick(View v) {
        if(v==back){
            finish();
        }
        if(v==add){
            userid=AppPreferences.getInstance().getUserid();
            setDialog();



    }
    }

    private void setDialog() {

        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.dialog_course_info, (ViewGroup) findViewById(R.id.dailog_course));
        new AlertDialog.Builder(this).setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edit= (EditText)layout .findViewById(R.id.dilog_coursename);
                        couresename=edit.getText().toString();
                        if(couresename!=null&&courseType!=null){
                            course.setId(userid);
                            course.setCourse_name(couresename);
                            course.setCourseType(courseType);
                            addCourse(course);}
                        else{Toast.makeText(getApplicationContext(),"请完善课程信息",Toast.LENGTH_LONG).show(); }

                    }
                })
                .setNegativeButton("取消", null).show();
        final RadioGroup rg_courseType= (RadioGroup) layout.findViewById(R.id.rg_coursetype);
        final RadioButton chinese= (RadioButton) layout.findViewById(R.id.chinese);
        final RadioButton oilpainting= (RadioButton) layout.findViewById(R.id.oilpainting);
        final RadioButton sketch= (RadioButton) layout.findViewById(R.id.sketch);
        final RadioButton watercolor= (RadioButton) layout.findViewById(R.id.watercolor);
        final RadioButton simpledrawing= (RadioButton) layout.findViewById(R.id.simpledrawing);
        final RadioButton gouache= (RadioButton) layout.findViewById(R.id.gouache);
        final RadioButton coloredpencil= (RadioButton) layout.findViewById(R.id.coloredpencil);
        final RadioButton cartoon= (RadioButton) layout.findViewById(R.id.cartoon);
        final TextView tv_coursetype= (TextView) layout.findViewById(R.id.tv_courseType);
        rg_courseType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==chinese.getId())  {courseType=chinese.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==oilpainting.getId())  {courseType=oilpainting.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==sketch.getId())  {courseType=sketch.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==watercolor.getId())  {courseType=watercolor.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==simpledrawing.getId())  {courseType=simpledrawing.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==gouache.getId())  {courseType=gouache.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==coloredpencil.getId())  {courseType=coloredpencil.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
                if(checkedId==cartoon.getId()) {courseType=cartoon.getText().toString();tv_coursetype.setText("课程类型:"+courseType);}
            }
        });
        Button coursetype=(Button)layout.findViewById(R.id.btn_courseType);
        coursetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg_courseType.getVisibility()==View.GONE){rg_courseType.setVisibility(View.VISIBLE);}
                else rg_courseType.setVisibility(View.GONE);
            }
        });
    }

    private void addCourse(Course course) {
    Subscriber<String> subscriber=new Subscriber<String>() {
     @Override
     public void onCompleted() {

     }

     @Override
     public void onError(Throwable e) {
         e.printStackTrace();

     }

     @Override
     public void onNext(String s) {
        Toast.makeText(getApplicationContext(),"创建成功",Toast.LENGTH_LONG).show();
     }
 };
        NetworkWrapper.get().addCourese(subscriber,AppPreferences.getInstance().getAuth(),course);
    };








    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Course course=my_courses.get(position);
        Intent intent =new Intent(this,FilesuploadActivity.class);
        intent.putExtra("id",course.getId());
        startActivity(intent);
    }
}
