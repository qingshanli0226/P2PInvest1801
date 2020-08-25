package com.p2p.bawei.p2pinvest1801.more.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.user.presenter.RegisterPresenterImpl;

public class RegisterActivity extends BaseMVPActivity<RegisterPresenterImpl, RegisterContract.IRgisterView> implements RegisterContract.IRgisterView, View.OnClickListener {
    private MyLoadingBar loadingBar;
    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;

    @Override
    protected void initHttpData() {
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterPresenterImpl();
        printLog("presenter");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);
        ImageView ivTitleBack = findViewById(R.id.iv_title_back);
        TextView tvTitle = findViewById(R.id.tv_title);

        etRegisterNumber = findViewById(R.id.et_register_number);
        etRegisterName = findViewById(R.id.et_register_name);
        etRegisterPwd = findViewById(R.id.et_register_pwd);
        etRegisterPwdagain = findViewById(R.id.et_register_pwdagain);
        Button btnRegister = findViewById(R.id.btn_register);

        tvTitle.setText("用户注册");
        ivTitleBack.setVisibility(View.VISIBLE);

        ivTitleBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                //回退上一级
                finish();
                break;
            case R.id.btn_register:

                printLog("register");

                //注册
                String phone = etRegisterNumber.getText().toString().trim();
                String name = etRegisterName.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdA = etRegisterPwdagain.getText().toString().trim();

                //作输入合法判断
                if (phone.equals("") || phone == null || phone.length() > 11) {
                    etRegisterNumber.setText("");
                    Toast.makeText(this, "电话不合法", Toast.LENGTH_SHORT).show();
                    return;
                } else if (name.equals("") || name == null) {
                    Toast.makeText(this, "用户名不合法", Toast.LENGTH_SHORT).show();
                    etRegisterName.setText("");
                    return;
                } else if (pwd.equals("") || pwd == null || pwdA.equals("") || pwdA == null || !pwd.equals(pwdA)) {
                    Toast.makeText(this, "密码输入不合法", Toast.LENGTH_SHORT).show();
                    etRegisterPwd.setText("");
                    etRegisterPwdagain.setText("");
                    return;
                }
                iHttpPresenter.getRegister(name, pwd, phone);
                printLog("success");
                break;
        }
    }

    @Override
    public void onRegister(String registerResult) {
        //返回上级
        finish();
        showMessage(registerResult);
        printLog(registerResult);
    }
}
