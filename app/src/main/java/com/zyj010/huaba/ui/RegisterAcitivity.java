package com.zyj010.huaba.ui;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.runzii.lib.utils.Logs;
import com.zyj010.huaba.R;
import com.zyj010.huaba.databinding.ActivityRegisterBinding;
import com.zyj010.huaba.http.NetworkWrapper;
import com.zyj010.huaba.model.User;
import com.zyj010.huaba.model.http.RegistBody;
import com.zyj010.huaba.utils.Toasts;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by zyj010 on 2016/4/11 0011.
 */
public class RegisterAcitivity extends AppCompatActivity {
    private User user;

    private ActivityRegisterBinding binding;
    private Long time;

    public static final long DELAY_SECONDS = 10l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        init();
        RxView.clicks(binding.btnSendCode)
                .flatMap(aVoid1 -> {
                    binding.btnSendCode.setEnabled(false);
                    sendCode();
                    return Observable.timer(1, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread());
                })
                .repeat(DELAY_SECONDS)
                .subscribe(aLong -> {
                    binding.btnSendCode.setText(DELAY_SECONDS - aLong + "s");
                    if (aLong == DELAY_SECONDS) {
                        binding.btnSendCode.setText("重新获取");
                        binding.btnSendCode.setEnabled(true);
                    }
                });
    }


    private void init() {
        Drawable drawable1 = getResources().getDrawable(R.drawable.user);
        drawable1.setBounds(0, 0, 45, 55);
        binding.registerUser.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.password);
        drawable2.setBounds(0, 0, 45, 55);
        binding.registerPassword.setCompoundDrawables(drawable2, null, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.identifycode);
        drawable3.setBounds(0, 0, 48, 48);
        binding.registerYanzheng.setCompoundDrawables(drawable3, null, null, null);
    }


//    private boolean getData() {
//        String userId=binding.registerUser.getText().toString();
//        String password=binding.registerPassword.getText().toString();
//        String code=binding.registerYanzheng.getText().toString();
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


    public void sendCode() {
        String phone_number = binding.registerUser.getText().toString();
        if (TextUtils.isEmpty(phone_number) || phone_number.length() != 11) {
            Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else {

            NetworkWrapper.get().sendVerifyCode(phone_number, 1)
                    .subscribe(responseBody -> Toasts.show("验证码发送成功"), throwable -> {
                        Toasts.show(throwable.getMessage());
                        Logs.d(throwable.getMessage());
                    });
        }
    }

    public void regist(View view) {

        String userId = binding.registerUser.getText().toString();
        String password = binding.registerPassword.getText().toString();
        String code = binding.registerYanzheng.getText().toString();

        if (TextUtils.isEmpty(userId) || userId.length() != 11) {
            Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6 || password.length() > 12) {
            Toast.makeText(getApplicationContext(), "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        } else {

            RegistBody registBody = new RegistBody();
            registBody.setPasswrod(password);
            registBody.setPhone(userId);
            registBody.setUsername(userId);
            NetworkWrapper.get().regist(code, registBody)
                    .subscribe(responseBody -> Toasts.show("成功"), throwable -> {
                        Toasts.show(throwable.getMessage());
                        Logs.d(throwable.getMessage());
                    });

        }
    }
}
