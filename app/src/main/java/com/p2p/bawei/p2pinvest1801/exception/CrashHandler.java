package com.p2p.bawei.p2pinvest1801.exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.bw.lib_core.bean.BaseBean;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.p2p.bawei.p2pinvest1801.MyApplication;
import com.p2p.bawei.p2pinvest1801.api.MyApi;
import com.p2p.bawei.p2pinvest1801.view.activity.WelComeActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private String crashPath;//路径

    private Context context;
    private Thread.UncaughtExceptionHandler defalutExceptionHandler;

    private static CrashHandler instance;

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {

        Log.d("WBY", "uncaughtException: "+e.getCause());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(MyApplication.instance, "程序出现异常，已经重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                saveExecptionInfo(e);
                //crashReportToServer(e);
            }
        }).start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        killProcess();
    }

    private void saveExecptionInfo(Throwable e){
        //将一个异常信息放在输出流里
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        File file = new File(crashPath + format + ".txt");
        Log.d("wby", "saveExecptionInfo: "+file.getPath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String s = stringWriter.toString();
            byte[] bytes = s.getBytes();
            int length = bytes.length;

            try {
                fileOutputStream.write(bytes,0,length);
                fileOutputStream.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                fileOutputStream.close();//关流
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void crashReportToServer(Throwable e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("message",stringWriter.toString());
        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .crashReport(hashMap)
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                        Log.d("WBY", "onNext: "+s);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("WBY", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void killProcess() {

        //想停掉这个应用，必须确保当前所有的Activity已经finish后才可以结束进程，否则当前进程会一直存在
        for (Activity activity:HttpRetrofitManager.getInstance().getActivityList()){
            activity.finish();
        }

        Intent intent = new Intent();
        intent.setClass(MyApplication.instance, WelComeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.instance.startActivity(intent);

        System.exit(1);//结束进程
        Process.killProcess(Process.myPid());
    }

    public void init(Context context){
        this.context = context;
        //把之前的处理器缓存
        defalutExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        //存储异常报告的目录
        crashPath = MyApplication.instance.getExternalCacheDir()+"/crash/";
        File file = new File(crashPath);
        if(!file.exists()){
            file.mkdir();
        }
    }

}
