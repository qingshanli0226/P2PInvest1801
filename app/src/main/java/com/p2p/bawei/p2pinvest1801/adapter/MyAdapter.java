package com.p2p.bawei.p2pinvest1801.adapter;

import android.util.Log;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.example.baselibrary.view.ProgressView;
import java.util.List;

public class MyAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<InvestBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestBean.ResultBean item) {
        Log.d("hq", "Integer.parseInt(item.getProgress()):" + Integer.parseInt(item.getProgress()));
        ProgressView viewById = helper.itemView.findViewById(R.id.progress_item);
        viewById.setProgress(Integer.parseInt(item.getProgress()));
        helper.setText(R.id.title_item, item.getName());
        helper.setText(R.id.number_price, item.getMoney());
        helper.setText(R.id.number_day, item.getSuodingDays());
        helper.setText(R.id.number_lv, item.getYearRate());
        helper.setText(R.id.number_person, item.getMemberNum());
        helper.setText(R.id.number,item.getMinTouMoney());
    }
}
