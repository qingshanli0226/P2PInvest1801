package com.p2p.bawei.p2pinvest1801.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseMVPActivity;
import com.example.framework.base.manager.UserManager;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.example.common.CacheManager;
import com.example.net.mode.UploadBean;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.presenter.RegisterLoginPresenterImpl;

public class LoginActivity extends BaseMVPActivity<RegisterLoginPresenterImpl, RegisterLoginContract.RegisterLoginView> implements RegisterLoginContract.RegisterLoginView {

    private EditText etLoginName;
    private EditText etLoginPwd;
    private Button LoginButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void initGetData() {
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行非空判断
                isNull();
            }
        });
    }

    private void isNull() {
        if(etLoginName.getText().toString().trim().equals("")){
            showMessage("用户名不能为空");
            return;
        }

        if(etLoginPwd.getText().toString().trim().equals("")){
            showMessage("密码不能为空");
            return;
        }

        //进行网络请求
        iHttpPresenter.onGetLogin(etLoginName.getText().toString().trim(),etLoginPwd.getText().toString().trim());
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterLoginPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //进行sp实例
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        etLoginName = (EditText) findViewById(R.id.et_login_name);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        LoginButton = (Button) findViewById(R.id.LoginButton);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {

    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        showMessage("登录成功");
        //保存用户信息
        //用户名
        editor.putString(FinanceConstant.NAME,loginBean.getResult().getName());
        //Token值
        editor.putString(FinanceConstant.TOKEN,loginBean.getResult().getToken());
        //phone
        editor.putString(FinanceConstant.PHONE,loginBean.getResult().getPhone());
        //改变登录状态
        editor.putBoolean(FinanceConstant.ISLOGIN,true);
        editor.commit();
        //设置用户名称
        UserManager.getInstance().setUserName(sharedPreferences.getString(FinanceConstant.NAME,""));
        //重新跳转到主页面
        lunachActivity(MainActivity.class,null);
        //销毁这个页面
        finish();
    }

    @Override
    public void onLoginOutData(UnLoginBean unLoginBean) {

    }

    @Override
    public void uploadData(UploadBean uploadBean) {

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
