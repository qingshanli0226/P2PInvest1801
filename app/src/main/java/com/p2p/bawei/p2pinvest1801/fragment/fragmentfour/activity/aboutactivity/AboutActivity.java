package com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.aboutactivity;

import android.view.View;
import android.widget.ImageView;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

public class AboutActivity extends BaseActivity {
    private ImageView aboutBack;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int banLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        aboutBack = findViewById(R.id.about_back);
    }

    @Override
    public void initData() {

    }
}
