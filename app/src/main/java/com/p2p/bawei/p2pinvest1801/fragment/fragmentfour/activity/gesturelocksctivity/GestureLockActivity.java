package com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.gesturelocksctivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//手势锁  尚未完善
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
            public void onStart(@NotNull PatternLockerView patternLockerView) {

            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                patternLockerView.clearHitState();
            }
        });

    }
}
