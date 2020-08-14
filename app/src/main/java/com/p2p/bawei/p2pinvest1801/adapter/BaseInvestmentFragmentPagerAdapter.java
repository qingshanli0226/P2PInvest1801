package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseInvestmentFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragment_list;//fragment集合
    private List<String> title_list;//tab标题

    public BaseInvestmentFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragment_list, List<String> title_list) {
        super(fm);
        this.fragment_list = fragment_list;
        this.title_list = title_list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragment_list.get(i);
    }

    @Override
    public int getCount() {
        return title_list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}
