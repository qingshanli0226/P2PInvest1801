package com.p2p.bawei.p2pinvest1801.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


//网络下载工具类：提供下载图片的方法
//网络获取工具类
public class NetUtils {
    //获取网络图片
    public static Bitmap getBitmap(String url)throws ExecutionException, InterruptedException{
        return new MyTask().execute(url).get();//get方法获取执行完毕返回的结果Bitmap对象
    }

    static class MyTask extends AsyncTask<String,String,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageUrl  = strings[0];
            HttpURLConnection httpURLConnection =null;
            try {
                URL url = new URL(imageUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);

                if(httpURLConnection.getResponseCode() == 200){
                    InputStream inputStream = httpURLConnection.getInputStream();//获取流数据
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);//将流数据转换成Bitmap对象
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection==null){
                    httpURLConnection.disconnect();//断开连接
                }
            }
            return null;
        }
    }
}
