package com.p2p.bawei.p2pinvest1801.fragment;

import com.bw.common.view.MyProgressBar;
import com.bw.framwork.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;

public class HomePageFragment extends BaseFragment {
    private MyProgressBar myProgressBar;

    @Override
    public void initView() {

        myProgressBar= (MyProgressBar) findViewById(R.id.myPro);
        myProgressBar.setProgress(40);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.homepagerfragment;
    }
}
