package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.lib_core.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.contract.MyRegisterContract;
import com.p2p.bawei.p2pinvest1801.model.MyRegisterModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyRegisterPresenter;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity<MyRegisterPresenter> implements MyRegisterContract.View {

    private EditText editPhone;
    private EditText editName;
    private EditText editPwd;
    private EditText editQrpwd;
    private Button btRegister;
    private ImageView imageBack;


    @Override
        public int bandLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

        editPhone = (EditText) findViewById(R.id.edit_phone);
        editName = (EditText) findViewById(R.id.edit_name);
        editPwd = (EditText) findViewById(R.id.edit_pwd);
        editQrpwd = (EditText) findViewById(R.id.edit_qrpwd);
        btRegister = (Button) findViewById(R.id.bt_register);
        imageBack = (ImageView) findViewById(R.id.image_back);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initInject() {

        mPresenter = new MyRegisterPresenter(new MyRegisterModel(),this);
    }

    @Override
    public void initData() {

         btRegister.setClickable(false);

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
                     btRegister.setClickable(true);
                     mPresenter.get_register();
                 }else {
                     btRegister.setClickable(false);
                     Toast.makeText(RegisterActivity.this, "请输入要注册的用户名密码", Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }

    @Override
    public String getUsername() {
        return editName.getText().toString();
    }

    @Override
    public String getPwd() {
        return editPwd.getText().toString();
    }

    @Override
    public void goMain(RegisterBean registerBean) {

        mPresenter.get_register();
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();


    }
}
