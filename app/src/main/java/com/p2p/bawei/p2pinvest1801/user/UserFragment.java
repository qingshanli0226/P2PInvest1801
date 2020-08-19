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
        if (view.getId() == R.id.user_head_pic) {
            ARouter.getInstance().build(ARouterCode.USER_LOGIN_ACT).navigation();
        }
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(getContext());
        findViewById(R.id.user_head_pic).setOnClickListener(this);
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
