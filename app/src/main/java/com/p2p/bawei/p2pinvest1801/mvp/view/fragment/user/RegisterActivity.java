package com.p2p.bawei.p2pinvest1801.mvp.view.fragment.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.RegisterModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.RegisterView {
    private EditText registerPhone;
    private EditText registerUser;
    private EditText registerPassword;
    private EditText registerPwd;
    private Button btnRegister;
    @Override
    public void initViews() {
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerUser = (EditText) findViewById(R.id.register_user);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPwd = (EditText) findViewById(R.id.register_pwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mPresenter = new RegisterPresenter(new RegisterModel(), this);
    }

    @Override
    public void initDatas() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.registerP();
            }
        });
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_register;
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
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerView(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String username() {
        return registerUser.getText().toString().trim();
    }

    @Override
    public String pwd() {
        return registerUser.getText().toString().trim();
    }
}
