package com.example.user.view;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.common.ARouterCode;
import com.example.user.R;

@Route(path = ARouterCode.USER_REGISTER_ACT)
public class RegisterAct extends BaseActivity {
    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_register;
    }
}
