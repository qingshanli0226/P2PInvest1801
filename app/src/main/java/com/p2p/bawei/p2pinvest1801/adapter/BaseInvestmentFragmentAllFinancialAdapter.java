package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.baseview.BaseProgressView;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;

import java.util.List;

public class BaseInvestmentFragmentAllFinancialAdapter extends BaseQuickAdapter<InvestmentBean.ResultBean, BaseViewHolder> {
    public BaseInvestmentFragmentAllFinancialAdapter(int layoutResId, @Nullable List<InvestmentBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestmentBean.ResultBean item) {

        helper.setText(R.id.main_investment_all_financial_view_item_money,item.getMoney());
        helper.setText(R.id.main_investment_all_financial_view_item_title,item.getName());
        helper.setText(R.id.main_investment_all_financial_view_item_memberNum,item.getMemberNum());
        helper.setText(R.id.main_investment_all_financial_view_item_minTouMoney,item.getMinTouMoney());
        helper.setText(R.id.main_investment_all_financial_view_item_suodingDays,item.getSuodingDays());
        helper.setText(R.id.main_investment_all_financial_view_item_yearRate,item.getYearRate());
        BaseProgressView view = helper.getView(R.id.main_investment_all_financial_view_item_BaseProgressView);
        String progress = item.getProgress();
        int i = Integer.parseInt(progress);
        view.setProgress(i);
    }
}
