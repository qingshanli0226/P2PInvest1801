package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lib_core.mvp.view.BaseFragment;
import com.fynn.fluidlayout.FluidLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestmentAllFinancialContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.InvestmentAllFinancialModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.InvestmentAllFinancialPresenter;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragmentHotMoneyFragment extends BaseFragment <InvestmentAllFinancialPresenter> implements InvestmentAllFinancialContract.View  {
    private FluidLayout fluid;
    private List<String> list_data = new ArrayList<>();
    private int[] color = {0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3,0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    @Override
    public void initView() {
        fluid = findViewById(R.id.fluid);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {
//进行数据请求
        mPresenter = new InvestmentAllFinancialPresenter(new InvestmentAllFinancialModel(),this);
        mPresenter.getDta();
    }

    @Override
    public int BandLayout() {
        return R.layout.main_investment_hot_money_fragment;
    }

    @Override
    public void initAllData(InvestmentBean investmentBean) {
        list_data.clear();
        List<InvestmentBean.ResultBean> result = investmentBean.getResult();
        for (int i = 0 ;i < result.size() ; i++){
            list_data.add(result.get(i).getName());
        }
        Log.e("喵喵", "initAllData: "+list_data.size() );
        for (int x = 0; x < list_data.size(); x++) {

            int newcolor = color[(int) (color.length * Math.random())];
            GradientDrawable drawable=new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(60);
            drawable.setColor(newcolor);

            Button tv = new Button(getContext());

            tv.setText(list_data.get(x));
            tv.setBackground(drawable);
            tv.setTextSize(16);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(3, 3, 3, 3);
            fluid.addView(tv, params);
        }



    }
}
