package com.example.zyj010.huaba.http.Utils;

/**
 * Created by zyj010 on 2016/4/25 0025.
 */
public interface HttpCallback {
    //操作成功，达到预期目标
    void onSuccess(Object object);

    //操作失败
    void onFailed(String reason);

    //网络连接错误
    void onError(int statusCode);
}
