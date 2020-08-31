package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.ToolBar;
import com.example.common.bean.BaseBean;
import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.view.BaseActivity;
import com.example.net.BaseObserver;
import com.example.net.NetFunction;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.p2p.bawei.p2pinvest1801.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
//用户详情解界面
public class MyPortraitActivty extends BaseActivity implements ToolBar.ClicksListener{
    private ToolBar toolbar;
    private ImageView properTouxiang;
    private TextView properName;
    private Button btnReturn;
    @Override
    public void initViews() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        properTouxiang = (ImageView) findViewById(R.id.proper_touxiang);
        properName = (TextView) findViewById(R.id.proper_name);
        btnReturn = (Button) findViewById(R.id.btn_return);
        toolbar.setClicksListener(this);
    }

    @Override
    public void initDatas() {
        //点击退出登录
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initReturn();
            }
        });
    }
    //退出登录请求
    private void initReturn() {
        if(UserManagers.getInstance().isUserLogin()){
            RetrofitManager.getInstance().getRetrofit().create(P2PApi.class)
                    .logout()
                    .map(new NetFunction<BaseBean<String>,String>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<String>() {
                        @Override
                        public void onNext(String s) {
                            showMsg(s);
                            UserManagers.getInstance().processLogout();
                            Bundle bundle = new Bundle();
                            bundle.putInt("index", 0);
                            launchActivity(MainActivity.class, bundle);
                            finish();
                        }

                        @Override
                        public void onRequestError(String code, String message) {
                            showError(code,message);
                        }
                    });
        }
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_portrait;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(MyPortraitActivty.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(MyPortraitActivty.this, ""+code+"--"+message, Toast.LENGTH_SHORT).show();
    }
    //点击返回
    @Override
    public void leftclick() {
        finish();
    }

    @Override
    public void rightclick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
