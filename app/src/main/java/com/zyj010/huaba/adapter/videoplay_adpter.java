package com.zyj010.huaba.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.http.NetworkWrapper;
import com.zyj010.huaba.manage.AppPreferences;
import com.zyj010.huaba.model.Video;
import com.zyj010.huaba.utils.Toasts;
import com.zyj010.huaba.utils.VideoDownloadUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import io.vov.vitamio.widget.VideoView;
import rx.Subscriber;

/**
 * Created by zyj010 on 2016/5/24 0024.
 */
public class videoplay_adpter extends BaseAdapter {
    private List<Video> videos;
    private Context ct;
    private ViewHolder[] viewHolders;
    private Activity activity;
    private String coursename;
    VideoDownloadUtils videoDownloadUtils;
  public  videoplay_adpter(Context ct,List<Video> videos,Activity activity,String coursename){
      this.videos=videos;
      this.ct=ct;
      this.activity=activity;
      this.coursename=coursename;
      viewHolders=new ViewHolder[videos.size()];
  }
    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        Video video=null;
        Btn_play_Listener myListener=null;
        Btn_download_Listener btn_downd_listener=null;
        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_video_play,null);
            viewHolder=new ViewHolder();

            viewHolder.videoname= (TextView) convertView.findViewById(R.id.vedio_inf);
            viewHolder.download= (Button) convertView.findViewById(R.id.btn_video_download);
            viewHolder.play= (Button) convertView.findViewById(R.id.btn_video_start);
            viewHolder.progressBar= (ProgressBar) convertView.findViewById(R.id.download_progress);
            convertView.setTag(viewHolder);
            video=videos.get(position);
        }
    else {viewHolder= (ViewHolder) convertView.getTag();
            video=videos.get(position);}

        viewHolder.videoname.setText("第"+(position+1)+"集    "+video.getVideoname());
        viewHolders[position]=viewHolder;
        myListener=new Btn_play_Listener(position,viewHolder,video);
        btn_downd_listener=new Btn_download_Listener(position,viewHolder,video);
        viewHolder.play.setOnClickListener(myListener);
        viewHolder.download.setOnClickListener(btn_downd_listener);
        return convertView;
    }


    static class ViewHolder{
        TextView videoname;
        Button play;
        Button download;
        ProgressBar progressBar;
    }
    private class Btn_download_Listener implements View.OnClickListener{
                 int mPosition;
                 ViewHolder mviewholder;
                 Video myvideo;
        public Btn_download_Listener(int inPosition, ViewHolder viewholder,Video video){
            mPosition= inPosition;
            mviewholder=viewholder;
            myvideo=video;
        }
        @Override
        public void onClick(View v) {
            if(v.isSelected()==false){
                v.setSelected(true);
                upload(myvideo.getId());
            }
        }

        private void upload(long id) {
            Subscriber<String> subscriber=new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Toasts.show("获取视频失败");
                }

                @Override
                public void onNext(String s) {
                    star_upload(s,myvideo,mPosition,mviewholder);

                }


            };
            NetworkWrapper.get().getVideoUri(subscriber, AppPreferences.getInstance().getAuth(),id);
        }



    }
    private void star_upload(String s,Video myvideo,int mPosition,ViewHolder mviewholder) {
        final  String urlStr=s;
        String fileName=myvideo.getVideoname();

        String SDCard= Environment.getExternalStorageDirectory()+"";
        final String pathName=SDCard+"/HuaBa/"+coursename+"/";//文件存储路径
        File f=new File(pathName);

        final File file=new File(pathName+"第"+(mPosition+1)+"集"+fileName);
        if(!f.exists()){
            f.mkdirs();//新建文件夹
        }
        if(file.exists()){
            Toasts.show("文件已下载");
            return;
        }


        final android.os.Handler handler=new android.os.Handler(){

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                Bundle b = msg.getData();
                int precent=b.getInt("progress");
                switch(msg.what) {
                    case 0:Toasts.show("开始下载");
                        mviewholder.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case 1:mviewholder.download.setSelected(false);
                        break;
                    case 2:
                        mviewholder.progressBar.setProgress(videoDownloadUtils.getPrecent());
                        break;
                }
            }
        };
        videoDownloadUtils=new VideoDownloadUtils(handler,urlStr,file);
        videoDownloadUtils.startDownload();
