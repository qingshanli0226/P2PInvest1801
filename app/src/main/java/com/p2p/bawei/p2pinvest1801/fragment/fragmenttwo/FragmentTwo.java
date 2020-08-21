package com.p2p.bawei.p2pinvest1801.fragment.fragmenttwo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.fragment.fragmenttwo.thetwo1.FragmentQB;
import com.p2p.bawei.p2pinvest1801.fragment.fragmenttwo.thetwo2.FragmentTJ;
import com.p2p.bawei.p2pinvest1801.fragment.fragmenttwo.thetwo3.FragmentRM;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

import java.util.ArrayList;

public class FragmentTwo extends BaseFragment {
    private TabLayout tabTwoFId;
    private ViewPager viewpagerTwoId;
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_two;
    }

    @Override
    public void initView() {
        tabTwoFId = (TabLayout) findViewById(R.id.Tab_TwoF_id);
        viewpagerTwoId = (ViewPager) findViewById(R.id.viewpager_Two_id);

        initTab();

        list.add(new FragmentQB());
        list.add(new FragmentTJ());
        list.add(new FragmentRM());

        viewpagerTwoId.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewpagerTwoId.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tabTwoFId.setScrollPosition(i,v,true);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabTwoFId.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewpagerTwoId.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initTab() {
        tabTwoFId.addTab(tabTwoFId.newTab().setText("全部理财"));
        tabTwoFId.addTab(tabTwoFId.newTab().setText("推荐理财"));
        tabTwoFId.addTab(tabTwoFId.newTab().setText("热门理财"));
    }

    @Override
    public void initData() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }
}
