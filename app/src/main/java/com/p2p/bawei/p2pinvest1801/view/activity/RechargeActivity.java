package com.p2p.bawei.p2pinvest1801.view.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.lib_core.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

/**
 * 充值
 */
public class RechargeActivity extends BaseActivity<MyAllPresenter> {

    private EditText etChongzhi;
    private Button btnChongzhi;
    private ImageView imageBack;

    @Override
    public int bandLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {

        etChongzhi = (EditText) findViewById(R.id.et_chongzhi);
        btnChongzhi = (Button) findViewById(R.id.btn_chongzhi);
        imageBack = (ImageView) findViewById(R.id.image_back);

        btnChongzhi.setClickable(false);
        etChongzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = etChongzhi.getText().toString();
                if(TextUtils.isEmpty(money)){
                    btnChongzhi.setClickable(false);
                    btnChongzhi.setBackgroundColor(R.drawable.btn_02);
                }else {
                    btnChongzhi.setClickable(true);
                    btnChongzhi.setBackgroundColor(Color.parseColor("#2ABAED"));

                }
            }
        });

        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RechargeActivity.this, "正在充值，请稍后...", Toast.LENGTH_SHORT).show();
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
