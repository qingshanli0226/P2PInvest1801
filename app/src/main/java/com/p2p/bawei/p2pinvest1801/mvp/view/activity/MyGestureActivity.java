package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MyGestureActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView profileName;

    private PatternLockView mPatternLockView;
    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
            //判断是否成功：24678
            String mima = "24678";
            String patternToString = PatternLockUtils.patternToString(mPatternLockView, pattern);
//            Toast.makeText(MainActivity.this,"您绘制的密码是："+patternToString,Toast.LENGTH_SHORT).show();
            if(!TextUtils.isEmpty(patternToString)){
                if(patternToString.equals(mima)){
                    Toast.makeText(MyGestureActivity.this,"您绘制的密码是："+patternToString+"\n"+"密码正确，正在进入首页...",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MyGestureActivity.this,"您绘制的密码是："+patternToString+"\n"+"密码错误，请重新绘制",Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mygesture);
        profileImage =  findViewById(R.id.profile_image);
        profileName =  findViewById(R.id.profile_name);
        mPatternLockView =  findViewById(R.id.patter_lock_view);
//        mPatternLockView.setDotCount(3);
//        mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
//        mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
//        mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
//        mPatternLockView.setAspectRatioEnabled(true);
//        mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
//        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
//        mPatternLockView.setDotAnimationDuration(150);
//        mPatternLockView.setPathEndAnimationDuration(100);
//        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.white));
//        mPatternLockView.setInStealthMode(false);
//        mPatternLockView.setTactileFeedbackEnabled(true);
//        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        RxPatternLockView.patternComplete(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompleteEvent>() {
                    @Override
                    public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
                        Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
                    }
                });

        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing started");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progress: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern complete: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been cleared");
                        }
                    }
                });
    }
}
