package com.p2p.bawei.p2pinvest1801.user;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.mvp.view.BaseFragment;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.R;

@Route(path = ARouterCode.USER_FRAGMENT)
public class UserFragment extends BaseFragment {

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(getContext());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.user_layout;
    }
}
