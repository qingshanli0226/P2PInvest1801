package com.p2p.bawei.p2pinvest1801.user.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.user.mvp.model.RegisterModel;
import com.p2p.bawei.p2pinvest1801.user.mvp.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.IRegisterContractView {
    private ImageView registerBack;
    private EditText registerEtPhone;
    private EditText registerEtUsername;
    private EditText registerEtPwd;
    private EditText registerEtPwd2;
    private Button registerEtRegister;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_et_register:
                mPresenter.getRegister(registerEtPhone.getText().toString().trim(),
                        registerEtPwd.getText().toString().trim());
                break;
        }
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        registerBack = findViewById(R.id.register_back);
        registerEtPhone = findViewById(R.id.register_et_phone);
        registerEtUsername = findViewById(R.id.register_et_username);
        registerEtPwd = findViewById(R.id.register_et_pwd);
        registerEtPwd2 = findViewById(R.id.register_et_pwd2);
        registerEtRegister = findViewById(R.id.register_et_register);
        registerEtRegister.setOnClickListener(this);

        mPresenter = new RegisterPresenter(new RegisterModel(), this);


    }

    @Override
    public void initData() {

    }


    @Override
    public void onRegister(StringBean stringBean) {

        if (stringBean.getCode().equals("200")) {
            Toast.makeText(this, "" + stringBean.getResult() + stringBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "" + stringBean.getResult() + stringBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
