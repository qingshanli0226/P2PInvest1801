package com.p2p.bawei.p2pinvest1801.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framework.base.BaseActivity;
import com.example.framework.base.BaseMVPActivity;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.presenter.RegisterLoginPresenterImpl;

public class MoreRegisterActivity extends BaseMVPActivity<RegisterLoginPresenterImpl, RegisterLoginContract.RegisterLoginView> implements RegisterLoginContract.RegisterLoginView {

    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdAgain;
    private Button RegisterButton;

    @Override
    protected void initData() {

    }

    private void isNull() {
        if(etRegisterNumber.getText().toString().trim().equals("")){
            showMessage("手机号不能为空");
            return;
        }

        if(etRegisterName.getText().toString().trim().equals("")){
            showMessage("用户名不能为空");
            return;
        }

        if(etRegisterPwd.getText().toString().trim().equals("")){
            showMessage("密码不能为空");
            return;
        }

        if(etRegisterPwdAgain.getText().toString().trim().equals("")){
            showMessage("请再次输入确认密码");
            return;
        }

        if(!etRegisterPwd.getText().toString().trim().equals(etRegisterPwdAgain.getText().toString().trim())){
            showMessage("两次输入的密码不一致");
            return;
        }

        //发起注册网络请求
        iHttpPresenter.onGetRegister(etRegisterName.getText().toString().trim(),etRegisterPwd.getText().toString().trim());
    }

    @Override
    protected void initView() {
        etRegisterNumber = (EditText) findViewById(R.id.et_register_number);
        etRegisterName = (EditText) findViewById(R.id.et_register_name);
        etRegisterPwd = (EditText) findViewById(R.id.et_register_pwd);
        etRegisterPwdAgain = (EditText) findViewById(R.id.et_register_pwd_again);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_register;
    }

    @Override
    protected void initGetData() {
        //注册
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行输入信息非空判断 并进行网络请求
                isNull();
            }
        });
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterLoginPresenterImpl();
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        showMessage("注册成功");
        finish();
    }

    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public void onLoginOutData(UnLoginBean unLoginBean) {

    }

    @Override
    public void showError(String code, String message) {
        showMessage(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
