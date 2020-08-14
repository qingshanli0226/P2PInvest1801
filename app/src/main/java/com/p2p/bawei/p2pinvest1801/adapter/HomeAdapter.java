package com.p2p.bawei.p2pinvest1801.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.ProgressView;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<InvestBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestBean.ResultBean item) {
        helper.setText(R.id.list_text, item.getName());
        helper.setText(R.id.list_bai, item.getYearRate());
        helper.setText(R.id.list_day, item.getSuodingDays());
        helper.setText(R.id.list_num1, item.getMinTouMoney());
        helper.setText(R.id.list_num2, item.getMemberNum());
        ProgressView view = helper.getView(R.id.list_progress);
        view.setProgress(Integer.parseInt(item.getProgress()));
        helper.setText(R.id.list_wan, item.getMoney());
    }
}
