package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;

public class MoreFragment  extends BaseFragment<MyHomePresenter> implements MyHomeContract.View {


    @Override
    public int bandLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter(MyHomeBean myHomeBean) {

    }
}
