package com.p2p.bawei.p2pinvest1801.execption;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.common.Llog;
import com.example.framwork.manager.CacheManager;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.App;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.welcome.WelcomeActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 实现一个接口，该接口可以接收到，应用程序未捕获到的异常事件，
 * 在该方法中可以处理该异常信息,例如将异常信息存储到本地，推送到服务端，给用户提示，启动一个新的应用进程
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandlerAA";

    //实现单例
    private static CrashHandler instance;

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }


    private String crashPath;

    private Context context;
    private Thread.UncaughtExceptionHandler defalutExceptionHandler;


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull final Throwable e) {
        Llog.d("" + e.getCause());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(App.instance, "程序出现异常，已经重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                saveExecptionInfo(e); //存储到本地
                crashReportToServer(e);

            }
        }).start();


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        killProcess();
    }

    private void saveExecptionInfo(Throwable e) {
        //将异常信息放到一个输出流里
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());

        File crashFile = new File(crashPath +"/"+ timeStr + ".txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(crashFile);
            String crashStr = stringWriter.toString();
            byte[] crashByteArray = crashStr.getBytes();
            int length = crashByteArray.length;
            try {
                fileOutputStream.write(crashByteArray, 0, length);
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

    public void init(Context context) {
        this.context = context;

        defalutExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();//把之前的处理器缓存起来
        Thread.setDefaultUncaughtExceptionHandler(this);

        //存储异常报告的目录
        crashPath = "/sdcard/data/crashException";
        File file = new File(crashPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void crashReportToServer(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String,String> params = new HashMap<>();
        params.put("message", stringWriter.toString());

//        NetRetrofitManager.getInstance().getRetrofit().create(Api.class)
//                .crashReport(params)
//                .subscribeOn(Schedulers.io())
//                .map(new NetFunction<BaseBean<String>, String>())
//                .observeOn(Schedulers.io())
//                .subscribe();
    }

    private void killProcess() {

        //想停掉这个应用，必须确保当前所有的Activity已经finish后才可以结束该进程，否则当前进程会一直存在

        for (Activity activity : CacheManager.getInstance().getActivityList()) {
            activity.finish();
        }

        Intent intent = new Intent();
        intent.setClass(App.instance, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.instance.startActivity(intent);

        System.exit(1);//结束进程
        android.os.Process.killProcess(Process.myPid());
    }

}
