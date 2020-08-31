package com.p2p.bawei.p2pinvest1801;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.framwork.mvp.user.UserManagers;
import com.example.net.NetModel;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.p2p.bawei.p2pinvest1801.exception.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {
    private RefWatcher refWatcher;
    public static App instace;
    @Override
    public void onCreate() {
        super.onCreate();
        instace = this;
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }

        UMConfigure.init(this, "5f49cb049be20132c19c603f", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "4b457e5333909dd5cf605c09590e06d2");
//获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("-----um","注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("-----um","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });


        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
