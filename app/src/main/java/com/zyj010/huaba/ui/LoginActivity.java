package com.zyj010.huaba.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zyj010.huaba.R;
import com.zyj010.huaba.http.NetworkWrapper;
import com.zyj010.huaba.manage.AppPreferences;
import com.zyj010.huaba.model.AuthToken;

import rx.Observable;
import rx.Subscriber;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginin;
    ImageView qq_login;
    ImageView weibo_login;
    TextView register;
    TextView foget_passwords;
    EditText editText_user;
    EditText editText_passwords;
    private Observable<AuthToken> login = null;
    private ProgressBar mProgressView;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        Drawable drawable1=getResources().getDrawable(R.drawable.user);
        drawable1.setBounds(0,0,45,55);
        editText_user.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2=getResources().getDrawable(R.drawable.password);
        drawable2.setBounds(0,0,45,55);
        editText_passwords.setCompoundDrawables(drawable2, null, null, null);
    }

    private void init() {
        loginin= (Button) findViewById(R.id.login);
        mProgressView= (ProgressBar) findViewById(R.id.login_progress);
        editText_user = (EditText) findViewById(R.id.edittext_user);
        editText_passwords = (EditText) findViewById(R.id.edittext_password);
        qq_login= (ImageView) findViewById(R.id.qq_login);
        weibo_login= (ImageView) findViewById(R.id.weibo_login);
        register= (TextView) findViewById(R.id.register_text);
        foget_passwords= (TextView) findViewById(R.id.forgget_password);
        loginin.setOnClickListener(this);
        register.setOnClickListener(this);
        foget_passwords.setOnClickListener(this);
        weibo_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==loginin){
            login();
        }
        if(v==register){
            rgister();
        }
        if(v==foget_passwords){
            forget();

        }

    }

    private void forget() {
        Intent intent=new Intent(LoginActivity.this,ForgetpasswordActivity.class);
        startActivity(intent);
    }

    private void rgister() {
        Intent intent=new Intent(LoginActivity.this,RegisterAcitivity.class);
        startActivity(intent);
    }

    private void login() {
        final String name=editText_user.getText().toString();
               String passwords=editText_passwords.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwords)){
            Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();

            return;

}
        else{

            showProgress(true);
            Subscriber<AuthToken> subscriber = new Subscriber<AuthToken>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    showProgress(false);
                }

                @Override
                public void onNext(AuthToken authToken) {
                   long  uid=Long.parseLong(name);
                    AppPreferences.getInstance().setAuth("bearer " + authToken.getAccess_token());
                    AppPreferences.getInstance().setuserid(uid);

                    Toast.makeText(getApplicationContext(),AppPreferences.getInstance().getUserid()+"",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    showProgress(false);
                    finish();
                }

        };
            NetworkWrapper.get().getAuthToken(subscriber, name, passwords);

    }

    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}

