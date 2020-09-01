package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.session.MediaSession;
import android.media.session.MediaSession.Token;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.mvp.view.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HomePresenter;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎页面
 */
public class WelComeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    private ImageView welcomeImg;
    private float num = 0.6f;
    private int number = 3;//倒计时


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if (msg.what == 0) {
                //获取服务端的最新版本号
                final UpDateBean.ResultBean upDateBean = (UpDateBean.ResultBean) msg.obj;
                //获取当前版本号
                PackageManager packageManager = getPackageManager();
                //第一个参数表示当前包名,第二个参数代表获取的的版本信息
                PackageInfo packageArchiveInfo = null;
                try {
                    packageArchiveInfo = packageManager.getPackageInfo(getPackageName(), 0);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if (packageArchiveInfo!=null){
                    String versionName = packageArchiveInfo.versionName;
                    //吧当前版本号和最新版本号进行比较
                    if (upDateBean.getVersion().equals(versionName)) {
                        //跳转主页面
                        Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelComeActivity.this);
                        builder.setTitle("检测到新的版本是否更新");
                        builder.setCancelable(false);
                        //确定按钮
                        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String filePath = "/sdcard/Download/app-debug.apk";
                                File file = new File(filePath);
                                if (!file.exists()) {
                                    //不存在
                                    //获取下载的APK地址
                                    String apkUrl = upDateBean.getApkUrl();
                                    upLoadApk(apkUrl);
                                    Log.e("不存在", "onClick: " + Environment.getExternalStorageDirectory());
                                } else {
                                    //存在
                                    Log.e("存在", "onClick: ");
                                    installAPK();
                                }
                            }
                        });
                        //取消按钮
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.show();
                    }
                }
            }
        }
    };

    //下载apk
    private void upLoadApk(String apkUrl) {
        OkGo.<File>get(apkUrl).execute(new FileCallback("/sdcard/Download", "/app-debug.apk") {
            @Override
            public void onSuccess(Response<File> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelComeActivity.this);
                builder.setTitle("安裝");
                builder.setMessage("是否安装");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        installAPK();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();

            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
            }
        });
    }

    private Timer timer;

    @Override
    public void initView() {

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        welcomeImg = findViewById(R.id.welcome_img);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (number <= 0) {
                    getVersion();
                    timer.cancel();
                }
                num += 0.2f;
                number--;
                //渐变
                welcomeImg.setAlpha(num);
            }
        }, 0, 1000);
    }

    @Override
    public void initData() {
        

    }

    @Override
    public void initInJect() {
        //进行数据请求
        mPresenter = new HomePresenter(new HomeModel(), this);
        mPresenter.getData();
        mPresenter.getUpDateMsg();
    }

    @Override
    public int BandLayout() {
        return R.layout.activity_wel_come;
    }

    /*
     * 下载到本地后执行安装
     */
    private void installAPK() {
        File apkFile = new File("/sdcard/Download/app-debug.apk", "1.2");
        if (!apkFile.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
//      安装完成后，启动app
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.parse("file://" + apkFile.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }


    //获取服务端版本信息
    private void getVersion() {
        OkGo.<String>get("http://49.233.93.155:9999/atguigu/json/P2PInvest/update.json").execute(new StringCallback() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                Gson gson = new Gson();
                UpDateBean upDateBean = gson.fromJson(body, UpDateBean.class);
                Message message1 = new Message();
                message1.what = 0;
                UpDateBean.ResultBean result = upDateBean.getResult();
                message1.obj = result;
                handler.sendMessage(message1);
            }
        });
    }

    @Override
    public void initHomeData(HomeBean homeBean) {

    }

    @Override
    public void upDate(UpDateBean upDateBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
