package com.example.zyj010.huaba.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.zyj010.huaba.R;

/**
 * Created by zyj010 on 2016/4/11 0011.
 */
public class forgetpassword extends AppCompatActivity{
    EditText forget_user;
    EditText forget_password;
    EditText forget_code;
    Button finish_forget;
    Button get_code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forggetpassword);
        init();
    }

    private void init() {
        forget_code=(EditText)findViewById(R.id.forgget_yanzheng);
        forget_user=(EditText)findViewById(R.id.forgget_user);
        forget_password=(EditText)findViewById(R.id.new_password);
        finish_forget=(Button)findViewById(R.id.finish_forgget);
        get_code=(Button)findViewById(R.id.get_forgetcode);
        Drawable drawable1=getResources().getDrawable(R.drawable.user);
        drawable1.setBounds(0,0,45,55);
        forget_user.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2=getResources().getDrawable(R.drawable.password);
        drawable2.setBounds(0,0,45,55);
        forget_password.setCompoundDrawables(drawable2, null, null, null);
        Drawable drawable3=getResources().getDrawable(R.drawable.identifycode);
        drawable3.setBounds(0,0,48,48);
        forget_code.setCompoundDrawables(drawable3,null,null,null);
    }
}
