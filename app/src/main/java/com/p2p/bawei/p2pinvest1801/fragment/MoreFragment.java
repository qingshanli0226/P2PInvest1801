package com.p2p.bawei.p2pinvest1801.fragment;

import android.view.View;
import android.view.View.OnClickListener;

import com.example.framework.base.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.MoreAboutActivity;

public class MoreFragment extends BaseFragment implements OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.moreAbout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //关于硅谷理财
            case R.id.moreAbout:
                lunachActivity(MoreAboutActivity.class,null);
                break;
        }
    }
}
