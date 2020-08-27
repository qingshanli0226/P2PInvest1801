package com.p2p.bawei.p2pinvest1801.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.util.LruCache;

import java.io.File;
import java.io.IOException;


/**
 * 自己写的glide
 */
public class GlideManager {

    public static GlideManager instance;
    private File fileDir;//路径
    private Context context;//上下文
    private DiskLruCache diskLruCache;
    private LruCache<String, Bitmap> lruCache;

    //无参构造
    private GlideManager(){
    }

    public static GlideManager getInstance(){
        if (instance==null){
            instance = new GlideManager();
        }
        return instance;
    }

    //初始化
    public void init(Context context){
        this.context = context;

        fileDir = new File(context.getExternalCacheDir().getAbsoluteFile() + "/p2p/");

        if (!fileDir.exists()){
            fileDir.mkdir();
        }

        try {
            //使用缩略图,磁盘内存最多使用1个G的空间,如果超出则默认删除最先存储的图片
            DiskLruCache.open(fileDir,1,1,1000*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //实例化最大内存空间
        lruCache = new LruCache<>(Runtime.getRuntime().maxMemory()/8);

    }


    //从三级缓存中读取图片
    public Bitmap getBitmapFromMen(String key){
        synchronized (lruCache){
            return lruCache.get(key);
        }
    }

}
