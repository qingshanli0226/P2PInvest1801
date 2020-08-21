package com.p2p.bawei.p2pinvest1801.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存
 */
public class LruUtils {
    //实例化lrucachae对象
    private LruCache<String, Bitmap> lruCache;
    private long max=Runtime.getRuntime().maxMemory();//获取手机的最大内存
    public LruUtils(){
        //给内存大小,一般是最大内容的8分之1
        lruCache=new LruCache<String,Bitmap>((int) (max/8)){
            //重写该方法返回内个对象的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }
    //读取图片
    public Bitmap getBitmap(String key){
        return lruCache.get(key);
    }
    //存图片
    public void setBitmap(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }

}
