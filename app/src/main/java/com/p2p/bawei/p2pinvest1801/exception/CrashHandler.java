package com.p2p.bawei.p2pinvest1801.exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.framework2.manager.CacheManager;
import com.example.net.activity_bean.BaseBean;
import com.example.net.api_srever.ApiServer;
import com.example.net.http.HttpManager;
import com.example.net.http.NetMapFunction;
import com.p2p.bawei.p2pinvest1801.App;
import com.p2p.bawei.p2pinvest1801.home.WelcomeActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private String crashPath;
    private Context context;
    private Thread.UncaughtExceptionHandler handler;
    private static  CrashHandler instance;
    public static CrashHandler getInstance(){
        if (instance==null){
            instance=new CrashHandler();
        }
        return instance;
    }
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull final Throwable e) {
        Log.d("fff","uncaughtException"+e.getCause());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "程序出现异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                saveExecptionInfo(e);
                crashReportToServer(e);
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        killApp();
    }

    private void killApp() {
        for(Activity activity: CacheManager.getInstance().getActivityList()) {
            activity.finish();
        }

        Intent intent = new Intent();
        intent.setClass(App.instance, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.instance.startActivity(intent);

        System.exit(1);//结束进程
        android.os.Process.killProcess(Process.myPid());
    }
    public void init(Context context) {
        this.context = context;
        handler = Thread.getDefaultUncaughtExceptionHandler();//把之前的处理器缓存起来
        Thread.setDefaultUncaughtExceptionHandler(this);


        crashPath = App.instance.getExternalCacheDir()+"/crash/";
        File file = new File(crashPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    private void saveExecptionInfo(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());
        File file = new File(crashPath + timeStr + ".txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream= new FileOutputStream(file);
            String crashStr = stringWriter.toString();
            byte[] bytes = crashStr.getBytes();
            int length = bytes.length;
            fileOutputStream.write(bytes,0,length);
            fileOutputStream.flush();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void crashReportToServer(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String,String> params = new HashMap<>();
        params.put("message", "任鹏飞"+stringWriter.toString());
        HttpManager.getHttpManager().getRetrofit()
                .create(ApiServer.class)
                .crashReport(params)
                .subscribeOn(Schedulers.io())
                .map(new NetMapFunction<BaseBean<String>,String>())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("fff ", s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fff", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
