package com.p2p.bawei.p2pinvest1801.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class Picture extends BaseActivity {
    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        Glide.with(this).load(image).into((ImageView) findViewById(R.id.show_head_pic));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public int bandLayout() {
        return R.layout.picture_layout;
    }
}
