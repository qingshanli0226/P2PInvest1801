package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mysef.MyPragessagerView;

import java.util.List;

public class MyFragmentfragmentAdapter extends BaseQuickAdapter<MyInestEntivity.ResultBean, BaseViewHolder> {
    public MyFragmentfragmentAdapter(int layoutResId, @Nullable List<MyInestEntivity.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInestEntivity.ResultBean item) {
        helper.setText(R.id.text_jh,item.getName());
        helper.setText(R.id.wan,item.getMoney());
        helper.setText(R.id.bfb,item.getYearRate());
        helper.setText(R.id.day,item.getSuodingDays());
        helper.setText(R.id.minTouMoney,item.getMinTouMoney());
        helper.setText(R.id.memberNum,item.getMemberNum());
        MyPragessagerView pv = helper.getView(R.id.pv);
        pv.setProgress(Integer.parseInt(item.getProgress()));
    }
}
