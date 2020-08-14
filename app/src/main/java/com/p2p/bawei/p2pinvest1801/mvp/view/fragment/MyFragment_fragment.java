package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.common.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentfragmentAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyInvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.MyInvestModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MyGetInvestPresenter;

import java.util.List;

public class MyFragment_fragment extends BaseFragment<MyGetInvestPresenter> implements MyInvestContract.mView {
    private RecyclerView rv;
    @Override
    public int BondLayout() {
        return R.layout.myfragmentfragment;
    }

    @Override
    public void initview() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void initInJect() {
        mPresenter=new MyGetInvestPresenter(new MyInvestModel(),this);
        mPresenter.getInvest();
    }

    @Override
    public void getinvest(MyInestEntivity myInestEntivity) {
        List<MyInestEntivity.ResultBean> beans = myInestEntivity.getResult();
        MyFragmentfragmentAdapter myFragmentfragmentAdapter = new MyFragmentfragmentAdapter(R.layout.myinvestjh, beans);
        rv.setAdapter(myFragmentfragmentAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
