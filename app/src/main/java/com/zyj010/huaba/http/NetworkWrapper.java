package com.zyj010.huaba.http;

import android.util.Base64;


import com.zyj010.huaba.manage.AppSettings;
import com.zyj010.huaba.model.AuthToken;
import com.zyj010.huaba.model.Course;
import com.zyj010.huaba.model.User;
import com.zyj010.huaba.model.http.RegistBody;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by runzii on 16-5-1.
 */
public class NetworkWrapper {

    private ApiService apiService;
    private ApiService apiService_login;

    public static final String BASE_URL = "http://" + AppSettings.SERVER_IP + ":" + AppSettings.SERVER_PORT + "/api/";

    public static final String userAndPassword = "paintmooc:secret";
    public static final String base_authorization = "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private Retrofit retrofit_login;
    //构造方法私有
    private NetworkWrapper() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        retrofit_login = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://" + AppSettings.SERVER_IP + ":" + AppSettings.SERVER_PORT)
                .build();
        apiService = retrofit.create(ApiService.class);
        apiService_login = retrofit_login.create(ApiService.class);
    }

    /**
     * 获取基本认证的token,用于一些请求
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param phone
     * @param password
     */
    public void getAuthToken(Subscriber<AuthToken> subscriber, String phone, String password) {
        apiService_login.getAuthToken(base_authorization, phone, password, "password")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getUploadToken(Subscriber<String> subscriber, String accessToken) {
        apiService
                .getUploadToken(accessToken)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String body = null;
                        try {
                            body = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return body;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getCourses(Subscriber<List<Course>> subscriber, long userid, String type) {
        apiService.getCourses(userid, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getAllCourses(Subscriber<List<Course>> subscriber) {
        apiService.getAllCourses()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void addCourese(Subscriber<String> subscriber, String accesstoken, Course courese) {
        apiService.addCourse(accesstoken, courese)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String body = null;
                        try {
                            body = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return body;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getmyid(Subscriber<User> subscriber, String accesstoken) {
        apiService.getmyid(accesstoken)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getVideoUri(Subscriber<String> subscriber, String accesstoken, long vid) {
        apiService.getVideoUri(accesstoken, vid)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String body = null;
                        try {
                            body = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return body;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Observable<ResponseBody> regist(String verifyCode, RegistBody registBody) {
        return apiService.regist(verifyCode, registBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> sendVerifyCode(String phone, int type) {
        return apiService.sendVerifyCode(phone, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private boolean isHttp401Error(Throwable throwable) {
        return "HTTP 401 Unauthorized".equals(throwable.getMessage()) ? true : false;
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final NetworkWrapper INSTANCE = new NetworkWrapper();
    }

    //获取单例
    public static NetworkWrapper get() {
        return SingletonHolder.INSTANCE;
    }


}
