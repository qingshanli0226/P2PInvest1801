package com.p2p.bawei.p2pinvest1801.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.view.ProgressView;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<InvestBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestBean.ResultBean item) {
        ProgressView view = (ProgressView) helper.getView(R.id.progress_item);

        view.setProgress(Integer.parseInt(item.getProgress()));
        helper.setText(R.id.title_item, item.getName());
        helper.setText(R.id.number_price, item.getMoney());
        helper.setText(R.id.number_day, item.getSuodingDays());
        helper.setText(R.id.number_lv, item.getYearRate());
        helper.setText(R.id.number_person, item.getMemberNum());

    }
}
