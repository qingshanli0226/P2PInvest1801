package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class MyTop_Up_Activity extends BaseActivity {
    private EditText edTopup;
    private Button butTopup;

    @Override
    public int BondLayout() {
        return R.layout.mytop_up_activity;
    }

    @Override
    public void initview() {
        edTopup = (EditText) findViewById(R.id.ed_topup);
        butTopup = (Button) findViewById(R.id.but_topup);
    }

    @Override
    public void initdata() {
        edTopup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start>=0&&count==1){
                    butTopup.setBackgroundColor(Color.parseColor("#28B9EC"));
                }else if (start<=0&&count==0){
                    butTopup.setBackgroundColor(Color.GRAY);
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
