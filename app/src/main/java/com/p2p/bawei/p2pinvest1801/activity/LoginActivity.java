package com.p2p.bawei.p2pinvest1801.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.common.view.ToolBar;
import com.bw.framwork.view.BaseActivity;
import com.bw.net.SpManager;
import com.bw.net.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.LoginModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.LoginPresenter;
public class LoginActivity extends BaseActivity<LoginPresenter> implements ToolBar.OnToolBarClick, LoginContract.View {

    private ToolBar toolBar;
    private EditText loginName,loginPwd;
    private Button login;

    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        toolBar=findViewById(R.id.login_toolbar);
        toolBar.setOnToolBarClick(this);
        loginName = findViewById(R.id.login_name);
        loginPwd = findViewById(R.id.login_pwd);
        login = findViewById(R.id.login);

        mPresenter=new LoginPresenter(new LoginModel(),this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginName.getText().toString().trim();
                String pwd = loginPwd.getText().toString().trim();
                if (name.equals("")||pwd.equals("")){
                    showMsg("用户名或密码不能为空。");
                }else {
                    mPresenter.login();
                }

            }
        });

    }

    @Override
    public void initData() {

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
    public void showError(int code, String message) {
        showMsg("错误"+code+":"+message);
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toolBar.unOnToolBarclick();

        UserManager.getInstance().removeActivity(this);
    }

    @Override
    public String getname() {
        return loginName.getText().toString().trim();
    }

    @Override
    public String getpwd() {
        return loginPwd.getText().toString().trim();
    }

    @Override
    public void loginOk(LoginBean loginBean) {
        showMsg(loginBean.getMessage());
        UserManager.getInstance().saveUserBean(loginBean);   //储存用户登录信息
        SpManager.getInstance().addContent("token",loginBean.getResult().getToken());  //sp储存token

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("num",0);
        startActivity(intent);
        finish();
    }
}
