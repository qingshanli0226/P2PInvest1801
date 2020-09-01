package com.p2p.bawei.p2pinvest1801.more.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.example.common.Llog;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

public class GesturePasswordActivity extends AppCompatActivity {

    private PatternIndicatorView patternIndicatorView;
    private PatternLockerView patternLockView;
    private List<Integer> hitIndexList;
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_password);
        patternIndicatorView = findViewById(R.id.pattern_indicator_view);
        patternLockView = findViewById(R.id.pattern_lock_view);

        patternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView patternLockerView) {
                Llog.d( "onStart: ");

            }

            @Override
            public void onChange(PatternLockerView patternLockerView, List<Integer> list) {
                Llog.d( "onChange: "+list.toString());
            }

            @Override
            public void onComplete(PatternLockerView patternLockerView, List<Integer> list) {
                Llog.d("onComplete: " + list.toString());

                //连接4个点以上
                if (list.size() <= 3) {
                    Toast.makeText(GesturePasswordActivity.this, "至少连接4个点", Toast.LENGTH_SHORT).show();
                }

                if (flag) {
                    if (hitIndexList.equals(list)) {
                        Toast.makeText(GesturePasswordActivity.this, "成功", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(GesturePasswordActivity.this, "不同", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    hitIndexList.addAll(list);
                    patternIndicatorView.updateState(list, true);
                    flag = true;
                }

            }

            @Override
            public void onClear(PatternLockerView patternLockerView) {
                Llog.d("onClear: ");
            }
        });

        hitIndexList = new ArrayList<>();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
