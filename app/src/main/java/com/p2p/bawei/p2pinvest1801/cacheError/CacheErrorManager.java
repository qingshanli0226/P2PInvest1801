package com.p2p.bawei.p2pinvest1801.cacheError;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.bw.net.RetrofitManager;
import com.bw.net.api.Api;
import com.bw.net.bean.UploadBean;
import com.p2p.bawei.p2pinvest1801.manager.UserManager;
import com.p2p.bawei.p2pinvest1801.WelcomeActivity;

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

public class CacheErrorManager implements Thread.UncaughtExceptionHandler {

    private String crashPath;

    private Context context;
    private Thread.UncaughtExceptionHandler defalutExceptionHandler;

    private static CacheErrorManager cacheErrorManager;

    public static CacheErrorManager getInstance() {
        if (cacheErrorManager == null) {
            synchronized (String.class) {
                if (cacheErrorManager == null) {
                    cacheErrorManager = new CacheErrorManager();
                }
            }
        }
        return cacheErrorManager;
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        Log.i("wjh", e.getMessage() + "===" + e.getCause());

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
                saveExecptionInfo(e);
                crashReportToServer(e);
            }
        }).start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        killProcess(true);
    }


    public void init(Context context) {
        this.context = context;
        defalutExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();//把之前的处理器缓存起来
        Thread.setDefaultUncaughtExceptionHandler(this);

        //存储异常报告的目录
        crashPath = context.getExternalCacheDir() + "/crash/";
        File file = new File(crashPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     *
     * @param isResurrection 判断是否要复活本程序
     */
    public void killProcess(boolean isResurrection) {
        //想停掉这个应用，必须确保当前所有的Activity已经finish后才可以结束该进程，否则当前进程会一直存在

        for (Activity activity : UserManager.getInstance().getActivities()) {
            activity.finish();
        }

        if (isResurrection){
            Intent intent = new Intent();
            intent.setClass(context, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        System.exit(1);//结束进程
        android.os.Process.killProcess(Process.myPid());
    }


    private void saveExecptionInfo(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);
        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());

        File crashFile = new File(crashPath + timeStr + ".txt");
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
            } finally {
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

    private void crashReportToServer(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String, String> params = new HashMap<>();
        params.put("message", "wangmou:Error---"+stringWriter.toString());
        RetrofitManager.getInstance().retrofit
                .create(Api.class)
                .crashReport(params)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<UploadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        Log.w("wjh",uploadBean.getMessage());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
