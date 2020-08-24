package com.p2p.bawei.p2pinvest1801.user_act.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user_act.manager.UserManager;
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
        return ((EditText) findViewById(R.id.user_name_et)).getText().toString().trim();
    }

    @Override
    public String getUserPWD() {
        return ((EditText) findViewById(R.id.user_pwd_et)).getText().toString().trim();
    }

    @Override
    public void goMain(LoginBean.ResultBean loginBean) {
        Intent head_image = new Intent().putExtra("head_image", loginBean.getAvatar());
        setResult(10000,head_image);
        UserManager.getUserManager().setAddress(loginBean.getAddress());
        UserManager.getUserManager().setAvatar(loginBean.getAvatar());
        UserManager.getUserManager().setEmail(loginBean.getEmail());
        UserManager.getUserManager().setId(loginBean.getId());
        UserManager.getUserManager().setMoney(loginBean.getMoney());
        UserManager.getUserManager().setName(loginBean.getName());
        UserManager.getUserManager().setAddress(loginBean.getPassword());
        UserManager.getUserManager().setPhone(loginBean.getPhone());
        UserManager.getUserManager().setPoint(loginBean.getPoint());
        UserManager.getUserManager().setToken(loginBean.getToken());
        finish();
    }
}
