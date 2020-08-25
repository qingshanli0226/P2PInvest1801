package com.p2p.bawei.p2pinvest1801.sample;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class SampleActivity {
    private DiskLruCache diskLruCache;
    private void initDiskLru(){
        File file = new File("/sdcard/bitmap/");
        if (!file.exists()){
            file.mkdir();
        }
        try {
            diskLruCache = DiskLruCache.open(file, 1, 1, 1024 * 1024 * 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Bitmap samplePICWithBirmapN(int width,int height, Bitmap bitmap){
        int picWidth = bitmap.getWidth();
        int picHeight =bitmap.getHeight();
        int sampleSize=1;
        while (picHeight/sampleSize>height || picWidth/sampleSize>width){
            sampleSize=sampleSize*2;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=false;
        options.inSampleSize=sampleSize;
        options.inPreferredConfig=Bitmap.Config.ARGB_8888;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] data= byteArrayOutputStream.toByteArray();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return bitmap1;

    }


    private void downloadUriToDisk(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DiskLruCache.Editor editor=diskLruCache.edit("111");
                    if (editor!=null){
//                        OutputStream outputStream=editor.st
                        URL url = new URL("");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
