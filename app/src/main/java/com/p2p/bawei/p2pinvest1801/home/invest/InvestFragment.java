package com.p2p.bawei.p2pinvest1801.home.invest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.more.MoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends Fragment {

    private View inflate;
    private TabLayout invest_tab;
    private ViewPager invest_vp;
    private List<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_investk, container, false);
        invest_tab=inflate.findViewById(R.id.invest_tab);
        invest_vp=inflate.findViewById(R.id.invest_vp);
        initTab();
        initVp();
        return inflate;
    }

    private void initVp() {
        fragments=new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());
        invest_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        invest_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                invest_tab.setScrollPosition(position,positionOffset,true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTab() {
        invest_tab.addTab(invest_tab.newTab().setText("全部理财"));
        invest_tab.addTab(invest_tab.newTab().setText("推荐理财"));
        invest_tab.addTab(invest_tab.newTab().setText("热门理财"));
//        invest_tab.setTabMode(TabLayout.MODE_FIXED);
        invest_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                invest_vp.setCurrentItem(tab.getPosition());
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
