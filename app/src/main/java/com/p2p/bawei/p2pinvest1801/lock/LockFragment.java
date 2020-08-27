package com.p2p.bawei.p2pinvest1801.lock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.mvp.view.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("Registered")
public class LockFragment extends BaseActivity {
    private String lock_str;
    private int number = 5;

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        PatternLockerView mLockGo = findViewById(R.id.lock_go);
        final SharedPreferences lock = getSharedPreferences("lock", Context.MODE_PRIVATE);
        lock_str = lock.getString("lock_str", null);
        mLockGo.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer integer : list) {
                    stringBuilder.append(integer);
                }
                if (stringBuilder.toString().equals(lock_str)) {
                    Log.e("hq", "onChange: " + list.toString());
                    finish();
                } else {
                    number--;
                    if (number == 0){
                        showMessage("图案错误，请重新登录!");
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = lock.edit();
                        edit.clear();
                        finish();
                    }else {
                        showMessage("图案错误，您还有" + number + "机会");
                    }
                }
            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.lock_fragment_layout;
    }
}