//        new Thread(){
//            @Override
//            public void run() {
//                Looper.prepare();
//                handler.sendMessage(handler.obtainMessage(0,"start"));
//                try {
//                /*
//                 * 通过URL取得HttpURLConnection
//                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
//                 * <uses-permission android:name="android.permission.INTERNET" />
//                 */
//
//                    URL url = new URL(urlStr);
//                    URLConnection conn = url.openConnection();
//                    int contentLength = conn.getContentLength();
//
//                    //取得inputStream，并将流中的信息写入SDCard
//
//                /*
//                 * 写前准备
//                 * 1.在AndroidMainfest.xml中进行权限配置
//                 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//                 * 取得写入SDCard的权限
//                 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
//                 * 3.检查要保存的文件上是否已经存在
//                 * 4.不存在，新建文件夹，新建文件
//                 * 5.将input流中的信息写入SDCard
//                 * 6.关闭流
//                 */
//
//                    InputStream input = conn.getInputStream();
//                    OutputStream output = new FileOutputStream(file);
//                    //读取大文件
//                    byte[] buffer = new byte[1024];
//                    int len;
//                    int Count = 0;
//                    while ((len = input.read(buffer)) != -1) {
//                        output.write(buffer, 0, len);
//                        Count=Count+len;
//                        precent=Count*100/contentLength;
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("progress",precent);
//                        handler.sendMessage(handler.obtainMessage(2,bundle));
//                        output.flush();
//                    }
//                    input.close();
//                    if (output != null) {
//                        output.close();
//                    }
//
//                    Toasts.show("下载完成");
//                    handler.sendMessage(handler.obtainMessage(1,"下载结束"));
//                    Looper.loop();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


    }

    private class Btn_play_Listener implements View.OnClickListener {
        int mPosition;
        ViewHolder mviewholder;
        Video myvideo;
        public Btn_play_Listener(int inPosition, ViewHolder viewholder,Video video){
            mPosition= inPosition;
            mviewholder=viewholder;
            myvideo=video;
        }
        @Override
        public void onClick(View v) {
            VideoView videoview1= (VideoView) activity.findViewById(R.id.videoview_play);

            if(v.isSelected()==false){
                videoview1.stopPlayback();
                 v.setSelected(true);
                    biger();
                Toasts.show(myvideo.getId()+"");
                getPath(myvideo.getId());
             for(int i=0;i<getCount();i++){
                 if(i!=mPosition){

                     if(viewHolders[i].play.isSelected()==true){
                         viewHolders[i].play.setSelected(false);
                         smaller(viewHolders[i]);
                     }
                 }
             }
            }

        }

        private void getPath(long id) {
            Subscriber<String> subscriber=new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Toasts.show("获取视频失败");
                }

                @Override
                public void onNext(String s) {

                    play(s);
                }
            };
            NetworkWrapper.get().getVideoUri(subscriber, AppPreferences.getInstance().getAuth(),id);
        }

        private void play(String uri) {
            VideoView videoview= (VideoView) activity.findViewById(R.id.videoview_play);
            String SDCard= Environment.getExternalStorageDirectory()+"/HuaBa/"+coursename+"/";
            String path=SDCard+"第"+(mPosition+1)+"集"+myvideo.getVideoname();
            File file=new File(path);
            if(file.exists()){
                videoview.setVideoPath(path);
                videoview.requestFocus();

            }
     else {
                videoview.setVideoURI(Uri.parse(uri));
                videoview.requestFocus();
            }
        }

        private void smaller(ViewHolder mviewholder) {
            float Size=  mviewholder.videoname.getTextSize()/1.3f;
            mviewholder.videoname.setTextSize(TypedValue.COMPLEX_UNIT_PX ,Size);
            int download_width= (int) (mviewholder.download.getWidth()/1.3);
            int  download_height= (int) (mviewholder.download.getHeight()/1.3);
            mviewholder.download.setHeight(download_height);
            mviewholder.download.setWidth(download_width);


            int play_width= (int) (mviewholder.play.getWidth()/1.3);
            int  play_height= (int) (mviewholder.play.getHeight()/1.3);
            mviewholder.play.setWidth(play_width);
            mviewholder.play.setHeight(play_height);
        }

        private void biger() {
          float Size=  mviewholder.videoname.getTextSize()*1.3f;
            mviewholder.videoname.setTextSize(TypedValue.COMPLEX_UNIT_PX ,Size);
            int download_width= (int) (mviewholder.download.getWidth()*1.3);
            int  download_height= (int) (mviewholder.download.getHeight()*1.3);
            mviewholder.download.setHeight(download_height);
            mviewholder.download.setWidth(download_width);


            int play_width= (int) (mviewholder.play.getWidth()*1.3);
            int  play_height= (int) (mviewholder.play.getHeight()*1.3);
            mviewholder.play.setWidth(play_width);
            mviewholder.play.setHeight(play_height);


        }

    }

}
