package com.example.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.FinanceConstant;
import com.example.common.view.ToolBar;
import com.example.framework.R;

public abstract class BaseActivity extends AppCompatActivity implements ToolBar.IToolBarClickListner {

    private static final String TAG = "BaseActivity";
    ToolBar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        toolBar = findViewById(R.id.toolBar);
        toolBar.setiToolBarClickListner(this);
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
        intent.putExtra(FinanceConstant.BUNDLE,bundle);
        intent.setClass(this,activityClass);
        startActivity(intent);
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
