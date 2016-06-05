package com.zyj010.huaba.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zyj010.huaba.R;

/**
 * Created by zyj010 on 2016/4/18 0018.
 */
public class fragment_knowledge extends Fragment {
    EditText edittext_serch;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_knowledge,container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();

    }

    private void initListView() {
        edittext_serch= (EditText) this.getActivity().findViewById(R.id.knowledge_serch);
        Drawable drawable=getResources().getDrawable(R.drawable.search);
        drawable.setBounds(20,0,60,40);
        edittext_serch.setCompoundDrawables(drawable,null,null,null);
    }
}
