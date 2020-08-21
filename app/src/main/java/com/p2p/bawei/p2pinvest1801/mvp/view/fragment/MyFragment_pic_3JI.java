package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.example.common.mvp.view.BaseFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;

import java.io.File;

public class MyFragment_pic_3JI extends BaseFragment {
    private ImageView picImage;
    @Override
    public int BondLayout() {
        return R.layout.myfragment_pic_3ji;
    }

    @Override
    public void initview() {
        picImage = (ImageView) findViewById(R.id.picimage);
    }

    @Override
    public void initdata() {
        OkGo.<File>get("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png").execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                File file = response.body();
                String path = file.getAbsolutePath();
                Bitmap bitmap = samplePic(picImage.getWidth(),picImage.getHeight(),path);
                picImage.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void initInJect() {

    }
    //三级缓存




    //二次采样
    private Bitmap samplePic(int width,int height,String filepath){
        //第一次采集边框,计算出尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;//确定采集边框
        BitmapFactory.decodeFile(filepath,options);//将这个图片解码
        //计算出图片的宽和高
        int picHeight = options.outHeight;
        int picWidth = options.outWidth;
        //计算图片比例
        int samplesize=1;
        while (picHeight/samplesize>height || picWidth/samplesize>width){
            samplesize=samplesize*2;
        }
        //第一次采集结束
        //第二次采集像素
        options.inJustDecodeBounds=false;//设置不采集边框,采集像素
        options.inSampleSize=samplesize;//设置图片采集的比例
        options.inPreferredConfig=Bitmap.Config.ARGB_8888;//设置采集的图片的配置
        //返回采集好的数据
        return BitmapFactory.decodeFile(filepath,options);
    }
}
