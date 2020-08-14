package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentAdapter_1;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MoneyFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentAdapter_1 myFragmentAdapter_1;

    private View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_money, container, false);

        initView();
        return inflate;
    }

    private void initView() {
        tabLayout = (TabLayout) inflate.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) inflate.findViewById(R.id.viewPager);

        initTab();
        initViewPage();

    }

    private void initViewPage() {
        myFragmentAdapter_1 = new MyFragmentAdapter_1(getFragmentManager());
        viewPager.setAdapter(myFragmentAdapter_1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tabLayout.setScrollPosition(i,v,false);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initTab() {
        tabLayout.addTab(tabLayout.newTab().setText("全部理财"));
        tabLayout.addTab(tabLayout.newTab().setText("推荐理财"));
        tabLayout.addTab(tabLayout.newTab().setText("热门理财"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
