package com.p2p.bawei.p2pinvest1801.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.lib_core.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.contract.MyLoginContract;
import com.p2p.bawei.p2pinvest1801.model.MyLoginModel;
import com.p2p.bawei.p2pinvest1801.model.MyRegisterModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyLoginPresenter;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity<MyLoginPresenter> implements MyLoginContract.View {

    private ImageView imageBack;
    private EditText editName;
    private EditText editPwd;
    private Button btLogin;
    private String name;
    private String pwd;

    @Override
    public int bandLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        imageBack = (ImageView) findViewById(R.id.image_back);
        editName = (EditText) findViewById(R.id.edit_name);
        editPwd = (EditText) findViewById(R.id.edit_pwd);
        btLogin = (Button) findViewById(R.id.bt_login);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initInject() {

        mPresenter = new MyLoginPresenter(new MyLoginModel(),this);
    }

    @Override
    public void initData() {

        btLogin.setClickable(false);
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()>0){
                    btLogin.setClickable(true);
                    mPresenter.get_login();
                }else {
                    btLogin.setClickable(false);
                }
            }
        });

    }

    @Override
    public String getUsername() {
        name = editName.getText().toString().trim();
        return name ;
    }

    @Override
    public String getPwd() {
        pwd = editPwd.getText().toString().trim();
        return pwd;
    }

    @Override
    public void goMain(LoginBean loginBean) {

        mPresenter.get_login();
    }
}
