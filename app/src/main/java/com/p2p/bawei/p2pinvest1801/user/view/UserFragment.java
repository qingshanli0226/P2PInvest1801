package com.p2p.bawei.p2pinvest1801.user.view;


import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment {
    private TextView tvTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("我的账户");

    }
}
