package com.p2p.bawei.p2pinvest1801.home.more;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class GesteActivity extends AppCompatActivity {

    private PatternIndicatorView pattern_indicator_view;
    private PatternLockerView pattern_lock_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geste);
        initView();
    }

    private void initView() {
        pattern_indicator_view = (PatternIndicatorView) findViewById(R.id.pattern_indicator_view);
        pattern_lock_view = (PatternLockerView) findViewById(R.id.pattern_lock_view);
        pattern_lock_view.setOnPatternChangedListener(new OnPatternChangeListener() {
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
