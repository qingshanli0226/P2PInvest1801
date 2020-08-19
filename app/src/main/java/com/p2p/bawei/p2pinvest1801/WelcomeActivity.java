package com.p2p.bawei.p2pinvest1801;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.NetCommon;
import com.example.framework2.mvp.view.BaseActivity;
import com.example.net.activity_bean.IndexBean;
import com.example.net.activity_bean.InvestBean;
import com.example.net.activity_bean.UpdateBean;
import com.example.net.api_srever.ApiServer;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler =new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            if (msg.what==6){
                diaLog();
            }else {
                time_number.setText(msg.what+"");
            }

        }
    };
    private TextView time_number;
    private int start=3;
    private boolean isUpdate=false;
    private  Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        HttpManager.getHttpManager().setPath(NetCommon.BASE);
        initView();
        initIndex();
        initVersion();
        initInvest();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                start--;
                if (start!=0){
                    handler.sendEmptyMessage(start);

                }else {
                    handler.sendEmptyMessage(start);
                    handler.sendEmptyMessage(6);
                    timer.cancel();
//                    if (!isUpdate){
//                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        WelcomeActivity.this.finish();
//                        timer.cancel();
//                    }else {

//                    }


                }

            }
        }, 1000,1000);
    }

    private void initInvest() {
        HttpManager.getHttpManager().getRetrofit()
                .create(ApiServer.class)
                .getInvestk()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InvestBean>() {
                    @Override
                    public void success(InvestBean investBean) {
                        CacheManager.getInstance().setInvestBean(investBean);
                    }

                    @Override
                    public void error(String errorCode, String errorMessage) {

                    }
                });
    }

    private void initVersion() {

        HttpManager.getHttpManager().getRetrofit()
                .create(ApiServer.class)
                .getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpdateBean>() {
                    @Override
                    public void success(UpdateBean updateBean) {
                        CacheManager.getInstance().setUpdateBean(updateBean);
                        UpdateBean.ResultBean result = updateBean.getResult();
                        Log.e("FFF", "success: "+result.getVersion() );
                    }

                    @Override
                    public void error(String errorCode, String errorMessage) {

                    }
                });
    }

    private void initIndex() {
        HttpManager.getHttpManager().getRetrofit()
                .create(ApiServer.class)
                .getIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<IndexBean>() {
                    @Override
                    public void success(IndexBean indexBean) {
                        CacheManager.getInstance().setIndexBean(indexBean);
                        Log.e("FFF", "success: "+indexBean.getMsg()+indexBean.getCode() );

                    }

                    @Override
                    public void error(String errorCode, String errorMessage) {

                    }
                });
    }

    private void diaLog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.app_icon)
                .setTitle("更新版本")
                .setMessage("是否更新")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();

                    }
                })
                .create().show();
    }

    public void initView() {
        start=3;
        time_number = (TextView) findViewById(R.id.time_number);
        time_number.setText(start+"");
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onClick(View v) {

    }
}
