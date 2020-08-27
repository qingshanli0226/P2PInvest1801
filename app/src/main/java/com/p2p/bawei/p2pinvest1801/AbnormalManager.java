package com.p2p.bawei.p2pinvest1801;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.baselibrary.CacheManager;
import com.example.net.BaseBean;
import com.example.net.http.HttpManager;
import com.example.net.http.NetFunction;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.welcome.view.WelComeAct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.schedulers.Schedulers;

public class AbnormalManager implements Thread.UncaughtExceptionHandler {
    private Context context;
    private static AbnormalManager abnormalManager;
    private String crashPath;

    public static AbnormalManager getAbnormalManager() {
        if (abnormalManager == null) {
            abnormalManager = new AbnormalManager();
        }
        return abnormalManager;
    }

    public void init(Context context) {
        this.context = context;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        //存储异常报告的目录
        crashPath = App.app.getExternalCacheDir() + "/crash/";
        Log.e("AbnormalManager", "init: " + crashPath);
        File file = new File(crashPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull final Throwable throwable) {
        Throwable cause = throwable.getCause();
        Log.d("AbnormalManager", "cause:" + throwable.getCause());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "程序出现异常，已经重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                keepLoad(throwable);
                UpLoad(throwable);
            }
        }).start();


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        killProcess();
    }

    private void UpLoad(Throwable throwable) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);

        HashMap<String, String> params = new HashMap<>();
        params.put("message", stringWriter.toString());
        HttpManager.getHttpManager().getRetrofit().create(Api.class)
                .upData(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(Schedulers.io())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void success(String str) {
                        Log.e("hq", "success: " + str);
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void error(String errorMessage) {
                        Log.e("hq", "success: " + errorMessage);
                    }
                });
    }

    private void keepLoad(Throwable throwable) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);

        //生成一个文件名
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());

        File crashFile = new File(crashPath + timeStr + ".txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(crashFile);
            String s = stringWriter.toString();
            byte[] bytes = s.getBytes();
            int len = bytes.length;
            fileOutputStream.write(bytes, 0, len);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void killProcess() {
        //想停掉这个应用，必须确保当前所有的Activity已经finish后才可以结束该进程，否则当前进程会一直存在

        for (Activity activity : CacheManager.getCacheManager().getActivityList()) {
            activity.finish();
        }

        Intent intent = new Intent();
        intent.setClass(App.app, WelComeAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.app.startActivity(intent);

        System.exit(1);//结束进程
        android.os.Process.killProcess(Process.myPid());
    }
}
