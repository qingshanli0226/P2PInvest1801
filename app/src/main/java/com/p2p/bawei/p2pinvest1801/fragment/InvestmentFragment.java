package com.p2p.bawei.p2pinvest1801.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.bw.common.view.MyViewpager;
import com.bw.framwork.view.BaseFragment;
import com.bw.framwork.view.IView;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.InvestmentViewPagerAdapter;

import java.util.ArrayList;

public class InvestmentFragment extends BaseFragment implements IView {
    private TabLayout tabLayout;
    private MyViewpager viewPager;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private ArrayList<String> tabnames=new ArrayList<>();
    private InvestmentViewPagerAdapter adapter;

    @Override
    public void initView() {
        tabLayout= (TabLayout) findViewById(R.id.investment_tab);
        viewPager= (MyViewpager) findViewById(R.id.investment_vp);

        adapter=new InvestmentViewPagerAdapter(getActivity().getSupportFragmentManager(), tabnames, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {
        tabnames.clear();
        fragments.clear();

        tabnames.add("全部理财");
        tabnames.add("推荐理财");
        tabnames.add("热门理财");

        fragments.add(new Mine_LC());
        fragments.add(new Recommend_LC());
        fragments.add(new Hot_LC());
        adapter.notifyDataSetChanged();
    }

    @Override
    public int bandLayout() {
        return R.layout.investmentfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }
}
