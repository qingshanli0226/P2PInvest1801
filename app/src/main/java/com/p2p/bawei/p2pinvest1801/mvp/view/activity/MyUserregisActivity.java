package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyRegisterContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.MyRegisterModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MyRegisterPresenter;

public class MyUserregisActivity extends BaseActivity<MyRegisterPresenter> implements MyRegisterContract.mView {
    private EditText phone;
    private EditText username;
    private EditText paw;
    private EditText agpaw;
    private Button butRegister;
    @Override
    public int BondLayout() {
        return R.layout.myuserregis_activity;
    }

    @Override
    public void initview() {
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        paw = findViewById(R.id.paw);
        agpaw = findViewById(R.id.agpaw);
        butRegister = findViewById(R.id.but_register);
    }

    @Override
    public void initdata() {

        butRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String userstr = username.getText().toString().trim();
                 String pawstr = paw.getText().toString().trim();
                 String agpawstr = agpaw.getText().toString().trim();
                if (userstr.equals("") || userstr.equals(null)||pawstr.equals("") || pawstr.equals(null)||agpawstr.equals("") || agpawstr.equals(null)){
                    Toast.makeText(MyUserregisActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pawstr.equals(agpawstr)){
                    mPresenter.getregister();
                }else {


                    Toast.makeText(MyUserregisActivity.this, "两次密码输入不正确!", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }

    @Override
    public void initInJect() {

        mPresenter=new MyRegisterPresenter(new MyRegisterModel(),this);


    }

    @Override
    public void getregister(MyRegisterEntity myRegisterEntity) {
        String result = myRegisterEntity.getResult();
        if (myRegisterEntity.getCode().equals("200")){
            Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getusername() {
        return username.getText().toString().trim();
    }

    @Override
    public String getpaw() {
        return paw.getText().toString().trim();
    }
}
