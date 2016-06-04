package com.zyj010.huaba.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.manage.AppPreferences;

/**
 * Created by zyj010 on 2016/5/30 0030.
 */
public class SetupActivity extends Activity implements View.OnClickListener{
    private ImageView back;
    private Button outlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        init();
    }

    private void init() {
        back= (ImageView) findViewById(R.id.back_setup);
        outlogin= (Button) findViewById(R.id.btn_loginout);
        back.setOnClickListener(this);
        outlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==back){
            finish();
        }
        if (v==outlogin){
            AppPreferences.getInstance().clearAll();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
