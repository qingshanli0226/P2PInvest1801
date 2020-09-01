package com.p2p.bawei.p2pinvest1801.home.more.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework2.manager.CacheManager;
import com.example.framework2.mvp.view.BaseActivity;
import com.example.net.activity_bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.MainActivity;
import com.p2p.bawei.p2pinvest1801.myview.Bean;

public class LogActivity extends BaseActivity<LogPresenter> implements LogContract.View {


    private TextView tv_login_number;
    private EditText et_login_number;
    private RelativeLayout rl_login;
    private TextView tv_login_pwd;
    private EditText et_login_pwd;
    private Button btn_login;

    @Override
    public void onClick(View v) {
        mPresenter.login();
    }

    @Override
    public void initView() {



        et_login_number=findViewById(R.id.et_login_number);
        et_login_pwd=findViewById(R.id.et_login_pwd);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initPresenter() {
        mPresenter=new LogPresenter(new LogModel(),this);
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_log;
    }


    @Override
    public String getUserName() {
        return et_login_number.getText().toString().trim();
    }

    @Override
    public String getUserPwd() {
        return et_login_pwd.getText().toString().trim();
    }

    @Override
    public void logOk(LoginBean loginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name",loginBean.getResult().getName());
        intent.putExtra("avatar",(String) loginBean.getResult().getAvatar());
        startActivity(intent);
        finish();
    }
}
