package com.p2p.bawei.p2pinvest1801.ex;


/*

事故处理程序
用来捕获一些未知的异常信息,并上传到服务端

 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.example.lib_core.http.Manager;
import com.p2p.bawei.p2pinvest1801.app.APP;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private String crashPath;//获取的异常

    private Context context;

    public static CrashHandler instance;

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static CrashHandler getInstance(){
        if (instance==null){
            instance = new CrashHandler();
        }
        return instance;
    }

    //当程序出现异常时在这个方法里进行处理
    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        Log.d("shw", "uncaughtException: " + e.getCause());
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在这两个中间和在主线程中没有区别
                Looper.prepare();
                Toast.makeText(context, "程序出现异常,准备重启", Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                saveExceptionInfo(e);
                crashReportToServer(e);
            }
        });

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        killProcess();
    }

    //给服务器的崩溃报告
    private void crashReportToServer(Throwable e) {



    }

    //用来存储错误信息
    private void saveExceptionInfo(Throwable e) {

        //将异常信息存在输入流
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        //生成文件
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        File file = new File(crashPath + format + ".txt");
        Log.e("aaaaa", "saveExceptionInfo: "+file );

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String s = stringWriter.toString();//获取异常信息
            try {
                fileOutputStream.write(s.getBytes(),0,s.length());//进行存储
                fileOutputStream.flush();//刷新
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally {
                try {
                    fileOutputStream.close();//关流
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


    }


    //杀死进程
    private void killProcess(){

        for (Activity activity: Manager.getInstance().getActivity_list()){
            activity.finish();
        }

        //杀死之前拉活一个activity
        Intent intent = new Intent();
        intent.setClass(APP.instance, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        APP.instance.startActivity(intent);

        //结束进程
        System.exit(1);
        android.os.Process.killProcess(Process.myPid());
    }

    public void init(Context context){
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();//吧之前的处理器缓存起来
        Thread.setDefaultUncaughtExceptionHandler(this);
        //存储异常报告目录
//        Log.e("aaaaaaaaaaa", "init: "+APP.instance.getExternalCacheDir() );
//        crashPath = APP.instance.getExternalCacheDir()+"/sdcard/Pictures/";
//        File file = new File(crashPath);
//        if (!file.exists()){
//            file.mkdir();
//        }

    }

}
