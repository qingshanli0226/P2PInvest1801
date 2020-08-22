package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.lib_core.view.BaseFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.net.LruUtils;
import com.p2p.bawei.p2pinvest1801.net.NetUtils;
import com.p2p.bawei.p2pinvest1801.net.SDUtils;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class MyFragment_2 extends Fragment  {

    private ImageView imageView;
    private ImageView imageView2;

    private  LruUtils lruUtils= new LruUtils();//注意：同一个内存对象
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       inflate = inflater.inflate(R.layout.fragment_2, container, false);
        imageView = (ImageView) inflate.findViewById(R.id.imageView);
        imageView2 = (ImageView) inflate.findViewById(R.id.imageView_2);
        try {
            init_Click();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initData();
        return inflate;
    }

    private void initData() {
        OkGo.<File>get("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3995073927,3598178723&fm=26&gp=0.jpg").execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                File file = response.body();
                String path = file.getAbsolutePath();
                Bitmap bitmap = samplePic(imageView2.getWidth(),imageView2.getHeight(),path);
                imageView2.setImageBitmap(bitmap);
            }
        });
    }

    //二次采样
    private Bitmap samplePic(int width, int height, String path) {
        //第一次采集边框,计算出尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        //确定采集边框
        options.inJustDecodeBounds = true;
        //将这个图片解码
        BitmapFactory.decodeFile(path,options);
        //计算图片宽和高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //计算图片比例
        int sampleSize = 1;
        while (outWidth/sampleSize>width || outHeight/sampleSize>height){
            sampleSize=sampleSize*2;
        }
        //第一次采样结束
        //第二次采集像素
        options.inJustDecodeBounds=false;//设置不采集边框，采集像素
        //设置图片采集的比例
        options.inSampleSize=sampleSize;
        //设置图片采集的配置
        options.inPreferredConfig= Bitmap.Config.ARGB_8888;
        //返回采集好的数据
        return BitmapFactory.decodeFile(path,options);
    }

    private void init_Click() throws ExecutionException, InterruptedException {
        //先从内存中读取
        Bitmap bitmap = lruUtils.getBitmap("wby");
        if(bitmap!=null){//有图片
            imageView.setImageBitmap(bitmap);
            Toast.makeText(getContext(), "图片来自内存", Toast.LENGTH_SHORT).show();
        }else {
            //若内存没有去SD卡
            bitmap= SDUtils.getBitmap("wby.jpg");
            if(bitmap!=null){
                imageView.setImageBitmap(bitmap);
                Toast.makeText(getContext(), "图片来自SD卡", Toast.LENGTH_SHORT).show();
                //向内存中存储一下
                lruUtils.setBitmap("wby",bitmap);
            }else {
                //运行内存和SD卡都没有图片，接下来网络获取
                bitmap= NetUtils.getBitmap("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3995073927,3598178723&fm=26&gp=0.jpg");
                if(bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                    Toast.makeText(getContext(), "图片从网络获取", Toast.LENGTH_SHORT).show();


                    //将图片再放到SD卡和内存中
                    SDUtils.setBitmap("wby.jpg",bitmap);
                    lruUtils.setBitmap("wby",bitmap);
                }else {
                    Toast.makeText(getContext(), "网络未连接...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
