package com.p2p.bawei.p2pinvest1801.invest.all.view;


import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPFragment;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.all.contract.AllContract;
import com.p2p.bawei.p2pinvest1801.invest.all.presenter.AllPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class IAllFragment extends BaseMVPFragment<AllPresenterImpl, AllContract.IAllView> implements AllContract.IAllView {

    private MyLoadingBar loadingBar;


    @Override
    protected void initHttpData() {
        ihttpPresenter.getAllData();
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new AllPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_iall;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);

    }

    @Override
    public void onAllData(ProductBean productBean) {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }
}
