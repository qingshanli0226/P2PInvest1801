package com.p2p.bawei.p2pinvest1801.adapter;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.ProgressView;

import java.util.List;

public class MyAllAdapter extends BaseQuickAdapter<MyAllBean.ResultBean, BaseViewHolder> {
    public MyAllAdapter(int layoutResId, @Nullable List<MyAllBean.ResultBean> data) {
        super(layoutResId, data);
    }

    public MyAllAdapter(@Nullable List<MyAllBean.ResultBean> data) {
        super(data);
    }

    public MyAllAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAllBean.ResultBean item) {
        helper.setText(R.id.text_title,item.getName());
        helper.setText(R.id.text_money,item.getMoney()+"万");
        helper.setText(R.id.text_2,item.getYearRate()+"%");
        helper.setText(R.id.text_1,item.getMinTouMoney());
        helper.setText(R.id.text_date,item.getSuodingDays());
        helper.setText(R.id.text_3,item.getMemberNum());

        final ProgressView progressView = helper.getView(R.id.progress_view);


        progressView.setProgress(Integer.parseInt(item.getProgress()));

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressView.setFlagBig(true);
            }
        });

    }
}
