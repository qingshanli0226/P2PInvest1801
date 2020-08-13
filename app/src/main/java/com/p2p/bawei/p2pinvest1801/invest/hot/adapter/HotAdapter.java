package com.p2p.bawei.p2pinvest1801.invest.hot.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class HotAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    private String[] color = {
            "#FF34B3", "#9ACD32", "#9400D3", "#EE9A00",
            "#483D8B", "#1E90FF", "#00BFFF", "#F0a420"
    };

    public HotAdapter(int layoutResId, @Nullable List<ProductBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean.ResultBean item) {
        TextView textView = helper.getView(R.id.hot_item_tv);
        textView.setText(item.getName());

        textView.setBackgroundColor(android.graphics.Color.parseColor(color[(int) (color.length * Math.random())]));
    }
}
