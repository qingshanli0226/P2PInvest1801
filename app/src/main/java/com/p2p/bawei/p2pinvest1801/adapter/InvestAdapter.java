package com.p2p.bawei.p2pinvest1801.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class InvestAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayList;
    private ArrayList<String> titles;

    public InvestAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> arrayList, ArrayList<String> titles) {
        super(fm);
        this.arrayList = arrayList;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
