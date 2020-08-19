package com.p2p.bawei.p2pinvest1801.user_act.view;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user_act.model.LoginModel;
import com.p2p.bawei.p2pinvest1801.user_act.presenter.LoginPresenter;

@Route(path = ARouterCode.USER_LOGIN_ACT)
public class LoginAct extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enter_login) {
            mPresenter.getLoginData();
        }
    }

    @Override
    public void initView() {
        findViewById(R.id.enter_login).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this, new LoginModel());
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_loagin;
    }

    @Override
    public String getUserName() {
        return findViewById(R.id.user_name_et).toString().trim();
    }

    @Override
    public String getUserPWD() {
        return findViewById(R.id.user_pwd_et).toString().trim();
    }

    @Override
    public void goMain() {
        Toast.makeText(this, ARouterCode.USER_LOGIN_ACT, Toast.LENGTH_SHORT).show();
    }
}
