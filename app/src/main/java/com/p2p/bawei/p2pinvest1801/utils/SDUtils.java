package com.p2p.bawei.p2pinvest1801.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * sd卡
 */
public class SDUtils {
    //存图片
    public static  void setBitmap(String name, Bitmap bitmap){
        //获取路径存储图片
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file1 = new File(file, name);
            //存储图片:bitmap对象------>sd卡
            try {
                //参数一图片的格式,二图片的质量0-100,三输出流
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    //读取图片:Bitmapfile文件
    public static Bitmap getBitmap(String name){
        //获取路径存储图片
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Environment.getExternalStoragePublicDirectory()
        }
    }

}
