package com.p2p.bawei.p2pinvest1801.frist.view;

import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.view.ProgressView;

public class FirstFragment extends BaseFragment {
    private ProgressView mProgressMe;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mProgressMe = (ProgressView) findViewById(R.id.progress_me);
        mProgressMe.setProgress(90);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.first_layout;
    }
}
