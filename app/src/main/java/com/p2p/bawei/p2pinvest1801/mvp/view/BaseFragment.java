package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.p2p.bawei.p2pinvest1801.mvp.presenter.IPresenter;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment, IView, View.OnClickListener {
    protected P mPresenter;
    private View baseView;

    @Override
    public <V extends View> V findViewById(int id) {
        return baseView.findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(bandLayout(), container, false);
        return baseView;
    }

    @Override
    public void showView() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();
    }

    @Override
    public void hideView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public void showMessage(String message) {

    }

}
