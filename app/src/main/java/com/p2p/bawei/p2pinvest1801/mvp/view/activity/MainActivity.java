package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.lib_core.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class MainActivity extends BaseActivity {
    private ViewPager mainViewVp;
    private RadioGroup radio;
    @Override
    public void initView() {


        mainViewVp = (ViewPager) findViewById(R.id.main_view_vp);
        radio = (RadioGroup) findViewById(R.id.radio);



    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.activity_main;
    }
}
