package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.BaseInvestmentFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragment extends BaseFragment {

    private List<Fragment> fragment_list = new ArrayList<>();//fragment集合
    private List<String> title_list = new ArrayList<>();//tab标题

    @Override
    public void initView() {
        TabLayout mainInvestmentViewTabLayout = (TabLayout) findViewById(R.id.main_investment_view_tabLayout);
        ViewPager mainInvestmentViewViewpager = (ViewPager) findViewById(R.id.main_investment_view_viewpager);
        //初始化fragment
        InvestmentFragmentAllFinancialFragment investmentFragmentAllFinancialFragment = new InvestmentFragmentAllFinancialFragment();
        InvestmentFragmentRecommendAFinancialFragment investmentFragmentRecommendAFinancialFragment = new InvestmentFragmentRecommendAFinancialFragment();
        InvestmentFragmentHotMoneyFragment investmentFragmentHotMoneyFragment = new InvestmentFragmentHotMoneyFragment();
        //吧初始化的fragment添加到集合中
        fragment_list.add(investmentFragmentAllFinancialFragment);
        fragment_list.add(investmentFragmentRecommendAFinancialFragment);
        fragment_list.add(investmentFragmentHotMoneyFragment);
        title_list.add("全部理财");
        title_list.add("推荐理财");
        title_list.add("热门理财");
        BaseInvestmentFragmentPagerAdapter baseInvestmentFragmentPagerAdapter = new BaseInvestmentFragmentPagerAdapter(getChildFragmentManager(), fragment_list, title_list);
        mainInvestmentViewViewpager.setAdapter(baseInvestmentFragmentPagerAdapter);
        //吧viewpager和tabLayout联动起来
        mainInvestmentViewTabLayout.setupWithViewPager(mainInvestmentViewViewpager);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.main_investment_layout_fragment;
    }
}
