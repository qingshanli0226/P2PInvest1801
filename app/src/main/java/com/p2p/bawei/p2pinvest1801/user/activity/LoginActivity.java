package com.p2p.bawei.p2pinvest1801.user.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPActivity;
import com.example.net.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.view.MainActivity;
import com.p2p.bawei.p2pinvest1801.user.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user.presenter.LoginPresenterImpl;

public class LoginActivity extends BaseMVPActivity<LoginPresenterImpl, LoginContract.ILoginView> implements LoginContract.ILoginView {
    private MyLoadingBar loadingBar;

    @Override
    protected void initHttpData() {

    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new LoginPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);
        ImageView ivTitleBack = findViewById(R.id.iv_title_back);
        TextView tvTitle = findViewById(R.id.tv_title);
        final EditText etLoginNumber = findViewById(R.id.et_login_number);
        final EditText etLoginPwd = findViewById(R.id.et_login_pwd);
        Button btnLogin = findViewById(R.id.btn_login);


        tvTitle.setText("用户登录");
        ivTitleBack.setVisibility(View.VISIBLE);

        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(MainActivity.class, null);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etLoginNumber.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                //作输入合法判断
                if (number.equals("") || number == null) {
                    Toast.makeText(LoginActivity.this, "用户名不合法", Toast.LENGTH_SHORT).show();
                    etLoginNumber.setText("");
                    return;
                } else if (pwd.equals("") || pwd == null) {
                    Toast.makeText(LoginActivity.this, "密码输入不合法", Toast.LENGTH_SHORT).show();
                    etLoginPwd.setText("");
                    return;
                }
                iHttpPresenter.getLogin(number, pwd);
                printLog("login");
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onLogin(LoginBean loginResult) {
        //保存用户信息
        saveUser(loginResult);
        //返回上级
        removeCurrentActivity();
        showMessage(loginResult.getMessage());
        printLog(loginResult.toString());
    }

    private void saveUser(LoginBean loginResult) {
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", loginResult.getResult().getName());
        editor.putString("password", loginResult.getResult().getPassword());
        editor.commit();//必须提交，否则保存不成功
        printLog("保存用户信息");
    }

    @Override
    public void showError(String code, String message) {
        showError(code, message);
    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        launchActivity(MainActivity.class, null);
        return super.onKeyDown(keyCode, event);
    }
}
