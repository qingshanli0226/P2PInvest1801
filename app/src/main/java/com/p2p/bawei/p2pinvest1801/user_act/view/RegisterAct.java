package com.p2p.bawei.p2pinvest1801.user_act.view;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user_act.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.user_act.model.RegisterModel;
import com.p2p.bawei.p2pinvest1801.user_act.presenter.RegisterPresenter;

@Route(path = ARouterCode.USER_REGISTER_ACT)
public class RegisterAct extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enter_register) {
            mPresenter.getRegisterBeanData();
        }
    }

    @Override
    public void initView() {
        findViewById(R.id.enter_register).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new RegisterPresenter(this, new RegisterModel());
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_register;
    }

    @Override
    public String getUserName() {
        return ((EditText)findViewById(R.id.user_name_et)).getText().toString().trim();
    }

    @Override
    public String getReUserPWD() {
        return ((EditText)findViewById(R.id.user_re_pwd_et)).getText().toString().trim();
    }

    @Override
    public String getUserPWD() {
        return ((EditText)findViewById(R.id.user_pwd_et)).getText().toString().trim();
    }

    @Override
    public void goMain() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
