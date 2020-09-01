package com.p2p.bawei.p2pinvest1801.more.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;

import java.util.List;

public class AboutFinanceAdapter extends BaseQuickAdapter<AboutFinanceBean.ResultBean, BaseViewHolder> {
    public AboutFinanceAdapter() {
        super(R.layout.item_about_finance);
    }

    @Override
    protected void convert(BaseViewHolder helper, AboutFinanceBean.ResultBean item) {
        Glide.with(mContext).load(item.getCoverImg()).into((ImageView) helper.getView(R.id.item_about_finance_pic));
    }
}
