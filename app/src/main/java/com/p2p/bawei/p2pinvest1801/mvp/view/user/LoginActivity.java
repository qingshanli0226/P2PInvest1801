package com.p2p.bawei.p2pinvest1801.mvp.view.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.ToolBar;
import com.example.common.bean.LoginBean;
import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.LoginModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView, ToolBar.ClicksListener {
    private EditText loginPhone;
    private EditText loginPassword;
    private Button btnLogin;
    private ToolBar toolbar;
    @Override
    public void initViews() {
        loginPhone = (EditText) findViewById(R.id.login_phone);
        loginPassword = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        mPresenter = new LoginPresenter(new LoginModel(),this);
        toolbar.setClicksListener(this);
    }

    @Override
    public void initDatas() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loginP();
            }
        });
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {

    }


    @Override
    public void loginView(LoginBean loginBean) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        UserManagers.getInstance().saveLoginBean(loginBean);
        finish();
    }

    @Override
    public String username() {
        return loginPhone.getText().toString().trim();
    }

    @Override
    public String pwd() {
        return loginPassword.getText().toString().trim();
    }

    @Override
    public void leftclick() {
        finish();
    }

    @Override
    public void rightclick() {

    }
}
