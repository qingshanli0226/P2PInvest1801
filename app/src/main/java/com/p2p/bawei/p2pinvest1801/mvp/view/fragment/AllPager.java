package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framwork.mvp.view.BaseFragment;
import com.example.net.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.HomeAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.InvestModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.InvestPresenter;

import java.util.ArrayList;
import java.util.List;

public class AllPager extends BaseFragment<InvestPresenter> implements InvestContract.InvestView  {
    List<InvestBean.ResultBean> list_invest = new ArrayList<>();
    HomeAdapter homeAdapter;
    private RecyclerView allRv;
    @Override
    public void initViews() {
        allRv = (RecyclerView) findViewById(R.id.all_rv);
        mPresenter = new InvestPresenter(new InvestModel(), this);
        homeAdapter = new HomeAdapter(R.layout.listview,list_invest);
    }

    @Override
    public void initDatas() {
        mPresenter.investList();
        allRv.setLayoutManager(new LinearLayoutManager(getContext()));
        allRv.setAdapter(homeAdapter);
    }

    @Override
    public int bandLayout() {
        return R.layout.allpage;
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

    }

    @Override
    public void invest(InvestBean investbean) {
        list_invest.addAll(investbean.getResult());
        homeAdapter.notifyDataSetChanged();
    }
}
