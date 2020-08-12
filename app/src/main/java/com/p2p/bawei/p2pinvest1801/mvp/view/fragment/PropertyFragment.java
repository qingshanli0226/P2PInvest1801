package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.widget.Toast;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;

public class PropertyFragment extends BaseFragment {
    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public int bandLayout() {
        return R.layout.propertyfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }
}
