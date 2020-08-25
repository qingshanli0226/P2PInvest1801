package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.EdtivitnEnitiy;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.MyGetbanner;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MyGetBannerPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity<MyGetBannerPresenter> implements MyContract.mView{
    private int temp=3;
    private ImageView jb;
    private float f=1.0f;
    private TextView djs;
    private int hand_temp=0;
    private MyBannerEntity myBannerEntity1;
    private EdtivitnEnitiy edtivitnEnitiy1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                hand_temp++;
                if (hand_temp>=3){
                    getpush();
                }
            }
        }
    };
    private int bar=0;
    private void dload() {
//        +edtivitnEnitiy1.getResult().getVersion()
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("检查到新版本"+"!是否更新");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.sendEmptyMessage(1);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              jdtdlog();
            }
        });

        builder.show();
    }

    private void jdtdlog() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bar += 10;
                Log.e("cx", "run: " + bar);
                progressDialog.setMessage(bar / 100 + "%");
                progressDialog.setProgress(bar);
                if (bar >= 100) {
                    progressDialog.dismiss();
                    Message message = new Message();
                    message.what=1;
                    handler.sendMessage(message);
                    timer.cancel();
                }
            }
        }, 0, 1000);

    }

    private void getpush() {
        Intent intent = new Intent(MainActivity.this, MyMainActivity.class);
        intent.putExtra("banner",  myBannerEntity1);
        startActivity(intent);
        finish();
    }

    @Override
    public void getbanner(MyBannerEntity myBannerEntity) {
         myBannerEntity1 = myBannerEntity;
        if (myBannerEntity!=null){
            Message message = new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    }

    @Override
    public void getedtivity(EdtivitnEnitiy edtivitnEnitiy) {
         edtivitnEnitiy1 = edtivitnEnitiy;
        Log.e("edtivity", "getedtivity: 版本信息"+edtivitnEnitiy.getResult().getVersion() );
        if (edtivitnEnitiy!=null){
            Message message = new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    }


    @Override
    public int BondLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initview() {
        jb =  findViewById(R.id.jb);
        djs =  findViewById(R.id.djs);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

    }

    @Override
    public void initdata() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp--;
                f-=0.1f;
                jb.setAlpha(f);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        djs.setText(temp+"");
                    }
                });
                if (temp<=0){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            dload();
                        }
                    });
                    timer.cancel();
                }
            }
        },0,1000);
    }

    @Override
    public void initInJect() {
        mPresenter=new MyGetBannerPresenter(new MyGetbanner(),this);
        mPresenter.getbanner();
        mPresenter.geteditiy();
    }

}
