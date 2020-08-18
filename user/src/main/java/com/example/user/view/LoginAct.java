package com.example.user.view;

import android.view.View;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.common.ARouterCode;
import com.example.user.R;
import com.example.user.contract.LoginContract;
import com.example.user.model.LoginModel;
import com.example.user.presenter.LoginPresenter;

@Route(path = ARouterCode.USER_ACT)
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
        Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
    }
}
