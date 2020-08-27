package com.p2p.bawei.p2pinvest1801.exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.common.bean.BaseBean;
import com.example.framwork.mvp.user.UserManagers;
import com.example.net.BaseObserver;
import com.example.net.NetFunction;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.p2p.bawei.p2pinvest1801.App;
import com.p2p.bawei.p2pinvest1801.Welecome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private String crashPath;
    private static CrashHandler crashHandler;
    private Context context;
    Thread.UncaughtExceptionHandler defalutExceptionHandler;
    public CrashHandler() {
    }

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull final Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(App.instace, "出现异常,正在重新启动 -----"+e.getCause()+"问题", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                saveException(e);
                crashReportToServer(e);
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        killProcess();
    }
    public void init(Context context) {
        this.context = context;
        defalutExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        crashPath = "/sdcard/tmp/crash/";
        File file = new File(crashPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    private void killProcess() {
        for (Activity activity:UserManagers.getInstance().getList_activity()) {
            activity.finish();
        }

        Intent intent = new Intent(context, Welecome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        System.exit(1);
        android.os.Process.killProcess(Process.myPid());
    }

    private void crashReportToServer(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String,String> map= new HashMap<>();
        map.put("message",printWriter.toString()+"\n----异常用户信息:"+UserManagers.getInstance().getetname()+"\n----版本信息:"+UserManagers.getInstance().getVersion());
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .crashReport(map)
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.i("----", s);
                    }

                    @Override
                    public void onRequestError(String code, String message) {

                    }
                });
    }

    private void saveException(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        File file = new File(crashPath + format + ".txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String string = stringWriter.toString();
            byte[] bytes = string.getBytes();
            int length = bytes.length;
            try {
                fileOutputStream.write(bytes,0,length);
                fileOutputStream.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
