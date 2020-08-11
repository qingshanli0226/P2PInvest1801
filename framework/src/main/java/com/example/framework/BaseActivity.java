package com.example.framework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//定义Activity的基类，在里面定义抽象方法，抽象方法按照一定时序调用。并且在基类中定义方法，让子类复用
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    //子类需要实现的抽象方法
    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

    protected void printLog(String message) {
        Log.d("---", message);
    }

    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtra("param", bundle);
        intent.setClass(this, launcActivityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        printLog("onDestroy..............");
    }

    protected void destroy() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        pause();
        printLog("onPause..............");

    }

    public void pause() {
    }


    @Override
    protected void onResume() {
        super.onResume();
        resume();
        printLog("onResume..............");

    }

    public void resume() {
    }
}
