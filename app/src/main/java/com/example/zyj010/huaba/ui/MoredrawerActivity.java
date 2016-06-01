package com.example.zyj010.huaba.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.adapter.SortAdapter;
import com.example.zyj010.huaba.model.SortModel;
import com.example.zyj010.huaba.utils.CharacterParser;
import com.example.zyj010.huaba.utils.PinyinComparator;
import com.example.zyj010.huaba.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zyj010 on 2016/5/5 0005.
 */
public class MoredrawerActivity extends Activity{private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;


    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList=new ArrayList<SortModel>();

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_of_drawer);
        initViews();
    }

    private void initViews() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.list_drawer);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        SourceDateList = getDate();
        SourceDateList=filledSortletters(SourceDateList);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);





    }

    private  List<SortModel> getDate() {
        List<SortModel> sortmodels=new ArrayList<SortModel>();
        SortModel sortModel=new SortModel();
        Bitmap bitmap;
        sortModel.setModel("阿宝儿",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.a11));
        sortmodels.add(sortModel);
        sortModel=new SortModel();
        sortModel.setModel("安庆天",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.a12));
        sortmodels.add(sortModel);
        sortModel=new SortModel();
        sortModel.setModel("边巧铭",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.b13));
        sortmodels.add(sortModel);
        sortModel=new SortModel();
        sortModel.setModel("艹文县",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.b21));
        sortmodels.add(sortModel);
        sortModel=new SortModel();
        sortModel.setModel("当其钧",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.c22));
        sortmodels.add(sortModel);
        sortModel=new SortModel();
        sortModel.setModel("卜述林",bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.c23));
        sortmodels.add(sortModel);

        return sortmodels;
    }


    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledSortletters( List<SortModel> date){
        List<SortModel> mSortList = new ArrayList<SortModel>();
         mSortList=date;
        for(int i=0; i<mSortList.size(); i++){


            //汉字转换成拼音
            String pinyin = characterParser.getSelling(mSortList.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                mSortList.get(i).setSortLetters(sortString.toUpperCase());
            }else{
                mSortList.get(i).setSortLetters("#");
            }


        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
