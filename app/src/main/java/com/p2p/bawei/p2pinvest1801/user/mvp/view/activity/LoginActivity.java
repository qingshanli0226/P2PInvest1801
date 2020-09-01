package com.p2p.bawei.p2pinvest1801.user.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user.mvp.model.LoginModel;
import com.p2p.bawei.p2pinvest1801.user.mvp.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginContractView {
    private ImageView loginBack;
    private EditText loginEtPhone;
    private EditText loginEtPwd;
    private Button loginBtnLogin;

//    private CallBack callBack;
//
//    public CallBack getCallBack() {
//        return callBack;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                mPresenter.register(loginEtPhone.getText().toString().trim(),
                        loginEtPwd.getText().toString().trim()
                );
                break;
        }
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginBack = findViewById(R.id.login_back);
        loginEtPhone = findViewById(R.id.login_et_phone);
        loginEtPwd = findViewById(R.id.login_et_pwd);
        loginBtnLogin = findViewById(R.id.login_btn_login);
        loginBtnLogin.setOnClickListener(this);
        mPresenter = new LoginPresenter(new LoginModel(), this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLogin(LoginBean stringBean) {
        Log.i("onLogin", "onLogin: " + stringBean.getCode());
        if (stringBean.getCode().equals("200")) {
            Toast.makeText(this, "" + stringBean.getMessage(), Toast.LENGTH_SHORT).show();
//            callBack.sendUser(stringBean.getResult());

            LoginBean.ResultBean result = stringBean.getResult();
            SPUtils spUtils = SPUtils.getInstance();
            spUtils.put("name", result.getId()); //存入名称
            spUtils.put("avatar", result.getAvatar());//存入图片

            finish();
        } else {
            Toast.makeText(this, "" + stringBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    public interface CallBack {
//        void sendUser(LoginBean.ResultBean resultBean);
//    }



}
