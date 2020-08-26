package com.p2p.bawei.p2pinvest1801.exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.example.common.http.HttpManager;
import com.p2p.bawei.p2pinvest1801.application.AppApplication;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//实现一个接口，该接口可以接收到，应用程序未捕获到的异常事件，在该方法中可以处理该异常信息,例如将异常信息存储到本地，推送到服务端，给用户提示，启动一个新的应用进程
public class CrashHandler  implements Thread.UncaughtExceptionHandler{
    //目录
    private String crashpath;
    //上下文
    private Context context;
    private Thread.UncaughtExceptionHandler defalutexceptionHandler;


    //单例
    private static CrashHandler crashHandler;
    public static CrashHandler getInstance(){
        if (crashHandler==null){
            crashHandler=new CrashHandler();
        }
        return crashHandler;
    }



    @Override
    public void uncaughtException( Thread t,  Throwable e) {
        Log.e("ex", "uncaughtException: 进入异常捕获"+e.getCause() );
        //子线程提示
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "应用出现异常,系统即将崩溃!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //缓冲到文件里
                saveExecptionInfo(e);
                //
//                crashReportToServer(e);
            }
        }).start();

        //睡眠一段时间为提示和关掉app留时间
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //关掉app应用
        killProcess();
    }

    private void killProcess() {
        //想停掉这个应用，必须确保当前所有的Activity已经finish后才可以结束该进程，否则当前进程会一直存在
        List<Activity> activityList = HttpManager.getInstance().getActivityList();
        for (Activity activity : activityList) {
            activity.finish();
        }
        Intent intent = new Intent();
        intent.setClass(AppApplication.applicationthis, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppApplication.applicationthis.startActivity(intent);
        //结束进程
        System.exit(1);
        android.os.Process.killProcess(Process.myPid());
    }

    private void crashReportToServer(Throwable e) {
        //将异常信息放在一个流中
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        HashMap<String,String> map = new HashMap<>();
        map.put("messages",stringWriter.toString());
//        Disposable subscribe = HttpManager.getInstance().getRetrofit().create(Api.class)
//                .crashReport(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe();


    }

    private void saveExecptionInfo(Throwable e) {
        //将异常信息放在一个流中
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        File file = new File(crashpath + format + ".text");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String s = stringWriter.toString();
            byte[] bytes = s.getBytes();
            int length = bytes.length;
            fileOutputStream.write(bytes,0,length);
            fileOutputStream.flush();
         fileOutputStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    //初始化
    public void init(Context context){
        this.context=context;
        //把之前的缓冲器保存起来
        defalutexceptionHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        //存储异常报告的目录
        crashpath= AppApplication.applicationthis.getExternalCacheDir()+"/crash/";
        File file = new File(crashpath);
        if (!file.exists()){
            file.mkdir();
        }
    }
}
