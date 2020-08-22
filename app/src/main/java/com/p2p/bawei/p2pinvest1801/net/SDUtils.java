package com.p2p.bawei.p2pinvest1801.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//操作SD卡工具类：提供从SD卡中读写的方法
//读图片和写图片
public class SDUtils {
    //存图片：bitmap.compress()
    public static void setBitmap(String name, Bitmap bitmap){
        //获取路径存储图片
        if(Environment.getExternalStorageState().equals(Environment.DIRECTORY_PICTURES)){

            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file1 = new File(file, name);

            //存储图片：bitmap对象----》SD卡
            try {
                //参数一：图片格式，参数二：图片质量0-100，参数三：输出流
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //读图片：BitMapFactory.decodeFile()
    public static Bitmap getBitmap(String name){
        //获取路径存储图片
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file1 = new File(file, name);
            //读取图片：SD卡----Bitmap
            return BitmapFactory.decodeFile(file1.getAbsolutePath());
        }
        return null;
    }
}
