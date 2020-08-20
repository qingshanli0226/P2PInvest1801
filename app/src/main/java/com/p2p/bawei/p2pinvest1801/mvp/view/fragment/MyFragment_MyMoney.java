package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.common.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyMessagesActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyTop_Up_Activity;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyWithdrawDepositActivity;

public class MyFragment_MyMoney extends BaseFragment {
    private Button topUp;
    private Button withdrawDeposit;
    private ImageView setting;
    @Override
    public int BondLayout() {
        return R.layout.myfragmentmymoney;
    }

    @Override
    public void initview() {
        topUp = (Button) findViewById(R.id.top_up);
        withdrawDeposit = (Button) findViewById(R.id.withdraw_deposit);
        setting = (ImageView) findViewById(R.id.setting);
    }

    @Override
    public void initdata() {
        //设置
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyMessagesActivity.class));

            }
        });


        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //充值
                startActivity(new Intent(getActivity(), MyTop_Up_Activity.class));
            }
        });
        //提现
        withdrawDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyWithdrawDepositActivity.class));
            }
        });
    }

    @Override
    public void initInJect() {

    }
}
