package com.p2p.bawei.p2pinvest1801.invest.view;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAdapter;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.invest.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.invest.model.InvestModel;
import com.p2p.bawei.p2pinvest1801.invest.presenter.InvestPresenter;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment<InvestPresenter> implements InvestContract.View {
    private TextView mAllInvest;
    private TextView mTuiInvest;
    private TextView mHotInvest;
    private RecyclerView mListRv;
    private List<InvestBean.ResultBean> resultBeans;
    private MyAdapter myAdapter;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.all_invest:
                upTab(mAllInvest);
                break;
            case R.id.tui_invest:
                upTab(mTuiInvest);
                break;
            case R.id.hot_invest:
                upTab(mHotInvest);
                break;
        }
    }

    private void upTab(TextView txt) {
        int color = getResources().getColor(R.color.tabSelect);
        mTuiInvest.setBackgroundColor(Color.WHITE);
        mHotInvest.setBackgroundColor(Color.WHITE);
        mAllInvest.setBackgroundColor(Color.WHITE);
        mTuiInvest.setTextColor(Color.BLACK);
        mHotInvest.setTextColor(Color.BLACK);
        mAllInvest.setTextColor(Color.BLACK);
        txt.setBackgroundColor(color);
        txt.setTextColor(Color.RED);
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

    @Override
    public void initView() {
        mAllInvest = (TextView) findViewById(R.id.all_invest);
        mTuiInvest = (TextView) findViewById(R.id.tui_invest);
        mHotInvest = (TextView) findViewById(R.id.hot_invest);
        mAllInvest.setOnClickListener(this);
        mTuiInvest.setOnClickListener(this);
        mHotInvest.setOnClickListener(this);

        mListRv = (RecyclerView) findViewById(R.id.list_rv);
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
        return R.layout.invest_layout;
    }
}
