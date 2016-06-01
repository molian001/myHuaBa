package com.example.zyj010.huaba.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.manage.AppPreferences;
import com.example.zyj010.huaba.ui.AlreadydownloadActivity;
import com.example.zyj010.huaba.ui.SetupActivity;

/**
 * Created by zyj010 on 2016/4/18 0018.
 */
public class fragment_mine extends Fragment implements View.OnClickListener{
    private ImageButton setup;
    private ImageButton downloaded;
    private TextView username;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

    }

    private void init() {
        setup= (ImageButton) getActivity().findViewById(R.id.ibtn_set);
        downloaded= (ImageButton) getActivity().findViewById(R.id.downloaded_courses);
        username= (TextView) getActivity().findViewById(R.id.mine_username);
        username.setText(AppPreferences.getInstance().getUserid()+"");
        setup.setOnClickListener(this);
        downloaded.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==setup){
            startActivity(new Intent(getActivity(), SetupActivity.class));
        }
        if(v==downloaded){
            startActivity(new Intent(getActivity(), AlreadydownloadActivity.class));
        }
    }


}
