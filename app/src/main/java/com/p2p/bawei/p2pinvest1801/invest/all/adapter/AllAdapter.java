package com.p2p.bawei.p2pinvest1801.invest.all.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.all.view.Item_RoundProgressBar;

import java.util.List;

public class AllAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public AllAdapter(int layoutResId, @Nullable List<ProductBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean.ResultBean item) {
        helper.setText(R.id.p_name, item.getName());
        helper.setText(R.id.p_money, item.getMoney());
        helper.setText(R.id.p_yearlv, item.getYearRate());
        helper.setText(R.id.p_suodingdays, item.getSuodingDays());
        helper.setText(R.id.p_minzouzi, item.getMinTouMoney());
        helper.setText(R.id.p_minnum, item.getMemberNum());
        helper.setText(R.id.tv_roundPro, item.getProgress() + "%");

        Item_RoundProgressBar item_roundProgressBar = helper.getView(R.id.roundPro_all);
        float v = Float.parseFloat(item.getProgress());
        item_roundProgressBar.setProgress(v / 100);
    }
}
