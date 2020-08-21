package com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.gesturelocksctivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class GestureLockActivity extends AppCompatActivity {
    private PatternIndicatorView patternIndicatorView;
    private PatternLockerView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_gesture_lock);

        initView();
    }

    private void initView() {
        patternIndicatorView = findViewById(R.id.pattern_indicator_view);
        patternLockView = findViewById(R.id.pattern_lock_view);

        patternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(PatternLockerView patternLockerView, List<Integer> list) {

            }

            @Override
            public void onComplete(PatternLockerView patternLockerView, List<Integer> list) {

            }

            @Override
            public void onClear(PatternLockerView patternLockerView) {

            }
        });

    }
}
