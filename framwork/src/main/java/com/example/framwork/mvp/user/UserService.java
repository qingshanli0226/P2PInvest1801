package com.example.framwork.mvp.user;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.common.bean.BaseBean;
import com.example.common.bean.LoginBean;
import com.example.framwork.R;
import com.example.net.BaseObserver;
import com.example.net.NetFunction;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserService extends Service {

    public class MyBind extends Binder{
        public UserService getBind(){
            return UserService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }


    public void autologin(String token){
        HashMap<String,String> params = new HashMap<>();
        params.put("token", token);
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .autologin(params)
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        UserManagers.getInstance().saveLoginBean(loginBean);
                        Toast.makeText(UserService.this, "service 弹吐司:自动登录成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestError(String code, String errorMessgae) {
                        Toast.makeText(UserService.this, ""+code+":"+errorMessgae, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private Notification.Builder builder;
    private NotificationManager systemService;
    private RemoteViews remoteViews;
    public void StartNotification(){
        systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.wc_ac_01);
        remoteViews = new RemoteViews(getPackageName(), R.layout.mynotifiction);
        builder.setCustomContentView(remoteViews);
        systemService.notify(1, builder.build());
        downLoad();
    }

    private void downLoad() {
        OkGo.<File>get(UserManagers.getInstance().getVersionurl()).execute(new FileCallback("/sdcard/tmp/","newapk.apk") {
            @Override
            public void onSuccess(Response<File> response) {
                File body = response.body();
                Log.i("----body", body.length()+"");
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                int fraction = (int) (progress.fraction * 100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Looper.prepare();
                remoteViews.setProgressBar(R.id.notification_progress, 100, fraction, false);
                remoteViews.setTextViewText(R.id.notification_text, "已下载:"+fraction+"%");
                Looper.loop();
            }
        });
    }
}
