package com.example.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


//定义Activity的基类，在里面定义抽象方法，抽象方法按照一定时序调用
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        设置状态栏是白底黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(getLayoutId());

        initView();
        TAG="RH:"+getClass().getSimpleName();
        create();


    }

    protected  void create(){

    };

    //    子类需要实现的抽象方法
    protected abstract void initView();
    protected abstract int getLayoutId();


//    log打印
    protected void printLog(String message){
        Log.d(TAG,message);
    }

//    toast
    protected void showMessage(String message){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

//    传值加跳转
    protected void launchActivity(Class launchActivityClass,Bundle bundle){
        Intent intent = new Intent();
        if(bundle==null){
            bundle=new Bundle();
        }
        intent.putExtras(bundle);
        intent.setClass(this,launchActivityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printLog("onDestroy.........");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printLog("onPause.........");
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    private void resume() {
        printLog("resume...........");


    }
}
