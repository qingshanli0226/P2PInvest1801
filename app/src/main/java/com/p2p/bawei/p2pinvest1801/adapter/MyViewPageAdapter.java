package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPageAdapter extends FragmentPagerAdapter {
    private List<String> list_title;
    private List<Fragment> list_fragment_fragment;

    public MyViewPageAdapter(FragmentManager fm, List<String> list_title, List<Fragment> list_fragment_fragment) {
        super(fm);
        this.list_title = list_title;
        this.list_fragment_fragment = list_fragment_fragment;
    }

    @Override
    public Fragment getItem(int i) {
        return list_fragment_fragment.get(i);
    }

    @Override
    public int getCount() {
        return list_fragment_fragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
