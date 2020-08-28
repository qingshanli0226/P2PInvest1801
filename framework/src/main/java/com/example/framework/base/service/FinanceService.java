package com.example.framework.base.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
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

import java.io.File;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FinanceService extends Service {

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

    public void autoLogin(final Context context){
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        //自动登录
        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        if(aBoolean){
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
                                //把token和登录状态存入SP文件中
                                editor.putString(FinanceConstant.TOKEN,autoLoginBean.getResult().getToken());
                                editor.putBoolean(FinanceConstant.ISLOGIN,true);
                                editor.commit();
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

}
