package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class PwdActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView profileName;
    private PatternLockView patterLockView;
    private  PatternLockViewListener mPatternLockViewListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);

        initView();
        initPwd();
    }

    private void initPwd() {


        patterLockView = (PatternLockView) findViewById(R.id.patter_lock_view);

        patterLockView.addPatternLockListener(mPatternLockViewListener);



}

    private void initView() {
        profileImage = (ImageView) findViewById(R.id.profile_image);
        profileName = (TextView) findViewById(R.id.profile_name);
        patterLockView = (PatternLockView) findViewById(R.id.patter_lock_view);

        mPatternLockViewListener = new PatternLockViewListener() {
            @Override
            public void onStarted() {
                Log.d(getClass().getName(), "Pattern drawing started");
            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
                Log.d(getClass().getName(), "Pattern progress: " +
                        PatternLockUtils.patternToString(patterLockView, progressPattern));
            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                Log.d(getClass().getName(), "Pattern complete: " +
                        PatternLockUtils.patternToString(patterLockView, pattern));
                //判断是否成功：24678
                String mima = "24678";
                String patternToString = PatternLockUtils.patternToString(patterLockView, pattern);
//            Toast.makeText(MainActivity.this,"您绘制的密码是："+patternToString,Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(patternToString)) {
                    if (patternToString.equals(mima)) {
                        Toast.makeText(PwdActivity.this, "您绘制的密码是：" + patternToString + "\n" + "密码正确，正在进入首页...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PwdActivity.this,MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(PwdActivity.this, "您绘制的密码是：" + patternToString + "\n" + "密码错误，请重新绘制", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCleared() {
                Log.d(getClass().getName(), "Pattern has been cleared");
            }
        };

    }
}
