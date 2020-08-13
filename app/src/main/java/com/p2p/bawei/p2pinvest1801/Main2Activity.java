package com.p2p.bawei.p2pinvest1801;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.fragmentfour.FragmentFour;
import com.p2p.bawei.p2pinvest1801.fragmentone.FragmentOne;
import com.p2p.bawei.p2pinvest1801.fragmentthree.FragmentThree;
import com.p2p.bawei.p2pinvest1801.fragmenttwo.FragmentTwo;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity {
    private ViewPager viewpager;
    private TabLayout tabIdLayout;
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    public void onClick(View v) {

    }

    @Override
    public int banLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        viewpager = findViewById(R.id.viewpager);
        tabIdLayout = findViewById(R.id.tab_id_layout);

        initTab();

        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FragmentThree());
        list.add(new FragmentFour());

        viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tabIdLayout.setScrollPosition(i,v,true);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabIdLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewpager.setCurrentItem(position);
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
        tabIdLayout.addTab(tabIdLayout.newTab().setText("1").setIcon(R.mipmap.ic_launcher));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("2").setIcon(R.mipmap.ic_launcher));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("3").setIcon(R.mipmap.ic_launcher));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("4").setIcon(R.mipmap.ic_launcher));
    }

    @Override
    public void initData() {

    }
}
