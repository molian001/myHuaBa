package com.zyj010.huaba.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.adapter.course_adpter;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.ui.MoredrawerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj010 on 2016/4/10 0010.
 */
public class fragment_huashi extends Fragment{
    private List<Course> courses=new ArrayList<Course>();
    private course_adpter coureseAdpter;
    TextView more_huajia;
    ListView list_courses;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_huashi, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();

    }

    private void initListView() {
        more_huajia= (TextView) this.getActivity().findViewById(R.id.more_huajia);
        more_huajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MoredrawerActivity.class);
                startActivity(intent);
            }
        });
        Course course1=new Course();
        Course course2=new Course();
        Course course3=new Course();
        Course course4=new Course();
        Course course5=new Course();
        Bitmap bitmap1;
        list_courses= (ListView) this.getActivity().findViewById(R.id.listview_courses);
        course1.setCourse("白冰洋","艾尔大神《阿瓦隆》日漫基础教程","1",bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.c1), bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.cp) );
        courses.add(course1);
        course2.setCourse("莉莉吴","手绘素描彩铅实物","2",bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.c2), bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.cp) );
        courses.add(course2);
        course3.setCourse("莉莉吴","简笔画系列主题训练","2",bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.c3), bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.cp) );
        courses.add(course3);
        course4.setCourse("莉莉吴","手绘素描彩铅实物","2",bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.c4), bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.cp) );
        courses.add(course4);
        course5.setCourse("莉莉吴","手绘素描彩铅实物","3",bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.c5), bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.cp) );
        courses.add(course5);
        coureseAdpter=new course_adpter(getActivity(),courses);
        list_courses.setAdapter(coureseAdpter);


        View listItem =coureseAdpter.getView(0, null, list_courses);
        listItem.measure(0, 0);
        int total=listItem.getMeasuredHeight();

        total=total*coureseAdpter.getCount();
        ViewGroup.LayoutParams params=list_courses.getLayoutParams();
        params.height=total+(list_courses.getDividerHeight()*(coureseAdpter.getCount()-1));
        list_courses.setLayoutParams(params);


    }
}
