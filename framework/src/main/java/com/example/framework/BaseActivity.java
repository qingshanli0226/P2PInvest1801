package com.example.framework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//定义Activity的基类，在里面定义抽象方法，抽象方法按照一定时序调用。并且在基类中定义方法，让子类复用
public abstract class BaseActivity extends AppCompatActivity {
    private long exitTime = 0;

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ){
            //判断用户两次按键的时间差是否在一个预期值之内，是的话直接直接退出，不是的话提示用户再按一次后退键退出。
            if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this,"再点击一次，退出当前应用",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                //当返回true时，表示已经完整地处理了这个事件，并不希望其他的回调方法再次进行处理，而当返回false时，
                // 表示并没有完全处理完该事件，更希望其他回调方法继续对其进行处理，
                return true;
            }else{
                finish(); //结束当前activity
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
