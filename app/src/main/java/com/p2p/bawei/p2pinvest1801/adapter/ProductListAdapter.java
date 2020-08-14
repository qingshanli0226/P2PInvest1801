package com.p2p.bawei.p2pinvest1801.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.mode.InvestListBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.ui.RoundProgress;

import java.util.List;

public class ProductListAdapter extends BaseQuickAdapter<InvestListBean.ResultBean, BaseViewHolder> {

    public ProductListAdapter(int layoutResId, @Nullable List<InvestListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestListBean.ResultBean item) {
        helper.setText(R.id.itemListDays,item.getSuodingDays());
        helper.setText(R.id.itemListMinTouMoney,item.getMinTouMoney());
        helper.setText(R.id.itemListMoney,item.getMoney());
        helper.setText(R.id.itemListName,item.getName());
        helper.setText(R.id.itemListNumber,item.getMemberNum());
        helper.setText(R.id.itemListYearRate,item.getYearRate());
        RoundProgress roundProgress = helper.getView(R.id.itemListProgress);
        roundProgress.setProgress(Integer.parseInt(item.getProgress()));

    }
}
