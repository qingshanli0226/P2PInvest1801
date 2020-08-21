package com.p2p.bawei.p2pinvest1801.invest.view;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAdapter;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.invest.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.invest.model.InvestModel;
import com.p2p.bawei.p2pinvest1801.invest.presenter.InvestPresenter;

import java.util.ArrayList;
import java.util.List;

public class InvestFirstFragment extends BaseFragment<InvestPresenter> implements InvestContract.View {

    private List<InvestBean.ResultBean> resultBeans;
    private MyAdapter myAdapter;
    private ImageView mAnimationBase;

    @Override
    public void onClick(View v) {


    }

    @Override
    public void initView() {
        RecyclerView mListRv =  findViewById(R.id.list_rv);
        mListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(R.layout.item_layout, resultBeans);
        mListRv.setAdapter(myAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData();
    }

    @Override
    public void initPresenter() {
        mPresenter = new InvestPresenter(this, new InvestModel());
    }

    @Override
    public int bandLayout() {
        return R.layout.invest_first_layout;
    }

    @Override
    public void upDate(InvestBean investBean) {
        if (resultBeans == null) {
            resultBeans = new ArrayList<>();
        }
        resultBeans = investBean.getResult();
        myAdapter.getData().addAll(resultBeans);
        myAdapter.notifyDataSetChanged();
    }
}
