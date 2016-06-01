package com.example.zyj010.huaba.http;


import android.support.annotation.Nullable;

import com.example.zyj010.huaba.model.AuthToken;
import com.example.zyj010.huaba.model.Course;
import com.example.zyj010.huaba.model.User;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by runzii on 16-4-13.
 */
public interface ApiService {

    @GET("oauth/token")
    Observable<AuthToken> getAuthToken(@Header("Authorization") String authorization, @Query("username") String phone, @Query("password") String password, @Query("grant_type") String grant_type);

    @GET("courses/gettoken")
    Observable<ResponseBody> getUploadToken(@Header("Authorization") String authorization);

    @GET("/courses")
    Observable<List<Course>> getCourses(@Query("userid")long id, @Query("type") String type);
    @POST("/courses")
    Observable<ResponseBody> addCourse(@Header("Authorization") String authorization,@Body Course course);
    @GET("/users/self")
    Observable<User> getmyid(@Header("Authorization")String authorization);
    @GET("/courses")
    Observable<List<Course>> getAllCourses();
    @GET("/courses/videos/{vid}")
    Observable<ResponseBody> getVideoUri(@Header("Authorization") String authorization,@Path("vid")long vid);

}
