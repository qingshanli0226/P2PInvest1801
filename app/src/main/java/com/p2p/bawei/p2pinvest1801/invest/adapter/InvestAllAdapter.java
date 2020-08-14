package com.p2p.bawei.p2pinvest1801.invest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.custom.ProgressView;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;

import java.util.List;

public class InvestAllAdapter extends BaseQuickAdapter<InvestAllBean.ResultBean, BaseViewHolder> {


    public InvestAllAdapter() {
        super(R.layout.item_invest_all);
    }
    @Override
    protected void convert(BaseViewHolder helper, InvestAllBean.ResultBean item) {
        helper.setText(R.id.item_invest_all_name, item.getName());
        helper.setText(R.id.item_invest_all_money, item.getMoney());
        helper.setText(R.id.item_invest_all_minTouMoney, item.getMinTouMoney());
        helper.setText(R.id.item_invest_all_yearRate, item.getYearRate());
        helper.setText(R.id.item_invest_all_suodingDays, item.getSuodingDays());
        helper.setText(R.id.item_invest_all_memberNum, item.getMemberNum());

        ProgressView progressView = helper.getView(R.id.item_invest_all_progress);
        progressView.setProgress(Integer.parseInt(item.getProgress()));

    }
}
