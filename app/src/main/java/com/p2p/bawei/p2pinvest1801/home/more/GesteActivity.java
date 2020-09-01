package com.p2p.bawei.p2pinvest1801.home.more;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framework2.mvp.view.BaseActivity;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GesteActivity extends BaseActivity {
    private List<Integer> lock_list=new ArrayList<>();
    private PatternIndicatorView pattern_indicator_view;
    private PatternLockerView pattern_lock_view;
    private boolean flag=true;
    private boolean lock_flag=true;


    public void initView() {

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
                if (flag) {
                    lock_list.addAll(list);
                    flag = false;
                } else {
                    if (lock_list.size() != list.size()) {
                        lock_list.clear();
                        flag = true;
                        lock_flag = true;
                        showMessage("图案不一致，请重新输入");
                        return;
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            if (!list.get(i).equals(lock_list.get(i))) {
                                lock_flag = true;
                                lock_list.clear();
                                flag = true;
                                showMessage("图案不一致，请重新输入");
                                return;
                            }
                        }
                    }
                    if (lock_flag) {
                       saveList(list);
                       finish();
                    }
                }


            }

            @Override
            public void onClear(PatternLockerView patternLockerView) {

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
        return R.layout.activity_geste;
    }

    private void saveList(List<Integer> myList) {
        SharedPreferences geste = getSharedPreferences("geste", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = geste.edit();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < myList.size(); i++) {
            Log.e("fff", "saveList: "+myList.get(i) );
            stringBuffer.append(i);
        }
        edit.putBoolean("lock",true);
        edit.putString("mm",stringBuffer.toString());
        edit.commit();
    }

    @Override
    public void onClick(View v) {

    }
}
