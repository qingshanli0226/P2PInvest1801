package com.p2p.bawei.p2pinvest1801.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.p2p.bawei.p2pinvest1801.view.fragment.MyFragment_1;
import com.p2p.bawei.p2pinvest1801.view.fragment.MyFragment_2;
import com.p2p.bawei.p2pinvest1801.view.fragment.MyFragment_3;

public class MyFragmentAdapter_1 extends FragmentPagerAdapter {
    public MyFragmentAdapter_1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new MyFragment_1();

            case 1:
                return new MyFragment_2();

            case 2:
                return new MyFragment_3();

                default:
                    return new MyFragment_1();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
