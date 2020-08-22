package com.p2p.bawei.p2pinvest1801.activity;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.example.framework.base.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HandPasswordActivity extends BaseActivity {

    private PatternIndicatorView patternSmallPassword;
    private PatternLockerView patternPassword;
    private TextView handPasswordTv;

    //是否是第一次绘制
    private boolean isOne = true;
    private boolean isTwo = false;
    private String passwordOne = "";
    private String passwordTwo = "";

    @Override
    protected void initData() {
        patternPassword.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                if(list.size() < 4){
                    handPasswordTv.setText("最少链接4个点，请重新输入");
                    handPasswordTv.setTextColor(Color.RED);
                    patternPassword.clearHitState();
                    return;
                }
                if(isOne){
                    //第一次的图案
                    for (int i = 0; i < list.size() ; i++) {
                        passwordOne += list.get(i);
                    }
                    printLog(passwordOne+"----one");
                    isOne = false;
                    handPasswordTv.setText("第一次设置成功，设置第二次");
                    handPasswordTv.setTextColor(Color.BLACK);
                } else{
                    isTwo = true;
                }
                if(isTwo){
                    //第二次的图案
                    passwordTwo = "";
                    for (int i = 0; i < list.size() ; i++) {
                        passwordTwo += list.get(i);
                    }
                    printLog(passwordTwo+"----two");

                    if(passwordOne.equals(passwordTwo)){
                        showMessage("设置成功");
                        finish();
                    } else{
                        handPasswordTv.setText("与上次绘制不一致，请重新绘制");
                        handPasswordTv.setTextColor(Color.RED);
                        patternPassword.clearHitState();
//                        patternPassword.updateStatus(true);
                    }
                }

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {

            }
        });
    }

    @Override
    protected void initView() {
        patternSmallPassword = (PatternIndicatorView) findViewById(R.id.patternSmallPassword);
        patternPassword = (PatternLockerView) findViewById(R.id.patternPassword);
        handPasswordTv = (TextView) findViewById(R.id.handPasswordTv);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hand_password;
    }
}
