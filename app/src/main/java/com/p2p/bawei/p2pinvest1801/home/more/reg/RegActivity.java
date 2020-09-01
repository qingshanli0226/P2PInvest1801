package com.p2p.bawei.p2pinvest1801.home.more.reg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class RegActivity extends BaseActivity<RegPresenter> implements RegContract.View {


    private EditText et_register_number;
    private EditText et_register_name;
    private EditText et_register_pwd;
    private EditText et_register_pwdagain;
    private Button btn_register;

    @Override
    public void onClick(View v) {
        if (getUserPwd().equals(et_register_pwdagain.getText().toString().trim())){
            mPresenter.register();
        }else {
           show("二次输入密码不一致");
        }
    }

    @Override
    public void initView() {
        et_register_number=findViewById(R.id.et_register_number);
        et_register_name=findViewById(R.id.et_register_name);
        et_register_pwdagain=findViewById(R.id.et_register_pwdagain);
        et_register_pwd=findViewById(R.id.et_register_pwd);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter=new RegPresenter(new RegModel(),this);
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_reg;
    }


    @Override
    public String getUserName() {
        return et_register_name.getText().toString().trim();
    }

    @Override
    public String getUserPwd() {
        return et_register_pwd.getText().toString().trim();
    }

    @Override
    public void regOk() {
        this.finishActivity(1);
    }

    @Override
    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
