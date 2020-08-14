package com.p2p.bawei.p2pinvest1801.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.AllBean;
import com.p2p.bawei.p2pinvest1801.zdyview.ProgressView;


import java.util.List;

public class MyAllAdapter extends BaseQuickAdapter<AllBean.ResultBean, BaseViewHolder> {


    public MyAllAdapter(@Nullable List<AllBean.ResultBean> data) {
        super(R.layout.item_ff1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBean.ResultBean item) {
        helper.setText(R.id.itemff1_name, item.getName());
        helper.setText(R.id.item_ff1_money, item.getMoney());
        helper.setText(R.id.item_ff1_minTouMoney, item.getMinTouMoney());
        helper.setText(R.id.item_ff1_yearRate, item.getYearRate());
        helper.setText(R.id.item_ff1_suodingDays, item.getSuodingDays());
        helper.setText(R.id.item_ff1_memberNum, item.getMemberNum());

        ProgressView progressView = helper.getView(R.id.item_ff1_progress);
        progressView.setprogress(Integer.parseInt(item.getProgress()));
    }
}
