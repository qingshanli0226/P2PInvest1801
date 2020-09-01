package com.p2p.bawei.p2pinvest1801.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.activity_bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.myview.ProgressView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class InvestAllAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {
    public InvestAllAdapter( @Nullable List<InvestBean.ResultBean> data) {
        super(R.layout.invest_all_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestBean.ResultBean item) {
        helper.setText(R.id.item_invest_all_name,item.getName());
        helper.setText(R.id.item_invest_all_money,item.getMoney()+"万");
        helper.setText(R.id.item_invest_all_memberNum,item.getMemberNum());
        helper.setText(R.id.item_invest_all_minTouMoney,item.getMinTouMoney());
        helper.setText(R.id.item_invest_all_suodingDays,item.getSuodingDays()+"天");
        helper.setText(R.id.item_invest_all_yearRate,item.getYearRate()+"%");
        ProgressView progressView = helper.getView(R.id.item_invest_all_progress);
        progressView.setProgress(Integer.parseInt(item.getProgress()));
    }


}
