package com.p2p.bawei.p2pinvest1801.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.p2p.bawei.p2pinvest1801.mvp.view.fra.Fragment2;

public class MyChildAdapter extends FragmentStatePagerAdapter {
    public MyChildAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_SET_USER_VISIBLE_HINT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragment2.fList.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
