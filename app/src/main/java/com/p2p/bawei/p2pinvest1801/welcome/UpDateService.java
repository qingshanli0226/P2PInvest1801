package com.p2p.bawei.p2pinvest1801.welcome;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.p2p.bawei.p2pinvest1801.utils.DownloadUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpDateService extends Service {

    public UpDateService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        void startDownload() {
            new DownloadUtils(UpDateService.this, "http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk", "abc.apk");
//            downLoadApk("http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk");
        }
    }

    /*
     * 从服务器中下载APK
     */
    public void downLoadApk(final String uri) {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getApplicationContext());
        pd.setCancelable(false);// 设置点击屏幕Dialog不消失
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri);
                    Log.i("UpDateServiceUpDateService", "run: " + file.getPath());
                    //安装APk
                    installApk(file, getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 下载方法
     *
     * @param path
     * @return
     */
    public File getFileFromServer(String path)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(),
                    "gc.apk");
            Toast.makeText(this, "" + file.getPath(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + file.getPath(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + file.getPath(), Toast.LENGTH_SHORT).show();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                // 获取当前下载量
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }


    /**
     * 安装Apk
     *
     * @param file
     * @param context
     */
    public void installApk(File file, Context context) {

        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //参数1 上下文, 参数2 Provider主机地址 和清单文件中保持一致   参数3  共享的文件
            Log.i("AAAAAAAAAA", "installApk: "+file.getPath());
            Uri apkUri = FileProvider.getUriForFile(context, "com.p2p.bawei.p2pinvest1801.fileProvider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            //兼容8.0( 8.0 后需要加 未知来源的手动获取权限 来调起安装页面)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = context.getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    startInstallPermissionSettingActivity(context);
                    return;
                }
            }
        } else {
            installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(installIntent);


//        //判断是否在Android7.0以上
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= 24) {
//            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
//            Uri apkUri = FileProvider.getUriForFile(context, "com.example.asus.customer.provider", file);
//            //添加这一句表示对目标应用临时授权该Uri所代表的文件
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        }
//        context.startActivity(intent);
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(final Context context) {
        //注意这个是8.0新API
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
