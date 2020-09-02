package com.p2p.bawei.p2pinvest1801.view.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.lib_core.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

/**
 * 提现
 */
public class CashActivity extends BaseActivity<MyAllPresenter> {

    private EditText etInputMoney;
    private Button btnTixian;

    @Override
    public int bandLayout() {
        return R.layout.activity_cash;
    }

    @Override
    public void initView() {

        etInputMoney = (EditText) findViewById(R.id.et_input_money);
        btnTixian = (Button) findViewById(R.id.btn_tixian);

        btnTixian.setClickable(false);
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = etInputMoney.getText().toString();
                if(TextUtils.isEmpty(money)){
                    btnTixian.setClickable(false);
                    btnTixian.setBackgroundColor(R.drawable.btn_02);
                }else {
                    btnTixian.setClickable(true);
                    btnTixian.setBackgroundColor(Color.parseColor("#2ABAED"));

                }
            }
        });

        btnTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CashActivity.this, "正在提现，请稍后...", Toast.LENGTH_SHORT).show();
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
