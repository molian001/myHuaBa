package com.example.zyj010.huaba.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.adapter.found_adpter;
import com.example.zyj010.huaba.model.Found;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyj010 on 2016/4/12 0012.
 */
public class fragment_found extends Fragment {
    private List<Found> founds=new ArrayList<Found>();
    private found_adpter foundAdapter;
    ListView list_found;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_found, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListview();
    }

    private void initListview() {

        list_found= (ListView) this.getActivity().findViewById(R.id.found_listview);
        getFounds();
        foundAdapter=new found_adpter(getActivity(),founds);
        list_found.setAdapter(foundAdapter);
    }

    private void getFounds() {

        Found found1=new Found();
        Found found2=new Found();

        Bitmap Bitmap1;
        found1.setFound("首届画吧彩铅大赛","火热报名中","2016.4.1-2016.6.1", Bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.match1));
        founds.add(found1);
        found2.setFound("全国大学生油画创作大赛","火热报名中","2016.4.1-2016.6.1", Bitmap1= BitmapFactory.decodeResource(getResources(),R.drawable.match2));
        founds.add(found2);

    }
}