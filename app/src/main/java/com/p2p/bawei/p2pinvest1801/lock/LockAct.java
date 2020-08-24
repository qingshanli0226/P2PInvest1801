package com.p2p.bawei.p2pinvest1801.lock;

import android.content.SharedPreferences;
import android.view.View;

import com.example.baselibrary.mvp.view.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LockAct extends BaseActivity {
    private PatternLockerView mPatternLockView;
    private SharedPreferences lock;
    List<Integer> lock_list = new ArrayList<>();
    private boolean flag = true;
    private boolean lock_flag = true;

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        mPatternLockView = findViewById(R.id.pattern_lock_view);
        lock = getSharedPreferences("lock", MODE_PRIVATE);
    }

    @Override
    public void initData() {
        mPatternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                if (flag) {
                    lock_list.addAll(list);
                    flag = false;
                } else {
                    if (lock_list.size() != list.size()) {
                        return;
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            if (!list.get(i).equals(lock_list.get(i))) {
                                lock_flag = false;
                                showMessage("图案不一致，请重新输入");
                                flag = true;
                                return;
                            }
                        }
                    }
                    if (lock_flag) {
                        keep(list);
                    }
                }
            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {

            }
        });
    }

    private void keep(@NotNull List<Integer> list) {
        StringBuilder lockStr = new StringBuilder();
        for (Integer integer : list) {
            lockStr.append(integer);
        }
        SharedPreferences.Editor edit = lock.edit();
        edit.putString("lock_str", lockStr.toString());
        edit.apply();
        finish();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.lock_layout;
    }
}
