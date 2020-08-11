package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.p2p.bawei.p2pinvest1801.mvp.presenter.IPresenter;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment,IView {
    protected View mView;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(banLayout(),container,false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public <V extends View> V findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }
}
