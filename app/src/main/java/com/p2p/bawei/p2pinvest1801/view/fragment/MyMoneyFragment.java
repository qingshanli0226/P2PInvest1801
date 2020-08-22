package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;
import com.p2p.bawei.p2pinvest1801.view.activity.LoginActivity;

public class MyMoneyFragment extends BaseFragment<MyAllPresenter> implements MyAllContract.View, View.OnClickListener {

    private ImageView settings;
    private LinearLayout linear;


    @Override
    public int bandLayout() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void initView() {

        linear = (LinearLayout) findViewById(R.id.linear);
        settings = (ImageView) findViewById(R.id.settings);
        settings.setOnClickListener(this);


    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings:
                Intent intent = new Intent(getActivity(),UserMessageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
