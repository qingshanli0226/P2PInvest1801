package com.p2p.bawei.p2pinvest1801.home.more;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class AboutActivity extends BaseActivity {


    private ImageView about_back;

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void initView() {
        about_back=findViewById(R.id.about_back);
        about_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_about;
    }


}
