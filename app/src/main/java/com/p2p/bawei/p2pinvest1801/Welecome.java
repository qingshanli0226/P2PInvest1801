package com.p2p.bawei.p2pinvest1801;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.user.UserService;
import com.example.framwork.mvp.view.BaseActivity;
import com.example.net.BaseObserver;
import com.example.net.NetModel;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.common.bean.VersionBean;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.p2p.bawei.p2pinvest1801.exception.CrashHandler;
import com.p2p.bawei.p2pinvest1801.mvp.view.MainActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//欢迎页
public class Welecome extends BaseActivity implements CacheManager.IDataChangeListener{
    private int handlercount = 0;
    private ServiceConnection serviceConnection;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    handlercount++;
                    if(handlercount == 3){
                        showlog();
                    }
                    break;
                case 1:
                    count--;
                    welcomeCount.setText("倒计时:"+count+"");
                    break;
                case 2:
                    handlercount++;
                    if(handlercount == 3){
                        if(version.equals(UserManagers.getInstance().getVersion())){
                            Intent intent = new Intent(Welecome.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            showlog();
                        }
                    }
                    break;
            }
        }
    };
    private String title;
    //打开对话框
    private void showlog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("下载最新版本");
        builder.setMessage(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转主页面
                Intent intent = new Intent(Welecome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(Welecome.this,UserService.class);
//                        serviceConnection = new ServiceConnection() {
//                            @Override
//                            public void onServiceConnected(ComponentName name, IBinder service) {
//                                UserService.MyBind myBinder = (UserService.MyBind) service;
//                                myBinder.getBind().StartNotification();
//                            }
//
//                            @Override
//                            public void onServiceDisconnected(ComponentName name) {
//
//                            }
//                        };
//                        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
//                    }
//                }).start();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private TextView welcomeCount;
    private int count = 3;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    String version = "1.1";
    //初始化
    @Override
    public void initViews() {
        welcomeCount = (TextView) findViewById(R.id.welcome_count);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        CacheManager.getInstance().registerDataChangeListener(Welecome.this);
        //在欢迎页面进行单例类传值
        CrashHandler.getInstance().init(this);
        CacheManager.getInstance().init(this);
        UserManagers.getInstance().init(this);
        NetModel.init(this);
        sharedPreferences = getSharedPreferences("1801A", Context.MODE_PRIVATE);
        edit= sharedPreferences.edit();
    }

    @Override
    public void initDatas() {
        //开启一个线程进行首页缓存
        cachehome();
        //开启一个线程进行倒计时
        countdown();
        //网络请求版本
        getVersion();
    }

    private void getVersion() {
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .versionlist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<VersionBean>() {
                    @Override
                    public void onNext(VersionBean versionBean) {
                        if(versionBean.getCode() == 200){
                            title = versionBean.getResult().getDesc();
                            handler.sendEmptyMessage(2);
                            UserManagers.getInstance().setVersion(versionBean.getResult().getVersion());
                            Log.i("----111", versionBean.getResult().getApkUrl());
                            UserManagers.getInstance().setVersionurl(versionBean.getResult().getApkUrl());
                            Log.i("----version", "1111");
                        }else{
                            Toast.makeText(Welecome.this, "请求出错", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onRequestError(String code, String message) {

                    }
                });
    }

    private void countdown() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Welecome.this){
                    for (int i = 3; i > 0; i--) {
                        handler.sendEmptyMessage(1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.sendEmptyMessage(0);
                    Log.i("----for", "1111");
                }
            }
        }).start();
    }

    private void cachehome() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Welecome.this){
                    CacheManager.getInstance().initInter();
                }
            }
        }).start();
    }

    @Override
    public int bandLayout() {
        return R.layout.welcome;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //handler回收
        handler.removeMessages(0);
        handler.removeMessages(1);
        handler.removeMessages(2);
        //解除绑定
        UserManagers.getInstance().removeService();
        //清除
        CacheManager.getInstance().unRegisterDataChangeListener(this);
    }

    @Override
    public void onChange() {
        //请求到数据之后在进行handler发送
        Log.i("----change","1111");
        CacheManager.getInstance().add();
        handler.sendEmptyMessage(0);
    }
}
