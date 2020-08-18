package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.BaseInvestmentFragmentAllFinancialAdapter;
import com.p2p.bawei.p2pinvest1801.adapter.BaseInvestmentFragmentPagerAdapter;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.divideritemdecoration.BaseDividerItemDecoration;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestmentAllFinancialContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.InvestmentAllFinancialModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.InvestmentAllFinancialPresenter;
import com.p2p.bawei.p2pinvest1801.tool.InvestmentDataTool;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragmentAllFinancialFragment extends BaseFragment<InvestmentAllFinancialPresenter> implements InvestmentAllFinancialContract.View {
    private RecyclerView mainInvestmentAllFinancialViewRecyclerView;
    private List<InvestmentBean.ResultBean> list = new ArrayList<>();
    private BaseInvestmentFragmentAllFinancialAdapter baseInvestmentFragmentAllFinancialAdapter;
    @Override
    public void initView() {
        mainInvestmentAllFinancialViewRecyclerView = (RecyclerView) findViewById(R.id.main_investment_all_financial_view_RecyclerView);

        baseInvestmentFragmentAllFinancialAdapter = new BaseInvestmentFragmentAllFinancialAdapter(R.layout.main_investment_all_financial_item, list);
        mainInvestmentAllFinancialViewRecyclerView.setAdapter(baseInvestmentFragmentAllFinancialAdapter);
        mainInvestmentAllFinancialViewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseDividerItemDecoration baseDividerItemDecoration = new BaseDividerItemDecoration(getContext());
        mainInvestmentAllFinancialViewRecyclerView.addItemDecoration(baseDividerItemDecoration);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {
        //进行数据请求
        mPresenter = new InvestmentAllFinancialPresenter(new InvestmentAllFinancialModel(),this);
        mPresenter.getDta();
    }

    @Override
    public int BandLayout() {
        return R.layout.main_investment_all_financial_fragment;
    }

    @Override
    public void initAllData(InvestmentBean investmentBean) {

        //请求到的数据
        List<InvestmentBean.ResultBean> result = investmentBean.getResult();
        InvestmentDataTool.list.addAll(result);
        list.addAll(result);
        baseInvestmentFragmentAllFinancialAdapter.notifyDataSetChanged();

    }
}
