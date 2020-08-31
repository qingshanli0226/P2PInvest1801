package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;

import com.bw.common.view.MyProgressBar;
import com.bw.net.bean.AllLCBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class Mine_LCAdapter extends BaseQuickAdapter<AllLCBean.ResultBean, BaseViewHolder> {
    public Mine_LCAdapter(int layoutResId, @Nullable List<AllLCBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllLCBean.ResultBean item) {
        helper.setText(R.id.list_text,item.getName());
        helper.setText(R.id.list_wan,item.getMoney());
        helper.setText(R.id.list_bai,item.getYearRate());
        helper.setText(R.id.list_day,item.getSuodingDays());
        helper.setText(R.id.list_num1,item.getMemberNum());
        helper.setText(R.id.list_num2,item.getMinTouMoney());

        MyProgressBar myProgressBar = helper.getView(R.id.list_progress);
        myProgressBar.setProgress(Integer.parseInt(item.getProgress()));
        myProgressBar.unProgressBarAnimation();
    }
}
