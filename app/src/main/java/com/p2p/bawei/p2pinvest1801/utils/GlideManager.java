package com.p2p.bawei.p2pinvest1801.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.p2p.bawei.p2pinvest1801.bean.GlideTask;

import java.io.File;
import java.io.IOException;

public class GlideManager {
    private Context context;
    private File fileDir;
    private DiskLruCache diskLruCache;
    private LruCache<String, Bitmap> lruCache;
    private GlideManager(){

    }
    private static GlideManager instance;
    public static GlideManager getInstance(){
        if (instance == null) {
            instance = new GlideManager();
        }
        return instance;
    }
    public void init(Context context){
        this.context=context;
        //是放到sd卡上的地址
        fileDir= new File(context.getExternalCacheDir().getPath()+"/1801/");
        //!存在exists(存在)
        if (!fileDir.exists()){
            Log.e("cx", "init: 判断是否在sd卡有" );
            fileDir.mkdir();
        }
        try {
            //使用DiskLrucache缩略图,磁盘最多空间
             diskLruCache = DiskLruCache.open(fileDir, 1, 1, 1000 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //实例化内存缓存,内存空间最大值
         lruCache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/8));

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    GlideTask glideTask = (GlideTask) msg.obj;
                    glideTask.getImageView().setImageBitmap(glideTask.getBitmap());
                    //存入内存
//                    setBitmapToMe(EncryptUtil.encryptJsonByMd5(glideTask.getUrl()),glideTask.getBitmap());
                    break;
            }
        }
    };
    //内存读
    public Bitmap getBitmapFromMem(String key){
        //保护
        synchronized (lruCache){
            return lruCache.get(key);
        }
    }
    //内存写
    public void setBitmapToMe(String key,Bitmap bitmap){
        synchronized (lruCache){
            lruCache.put(key,bitmap);
        }
        Log.e("cx", "setBitmapToMe: "+lruCache.size() );
    }

}


