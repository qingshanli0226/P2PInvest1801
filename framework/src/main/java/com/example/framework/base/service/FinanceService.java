package com.example.framework.base.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.common.CacheManager;
import com.example.common.FinanceConstant;
import com.example.framework.base.manager.UserManager;
import com.example.framework.base.until.EncryptUntil;
import com.example.net.FinanceManager;
import com.example.net.mode.AutoLoginBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FinanceService extends Service {

    private boolean isLogin;

    public class FinanceBinder extends Binder{

        public FinanceService getFinanceService(){
            return FinanceService.this;
        }
    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public IBinder onBind(Intent intent) {
        return new FinanceBinder();
    }
    //自动登录
    public void autoLogin(final Context context){
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();
        synchronized (this){
            isLogin = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        }
        if(isLogin){
            //登陆过一次
            TreeMap<String,String> params = new TreeMap<>();
            params.put("token", CacheManager.getInstance().getSharedPreferences().getString(FinanceConstant.TOKEN,""));
            //拼接
            StringBuilder sb = new StringBuilder();
            for(String key : params.keySet()){
                String value = params.get(key);
                sb.append(key+"="+value+"&");
            }
            sb.append("encrypt=md5");
            //进行信息摘要(MD5)
            String sign = EncryptUntil.enrypByMd5(sb.toString());
            //加入TreeMap中
            params.put("sign",sign);
            //生成Base64暗文传输
            TreeMap<String, String> stringStringTreeMap = EncryptUntil.encryptParamsValueByBase64(params);

            //发起自动登录的网络请求
            FinanceManager.getInstance().getFinanceApi()
                    .autoLogin(stringStringTreeMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AutoLoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AutoLoginBean autoLoginBean) {
                            if(autoLoginBean.getCode().equals("200")){
                                Toast.makeText(context, "自动登录成功", Toast.LENGTH_SHORT).show();
                                synchronized (this){
                                    //把token和登录状态存入SP文件中
                                    editor.putString(FinanceConstant.TOKEN,autoLoginBean.getResult().getToken());
                                    editor.putBoolean(FinanceConstant.ISLOGIN,true);
                                    editor.commit();
                                }
                            } else{
                                Toast.makeText(context, ""+autoLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            //没有登录  进入主页面去登录
        }
    }
    //下载文件
//    "http://49.233.93.155:8080/atguigu/apk/P2PInvest/app_new.apk"
    public void downLoadFile(String url){
        OkGo.<File>get(url)
                .execute(new FileCallback("/sdcard/","app_new.apk") {
                    @Override
                    public void onSuccess(Response<File> response) {
                        File file = response.body();
                        Log.i("hj", "onSuccess: "+file.getPath());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        Log.i("hj", "onError: "+response.getException()+"---"+response.message());
                    }
                });
    }

    //二次采样
    //通过地址的二次采样
    public Bitmap samplePicPath(int width, int height, String filePath) {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeFile(filePath,options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>height || picWidth/sampleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        return BitmapFactory.decodeFile(filePath, options);

    }

    //通过图片的二次采样的方法
    public Bitmap samplePic(ImageView imageView, Bitmap bitmap){

        int picWidth = bitmap.getWidth();
        int picHeight = bitmap.getHeight();
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>imageView.getHeight() || picWidth/sampleSize > imageView.getWidth()) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        //将bitmap转换成byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Bitmap samPlaceBitmap =  BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samPlaceBitmap;
    }

}
