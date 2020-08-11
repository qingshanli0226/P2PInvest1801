package com.example.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    public void showMessage(String message){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String message){
        Log.i(TAG, "printLog: "+message);
    }

    public void lunachActivity(Class activityClass,Bundle bundle){
        Intent intent = new Intent();
        intent.putExtra("bundle",bundle);
        intent.setClass(this,activityClass);
        startActivity(intent);
    }
}
