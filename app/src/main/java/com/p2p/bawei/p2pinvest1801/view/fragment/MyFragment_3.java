package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.lib_core.view.BaseFragment;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.model.MyAllModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyFragment_3 extends BaseFragment<MyAllPresenter> implements MyAllContract.View {

    private AutoFlowLayout flowLayout;
    private int[] colors = {0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    private List<String > list_name = new ArrayList<>();


    @Override
    public int bandLayout() {
        return R.layout.fragment_my_3;
    }

    @Override
    public void initView() {

        flowLayout = (AutoFlowLayout) findViewById(R.id.flowLayout);
    }

    @Override
    public void initInject() {

        mPresenter = new MyAllPresenter(new MyAllModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_all();
    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

        List<MyAllBean.ResultBean> result = myAllBean.getResult();
        list_name.clear();
        for (int i = 0; i < result.size(); i++) {
            list_name.add(result.get(i).getName());
            Log.i("wby", "initAdapter: "+list_name);
        }

        flowLayout.setAdapter(new FlowAdapter(list_name) {
            @Override
            public View getView(int i) {

                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_item_fragment_3, null);
                TextView textView = inflate.findViewById(R.id.auto_tv);
                int newcolor = colors[(int) (colors.length * Math.random())];
                GradientDrawable drawable=new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setGradientType(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(60);
                drawable.setColor(newcolor);
                textView.setBackground(drawable);
                textView.setText(list_name.get(i));
                return inflate;
            }
        });
    }
}
