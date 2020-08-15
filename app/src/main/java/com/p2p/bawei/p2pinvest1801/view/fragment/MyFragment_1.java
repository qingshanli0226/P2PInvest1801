package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAllAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.model.MyAllModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.util.List;

public class MyFragment_1 extends BaseFragment<MyAllPresenter> implements MyAllContract.View {

    private RecyclerView recyclerView;
    private MyAllAdapter myAllAdapter;


    @Override
    public int bandLayout() {
        return R.layout.fragment_my_1;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myAllAdapter = new MyAllAdapter(R.layout.layout_fragment_1,null);
        recyclerView.setAdapter(myAllAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAllAdapter.notifyDataSetChanged();



    }

    @Override
    public void initInject() {

        mPresenter = new MyAllPresenter(new MyAllModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_all();
    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

        List<MyAllBean.ResultBean> data = myAllAdapter.getData();
        data.addAll(myAllBean.getResult());
        myAllAdapter.notifyDataSetChanged();
    }
}
