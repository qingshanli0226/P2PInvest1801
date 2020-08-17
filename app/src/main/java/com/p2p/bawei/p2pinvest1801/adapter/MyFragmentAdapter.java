package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list_fragment;
    private List<String> list_string;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_string) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_string = list_string;
    }

    @Override
    public Fragment getItem(int i) {
        return list_fragment.get(i);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }
//
//    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_string) {
//        super(fm);
//        this.list_fragment = list_fragment;
//        this.list_string = list_string;
//    }
//
//    @Override
//    public Fragment getItem(int i) {
//        return list_fragment.get(i);
//    }
//
//    @Override
//    public int getCount() {
//        return list_fragment.size();
//    }
//
   @Nullable
   @Override
   public CharSequence getPageTitle(int position) {
       return list_string.get(position);
   }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//
//    }




}
