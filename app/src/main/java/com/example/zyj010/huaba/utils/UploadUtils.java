package com.example.zyj010.huaba.utils;

import android.net.Uri;

import com.example.zyj010.huaba.ui.UploadActivity;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by runzii on 16-4-13.
 */
public class UploadUtils {
    private static UploadManager uploadManager;

    public static void PUT(String token, Uri uri, UpCompletionHandler completionHandler, UpCancellationSignal signal, UpProgressHandler progressHandler,long id,String videoname) {
        if (uploadManager == null) {
            uploadManager = new UploadManager();
        }
        Map<String, String> params = new HashMap<>();
        params.put("x:cid", ""+id);
        params.put("x:videoname", videoname);
        UploadOptions options = new UploadOptions(params, null, false, progressHandler, signal);
        uploadManager.put(FileUtils.getRealPathFromURI(uri), null, token,
                completionHandler, options);
    }


}
