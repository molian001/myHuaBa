package com.example.zyj010.huaba.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.http.Utils.HttpCallback;
import com.example.zyj010.huaba.http.Utils.HttpUtils;
import com.example.zyj010.huaba.http.httpConstants;
import com.example.zyj010.huaba.model.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by zyj010 on 2016/4/11 0011.
 */
public class RegisterAcitivity extends AppCompatActivity {
    EditText register_user;
    EditText register_password;
    EditText register_code;
    Button btn_getcode;
    Button btn_register;
    Map<String,String> registeuser;
    RequestParams params;
    String userdate="123";
    private static AsyncHttpClient httpClient=new AsyncHttpClient();
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


        });
        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcode();
            }
        });
    }



    private void init() {
        registeuser=new HashMap<String,String>();
        params=new RequestParams();
        register_password=(EditText)findViewById(R.id.register_password);
        register_user=(EditText)findViewById(R.id.register_user);
        register_code=(EditText)findViewById(R.id.register_yanzheng);
        btn_getcode=(Button)findViewById(R.id.get_code);
        btn_register=(Button)findViewById(R.id.finish_register);
        Drawable drawable1=getResources().getDrawable(R.drawable.user);
        drawable1.setBounds(0,0,45,55);
        register_user.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2=getResources().getDrawable(R.drawable.password);
        drawable2.setBounds(0,0,45,55);
        register_password.setCompoundDrawables(drawable2, null, null, null);
        Drawable drawable3=getResources().getDrawable(R.drawable.identifycode);
        drawable3.setBounds(0,0,48,48);
        register_code.setCompoundDrawables(drawable3,null,null,null);

    }
    private void register() throws UnsupportedEncodingException {

        String userId=register_user.getText().toString();
        String password=register_password.getText().toString();
        String code=register_code.getText().toString();

        if(TextUtils.isEmpty(userId)||userId.length()!=11){
            Toast.makeText(getApplicationContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)||password.length()<6||password.length()>12){
            Toast.makeText(getApplicationContext(),"请输入6-12位密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(code)){
            Toast.makeText(getApplicationContext(),"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            registeuser.put("username", userId);
            registeuser.put("password", password);
            registeuser.put("phone", userId);








            httpClient.post(getApplicationContext(),httpConstants.RegistUrl+"?verifyCode="+code,new StringEntity(new Gson().toJson(registeuser),ContentType.APPLICATION_JSON),"application/json",new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(getApplicationContext(),responseString, Toast.LENGTH_LONG).show();
                    Log.w("erron",responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

//    private boolean getData() {
//        String userId=register_user.getText().toString();
//        String password=register_password.getText().toString();
//        String code=register_code.getText().toString();
//
//        if(TextUtils.isEmpty(userId)||userId.length()!=11){
//            Toast.makeText(getApplicationContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(password)||password.length()<6||password.length()>12){
//            Toast.makeText(getApplicationContext(),"请输入6-12位密码",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if(TextUtils.isEmpty(code)){
//            Toast.makeText(getApplicationContext(),"请输入验证码",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else {
//            registeuser.put("uid",userId);
//            registeuser.put("username",userId);
//            registeuser.put("avatar",null);
//            registeuser.put("password",password);
//            registeuser.put("phone",userId);
//            registeuser.put("fans","0");
//            registeuser.put("followers","0");
//            registeuser.put("role",null);
//            registeuser.put("likes","0");
//            Gson gson=new Gson();
//            userdate=gson.toJson(registeuser);
//            params.put("verifyCode",code);
//            params.put("user",userdate);
//            return true;
//        }
//    }

    private void getcode() {
        String phone_number=register_user.getText().toString();
        RequestParams params = new RequestParams();
        params.put("phone",phone_number);
        params.put("type",1);

        if(TextUtils.isEmpty(phone_number)||phone_number.length()!=11){
            Toast.makeText(getApplicationContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

                httpClient.get(httpConstants.GetCode,params , new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        String str=String.valueOf(statusCode);
                        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
                    }
                });

        }

    }
}
