package com.p2p.bawei.p2pinvest1801.net;

import android.graphics.Bitmap;
import android.util.LruCache;

//操作内存工具类：提供从内存中读写的方法，内存不能持久保存，可能过一会就会被回收掉
//Lrucache存储工具类
public class LruUtils {
    //实例化LruCache对象
    private LruCache<String, Bitmap> lruCache;
    //获取手机的最大内存
    private long max = Runtime.getRuntime().maxMemory();

    public LruUtils(){

        lruCache = new LruCache<String,Bitmap>((int)max/8){//给内存大小，一般是最大内存的1/8


            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    //读图片
    public Bitmap getBitmap(String key){
        return lruCache.get(key);
    }

    //存图片
    public void setBitmap(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }
}
