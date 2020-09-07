package com.p2p.bawei.p2pinvest1801.activity;

import android.widget.TextView;

import com.bw.framwork.view.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

public class MoreShuoActivity extends BaseActivity {
    private PatternIndicatorView patternIndicatorView;
    private PatternLockerView patternLockView;
    private List<Integer> mList = null;
    private TextView moreShuoText;


    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        patternIndicatorView = findViewById(R.id.pattern_indicator_view);
        patternLockView = findViewById(R.id.pattern_lock_view);
        moreShuoText = findViewById(R.id.more_shuo_text);

        patternLockView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView patternLockerView) {
            }

            @Override
            public void onChange(PatternLockerView patternLockerView, List<Integer> list) {
            }

            @Override
            public void onComplete(PatternLockerView patternLockerView, List<Integer> list) {
                if (list.size() < 4) {
                    moreShuoText.setText("最少输入四个点，请重新绘制！");
                } else {
                    if (mList == null) {
                        mList = new ArrayList<>();
                        mList.addAll(list);
                    } else {
                        if (list.size()!=mList.size()){
                            moreShuoText.setText("与上一次绘制的不一致，请重新绘制！");
                        }else {
                            for (int i = 0; i < mList.size(); i++) {
                                Integer integer = mList.get(i);
                                if (integer != list.get(i)) {
                                    moreShuoText.setText("与上一次绘制的不一致，请重新绘制！");
                                    return;
                                }
                            }
                            showMsg("设置完成");
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onClear(PatternLockerView patternLockerView) {
                patternIndicatorView.updateState(mList, false);

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.more_shuo;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeActivity(this);
    }
}
