package com.p2p.bawei.p2pinvest1801.invest;


import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.all.view.IAllFragment;
import com.p2p.bawei.p2pinvest1801.invest.all.view.MyViewPager;
import com.p2p.bawei.p2pinvest1801.invest.commend.view.ICommendFragment;
import com.p2p.bawei.p2pinvest1801.invest.hot.view.IHotFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends BaseFragment {
    private final List<String> titleList = new ArrayList<>();
    private final List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        TabLayout tablayout = findViewById(R.id.tablayout);
        MyViewPager viewpager = findViewById(R.id.viewpager);

        fragmentList.add(new IAllFragment());
        fragmentList.add(new ICommendFragment());
        fragmentList.add(new IHotFragment());

        titleList.add("全部理财");
        titleList.add("推荐理财");
        titleList.add("热门理财");

        InvestAdapter investAdapter = new InvestAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
        viewpager.setAdapter(investAdapter);
        tablayout.setupWithViewPager(viewpager);
        tvTitle.setText("投资");
    }
}
