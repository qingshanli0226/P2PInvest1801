package com.p2p.bawei.p2pinvest1801;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.common.CacheManager;
import com.example.framework.base.manager.UserManager;
import com.example.net.FinanceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private String crashPath;

    private FileOutputStream fileOutputStream;

    private CrashHandler() {
    }

    private static CrashHandler crashHandler;

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    public void init(Context context){
        this.context = context;
        //把之前的处理器缓存起来
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        //储存异常文件的目录
        crashPath = FinanceApplication.financeApplication.getExternalCacheDir() + "/crash/";
        File file = new File(crashPath);
        //如果目录不存在就创建
        if(!file.exists()){
            file.mkdir();
        }
    }

    @Override
    public void uncaughtException(@NonNull Thread t,final  @NonNull Throwable e) {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "程序出现异常,已经重新启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //用文件保存报错的信息
                saveExceptionInfo(e);
                //上传到服务器
                crashReportToServer(e);
            }
        }).start();

        //睡眠  保证吐司和保存文件上传服务器的进行
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //摧毁这个进程
        killProcess();
    }

    private void crashReportToServer(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("用户信息：黄俊:   ");
//        stringWriter.append("用户信息："+UserManager.getInstance().getUserName());
        stringWriter.append("版本信息："+UserManager.getInstance().getVersion()+"  ");
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        HashMap<String,String> params = new HashMap<>();
        params.put("message",stringWriter.toString());
        FinanceManager.getInstance().getFinanceApi()
                .crashReport(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("hj", "onNext: "+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("hj", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveExceptionInfo(Throwable e) {
        //将异常信息放在输出流中
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("用户信息：黄俊:   ");
//        stringWriter.append("用户信息："+UserManager.getInstance().getUserName());
        stringWriter.append("版本信息："+UserManager.getInstance().getVersion()+"  ");
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        //生成一个文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        File file = new File("/sdcard/" + format + ".txt");

        try {
            fileOutputStream = new FileOutputStream(file);
            String s = stringWriter.toString();
            byte[] bytes = s.getBytes();
            int length = bytes.length;
            //开始写入
            fileOutputStream.write(bytes,0,length);
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
    }

    private void killProcess() {
        //关闭掉所有的activity
        ArrayList<Activity> activityArrayList = UserManager.getInstance().getActivityArrayList();
        for (int i = 0; i < activityArrayList.size() ; i++) {
            activityArrayList.get(i).finish();
        }

        //开启进程
        Intent intent = new Intent();
        intent.setClass(FinanceApplication.financeApplication,WelcomeActivity.class);
        //用application启动activity时必须添加Flags  添加对应的启动模式
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FinanceApplication.financeApplication.startActivity(intent);
        //结束进程
        System.exit(1);
        android.os.Process.killProcess(Process.myPid());
    }
}
