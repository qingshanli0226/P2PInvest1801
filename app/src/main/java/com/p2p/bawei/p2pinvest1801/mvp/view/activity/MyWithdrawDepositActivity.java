package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class MyWithdrawDepositActivity extends BaseActivity {
    private EditText edWithdraw;
    private Button butWithdraw;
    @Override
    public int BondLayout() {
        return R.layout.mywithdrawdeposit_activity;
    }

    @Override
    public void initview() {
        edWithdraw = findViewById(R.id.ed_withdraw);
        butWithdraw = findViewById(R.id.but_withdraw);
    }

    @Override
    public void initdata() {
        edWithdraw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start>=0&&count==1){
                    butWithdraw.setBackgroundColor(Color.parseColor("#28B9EC"));
                }else if (start<=0&&count==0){
                    butWithdraw.setBackgroundColor(Color.GRAY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initInJect() {

    }
}
