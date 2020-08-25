package com.p2p.bawei.p2pinvest1801.more.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.framework.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TogglemoreActivity extends BaseActivity {
    private TextView patternText;
    private PatternIndicatorView patternIndicatorView;
    private PatternLockerView patternLockView;
    private SharedPreferences box_isChecked;
    private List<Integer> lock_list = new ArrayList<>();//存储首次绘制
    private int content = 0;//验证次数
    private TextView toggleTitle;
    private boolean checked;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        patternIndicatorView = (PatternIndicatorView) findViewById(R.id.pattern_indicator_view);
        patternText = findViewById(R.id.pattern_text);
        toggleTitle = findViewById(R.id.toggle_title);

        patternLockView = (PatternLockerView) findViewById(R.id.pattern_lock_view);

        box_isChecked = CacheManager.getCacheManager().getSharedPreferences();
        checked = box_isChecked.getBoolean(InvestConstant.SP_ISCHECKED, false);

        patternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView patternLockerView) {
                //开始绘制
            }

            @Override
            public void onChange(PatternLockerView patternLockerView, List<Integer> list) {
                //改变绘制

            }

            @Override
            public void onComplete(PatternLockerView patternLockerView, List<Integer> list) {
                //绘制完成
                //判断是否是错误绘制
                //判断是否是登录验证
                if (checked) {
                    //登录验证手势密码
                    String lock_str = box_isChecked.getString(InvestConstant.SP_LOCK_STR, "");

                    StringBuilder stringBuilder = new StringBuilder();
                    for (Integer integer : list
                    ) {
                        stringBuilder.append(String.valueOf(integer));
                    }

                    if (!stringBuilder.toString().equals(lock_str.toString())) {
                        patternText.setText("手势密码错误，请重新输入");
                        return;
                    }

                    showMessage("登录成功");
                    finish();
                } else {
                    //做注册手势密码
                    isDraw(patternLockerView, list);
                }
            }

            @Override
            public void onClear(PatternLockerView patternLockerView) {
                //清除绘制
            }
        });

    }

    private void isDraw(PatternLockerView patternLockerView, List<Integer> list) {
        if (lock_list.size() == 0) {
            lock_list.addAll(list);
            printLog("添加一次" + lock_list.size());
        }
        if (list.size() <= 3) {
            patternLockerView.updateStatus(true);
            patternText.setText("至少连接4个点");
            content = 0;
            lock_list.clear();
            printLog("少于4个");
            return;
        } else {
            //两次绘制数量不同
            if (list.size() != lock_list.size()) {
                patternLockerView.updateStatus(true);
                patternText.setText("两次密码不一致");
                content = 0;
                lock_list.clear();
                printLog("数量不同" + content);

                return;
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).equals(lock_list.get(i))) {
                        patternLockerView.updateStatus(true);
                        patternText.setText("两次密码不一致");
                        content = 0;
                        lock_list.clear();
                        printLog("不一致" + content);
                        return;
                    }
                }
                patternText.setText("请再次绘制手势密码");
                content += 1;
                printLog("成功一次" + content);
            }
            if (content == 2) {
                printLog("成功");
                showMessage("手势密码设置成功");

                StringBuilder stringBuilder = new StringBuilder();
                for (Integer integer : list
                ) {
                    stringBuilder.append(String.valueOf(integer));
                }

                SharedPreferences.Editor editor = CacheManager.getCacheManager().getEditor();
                editor.putBoolean(InvestConstant.SP_ISCHECKED, true);
                editor.putString(InvestConstant.SP_LOCK_STR, stringBuilder.toString());
                editor.apply();
                editor.commit();
                finish();
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_togglemore;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        launchActivity(MainActivity.class, new Bundle());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checked) {
            toggleTitle.setText("输入手势密码以解锁");
        }
    }
}
