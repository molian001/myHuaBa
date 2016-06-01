package com.example.zyj010.huaba.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.fragment.fragment_found;
import com.example.zyj010.huaba.fragment.fragment_huashi;
import com.example.zyj010.huaba.fragment.fragment_knowledge;
import com.example.zyj010.huaba.fragment.fragment_mine;
import com.example.zyj010.huaba.manage.AppPreferences;

import java.io.File;

public class index extends FragmentActivity {
    Button[]  Tab;
    private int index;
    private fragment_huashi fragment_huashi;
    private fragment_mine fragment_mine;
    private fragment_found fragment_found;
    private fragment_knowledge fragment_knowledge;
    private int currentindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(AppPreferences.getInstance().getAuth())) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_index);

        initView();
        initTab();
    }

    private void initView() {
        Tab=new Button[5];
        Tab[0]= (Button) findViewById(R.id.Hua_shi);
        Tab[1]= (Button)findViewById(R.id.Zhishi);
        Tab[2]= (Button)findViewById(R.id.found);
        Tab[3]=(Button)findViewById(R.id.mine);
        Tab[4]=(Button)findViewById(R.id.center_button);
        Tab[0].setSelected(true);

    }

    private void initTab() {
    fragment_huashi=new fragment_huashi();
    fragment_mine=new fragment_mine();
    fragment_found=new fragment_found();
    fragment_knowledge=new fragment_knowledge();

        FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragment_huashi);
        fragmentTransaction.commit();
    }
public void onTabSelect(View view){
    switch (view.getId()){
        case R.id.Hua_shi:
            index=0;
            break;
        case R.id.Zhishi:
            index=1;
            break;
        case R.id.found:
            index=2;
            break;
        case R.id.mine:
            index=3;
            break;
        case R.id.center_button:
            index=4;
            break;
    }
    if (index==0){
        if(index!=currentindex){

            android.app.FragmentManager fm=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
            switch (currentindex){
                case 0:
                    fragmentTransaction.hide(fragment_huashi);
                    break;
                case 1:
                    fragmentTransaction.hide(fragment_knowledge);
                    break;
                case 2:
                    fragmentTransaction.hide(fragment_found);
                    break;
                case 3:
                    fragmentTransaction.hide(fragment_mine);
                    break;
            }

        if(!fragment_huashi.isAdded()){
        fragmentTransaction.add(R.id.fragment_container,fragment_huashi);}
        fragmentTransaction.show(fragment_huashi).commit();
        Tab[index].setSelected(true);
        Tab[currentindex].setSelected(false);
        currentindex=index;}
    }
    if (index==1){
        if(index!=currentindex){
            android.app.FragmentManager fm=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
            switch (currentindex){
                case 0:
                    fragmentTransaction.hide(fragment_huashi);
                    break;
                case 1:
                    fragmentTransaction.hide(fragment_knowledge);
                    break;
                case 2:
                    fragmentTransaction.hide(fragment_found);
                    break;
                case 3:
                    fragmentTransaction.hide(fragment_mine);
                    break;
            }
            if(!fragment_knowledge.isAdded()){
            fragmentTransaction.add(R.id.fragment_container,fragment_knowledge);}
            fragmentTransaction.show(fragment_knowledge).commit();
            Tab[index].setSelected(true);
            Tab[currentindex].setSelected(false);
            currentindex=index;}
    }
    if (index==2){
        if(index!=currentindex){
            android.app.FragmentManager fm=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
            switch (currentindex){
                case 0:
                    fragmentTransaction.hide(fragment_huashi);
                    break;
                case 1:
                    fragmentTransaction.hide(fragment_knowledge);
                    break;
                case 2:
                    fragmentTransaction.hide(fragment_found);
                    break;
                case 3:
                    fragmentTransaction.hide(fragment_mine);
                    break;
            }
            if(!fragment_found.isAdded()){
            fragmentTransaction.add(R.id.fragment_container,fragment_found);}
            fragmentTransaction.show(fragment_found).commit();
            Tab[index].setSelected(true);
            Tab[currentindex].setSelected(false);
            currentindex=index;}
    }
    if(index==3){
        if (index!=currentindex){
        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
            switch (currentindex){
                case 0:
                    fragmentTransaction.hide(fragment_huashi);
                    break;
                case 1:
                    fragmentTransaction.hide(fragment_knowledge);
                    break;
                case 2:
                    fragmentTransaction.hide(fragment_found);
                    break;
                case 3:
                    fragmentTransaction.hide(fragment_mine);
                    break;
            }
        if(!fragment_mine.isAdded())    {
        fragmentTransaction.add(R.id.fragment_container,fragment_mine);}
        fragmentTransaction.show(fragment_mine).commit();
        Tab[index].setSelected(true);
        Tab[currentindex].setSelected(false);
        currentindex=index;}
    }
    if(index==4){
        Intent intent=new Intent(index.this,VideoActivity.class);
        startActivity(intent);
    }

}

}
