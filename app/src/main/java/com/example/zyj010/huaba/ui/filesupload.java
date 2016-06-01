package com.example.zyj010.huaba.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zyj010.huaba.R;
import com.example.zyj010.huaba.http.HttpMethods;
import com.example.zyj010.huaba.manage.AppPreferences;
import com.example.zyj010.huaba.utils.Toasts;
import com.example.zyj010.huaba.utils.UploadUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;

import org.json.JSONObject;

import java.io.File;

import rx.Subscriber;

import static com.example.zyj010.huaba.utils.FileUtils.getRealPathFromURI;

/**
 * Created by zyj010 on 2016/5/23 0023.
 */
public class filesupload extends Activity  implements UpCancellationSignal, UpCompletionHandler, UpProgressHandler {
    private static final int READ_REQUEST_CODE = 1337;

    public static final String TAG = "upload_activity";
    private ImageView iv;
    private TextView videoname;
    private Button upload;
    private Uri uri;
    private TextView tv_addfile;
    private TextView tv_state;
    private ProgressBar progressbar;
    private long id;
    private ImageView back;
    private String  video=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        id =intent.getLongExtra("id",0);

        setContentView(R.layout.activity_upload);
        init();
    }

    private void init() {
        iv= (ImageView) findViewById(R.id.iv_main);
        videoname= (TextView) findViewById(R.id.tv_videoname);
        tv_addfile= (TextView) findViewById(R.id.add_file);
        tv_state= (TextView) findViewById(R.id.tv_upload_state);
        upload= (Button) findViewById(R.id.upload_pause);
        progressbar= (ProgressBar) findViewById(R.id.upload_progress);
        back= (ImageView) findViewById(R.id.back_upload);
        tv_addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFiles();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uri==null){
                    Toasts.show("未选择文件");
                    return;
                }
                if(progressbar.getProgress()!=0){return;}

                upload_start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void upload_start() {
        HttpMethods.getInstance()
                .getUploadToken(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        AppPreferences.getInstance().clearAll();
                    }

                    @Override
                    public void onNext(String s) {
//                        Toasts.show(s);
                        uploadFile(s);
                    }
                }, AppPreferences.getInstance().getAuth());
    }

    public void uploadFile(String token) {
        UploadUtils.PUT(token, uri, this, this, this,id,video);
    }
    private void getFiles() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a file (as opposed to a list
        // of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers, it would be
        // "*/*".
        intent.setType("video/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code READ_REQUEST_CODE.
        // If the request code seen here doesn't match, it's the response to some other intent,
        // and the below code shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.  Pull that uri using "resultData.getData()"
            Uri uri = null;
            File file=null;
            String path = null;
            if (resultData != null) {
                uri = resultData.getData();
                String filePath =  getRealPathFromURI(uri);
                Log.i(TAG, "Uri: " + uri.toString());
                Glide.with(this).load(uri).into(iv);
                path = uri.getEncodedPath();


                String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
                this.videoname.setText(fileName);
                video=fileName;
                this.uri = uri;
                progressbar.setProgress(0);
            }
        }
    }
    @Override
    public void complete(String key, ResponseInfo info, JSONObject response) {
        Log.d(TAG,info.toString());
    }
    @Override
    public void progress(String key, double percent) {
        progressbar.setProgress((int) (percent * 100));
        if(progressbar.getProgress()==100){
                Toasts.show("上传完成");
            tv_state.setText("上传成功");
        }
        if(progressbar.getProgress()>0&&progressbar.getProgress()<100){
            tv_state.setText("正在上传");
        }
    }

    @Override
    public boolean isCancelled() {
        return false;
    }


}
