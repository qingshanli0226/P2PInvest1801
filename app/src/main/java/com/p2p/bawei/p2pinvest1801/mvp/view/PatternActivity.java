package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.util.Log;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PatternActivity extends BaseActivity {
    private PatternIndicatorView patternIndicatorView;
    private PatternLockerView patternLockView;

    @Override
    public void initViews() {
        patternIndicatorView = (PatternIndicatorView) findViewById(R.id.pattern_indicator_view);
        patternLockView = (PatternLockerView) findViewById(R.id.pattern_lock_view);
    }
    String pattern;
    int position;
    String pattern_afrter;
    @Override
    public void initDatas() {
        patternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {
                Log.i("----start", "开始");
            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                if(list.size() < 4){
                    Toast.makeText(PatternActivity.this, "请滑动至少4个按钮", Toast.LENGTH_SHORT).show();
                    patternLockerView.clearHitState();
                }else{
                    if(flag){
                        pattern = null;
                        for (int i = 0; i < list.size(); i++) {
                            pattern += list.get(i);
                        }
                        position = list.size();
                        flag = false;
                    }else{
                        if(list.size() != position){
                            Toast.makeText(PatternActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                        }else{
                            pattern_afrter = null;
                            for (int i = 0; i < position; i++) {
                                pattern_afrter += list.get(i);
                            }
                            if(pattern_afrter.equals(pattern)){
                                finish();

                            }else{
                                Toast.makeText(PatternActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                Log.i("----onClear", "清除");
            }
        });
    }
    boolean flag = true;
    @Override
    public int bandLayout() {
        return R.layout.activity_patter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {

    }
}
