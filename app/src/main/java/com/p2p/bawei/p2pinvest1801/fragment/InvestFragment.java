package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.common.view.ToolBar;
import com.example.framework.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.InvestAdapter;
import com.p2p.bawei.p2pinvest1801.ui.MyViewPage;

import java.util.ArrayList;

public class InvestFragment extends BaseFragment {

    ToolBar toolBar;
    TabLayout investTab;
    ViewPager investVp;

    //Fragment数据源
    private ArrayList<Fragment> fragments = new ArrayList<>();
    ProductListFragment productListFragment;
    ProductRecommondFragment productRecommondFragment;
    ProductHotFragment productHotFragment;
    //标题数据源
    private ArrayList<String> tabLayoutTitles = new ArrayList<>();
    //适配器
    private InvestAdapter investAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData() {
            //添加数据
            fragments.add(new ProductListFragment());
            fragments.add(new ProductRecommondFragment());
            fragments.add(new ProductHotFragment());
            tabLayoutTitles.add("全部理财");
            tabLayoutTitles.add("推荐理财");
            tabLayoutTitles.add("热门理财");
            investAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initView() {
        toolBar = (ToolBar) findViewById(R.id.toolBar);
        investTab = (TabLayout) findViewById(R.id.investTab);
        investVp = (ViewPager) findViewById(R.id.investVp);
        //创建Fragment对象
//        createFragment();

        //创建适配器
        investAdapter = new InvestAdapter(getActivity().getSupportFragmentManager(),fragments,tabLayoutTitles);
        investVp.setAdapter(investAdapter);
        investTab.setupWithViewPager(investVp);
    }

    private void createFragment() {
            productListFragment = new ProductListFragment();


            productRecommondFragment = new ProductRecommondFragment();



            productHotFragment = new ProductHotFragment();

    }

}
