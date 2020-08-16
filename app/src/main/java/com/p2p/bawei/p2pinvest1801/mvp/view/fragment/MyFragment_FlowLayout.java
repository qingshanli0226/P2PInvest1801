package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.common.mvp.view.BaseFragment;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentfragmentAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyInvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.MyInvestModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MyGetInvestPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyFragment_FlowLayout extends BaseFragment<MyGetInvestPresenter> implements MyInvestContract.mView {
    private AutoFlowLayout fl;
    private List<String> list_string=new ArrayList<>();
    private int[] colors = {0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    @Override
    public int BondLayout() {
        return R.layout.myfragmentflowlayout;
    }

    @Override
    public void initview() {
        fl = (AutoFlowLayout) findViewById(R.id.fl);

    }

    @Override
    public void initdata() {

    }

    @Override
    public void initInJect() {
        mPresenter=new MyGetInvestPresenter(new MyInvestModel(),this);
        mPresenter.getInvest();
    }

    @Override
    public void getinvest(MyInestEntivity myInestEntivity) {
        List<MyInestEntivity.ResultBean> beans = myInestEntivity.getResult();
        for (int i = 0; i < beans.size(); i++) {
            list_string.add(beans.get(i).getName());
            Log.e("cx", "getinvest: "+beans.get(i).getName() );
        }
        fl.setAdapter(new FlowAdapter(list_string) {
            @Override
            public View getView(int i) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.textviewflow, null, false);
                TextView tw = inflate.findViewById(R.id.tw);
                tw.setText(list_string.get(i));
                int newcolor = colors[(int) (colors.length * Math.random())];
                GradientDrawable drawable=new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setGradientType(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(60);
                drawable.setColor(newcolor);
                tw.setBackground(drawable);
                return inflate;
            }
        });
    }
}
