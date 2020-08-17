package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class InvestmentViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> names;
    private ArrayList<Fragment> fragments;

    public InvestmentViewPagerAdapter(FragmentManager fm, ArrayList<String> names, ArrayList<Fragment> fragments) {
        super(fm);
        this.names = names;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
