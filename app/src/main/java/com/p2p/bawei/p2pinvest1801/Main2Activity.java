package com.p2p.bawei.p2pinvest1801;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.FragmentFour;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentone.FragmentOne;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.FragmentThree;
import com.p2p.bawei.p2pinvest1801.fragment.fragmenttwo.FragmentTwo;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity {
    private ViewPager viewpager;
    private TabLayout tabIdLayout;
    private TextView tvIdAct;
    private ImageView ivIdAct;
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
        tvIdAct = findViewById(R.id.tv_id_act);
        ivIdAct = findViewById(R.id.iv_id_act);

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
                if (position == 0){
                    tvIdAct.setText("首页");
                    ivIdAct.setVisibility(View.INVISIBLE);
                    tabIdLayout.getTabAt(position).setIcon(R.drawable.bottom02);
                    tabIdLayout.getTabAt(position+1).setIcon(R.drawable.bottom03);
                    tabIdLayout.getTabAt(position+2).setIcon(R.drawable.bottom05);
                    tabIdLayout.getTabAt(position+3).setIcon(R.drawable.bottom07);
                }else if (position ==1){
                    tvIdAct.setText("投资");
                    ivIdAct.setVisibility(View.INVISIBLE);
                    tabIdLayout.getTabAt(position).setIcon(R.drawable.bottom04);
                    tabIdLayout.getTabAt(position-1).setIcon(R.drawable.bottom01);
                    tabIdLayout.getTabAt(position+1).setIcon(R.drawable.bottom05);
                    tabIdLayout.getTabAt(position+2).setIcon(R.drawable.bottom07);
                }else if (position == 2){
                    tvIdAct.setText("我的资产");
                    ivIdAct.setVisibility(View.VISIBLE);
                    tabIdLayout.getTabAt(position).setIcon(R.drawable.bottom06);
                    tabIdLayout.getTabAt(position-2).setIcon(R.drawable.bottom01);
                    tabIdLayout.getTabAt(position-1).setIcon(R.drawable.bottom03);
                    tabIdLayout.getTabAt(position+1).setIcon(R.drawable.bottom07);

                }else if (position == 3){
                    tvIdAct.setText("更多");
                    ivIdAct.setVisibility(View.INVISIBLE);
                    tabIdLayout.getTabAt(position).setIcon(R.drawable.bottom08);
                    tabIdLayout.getTabAt(position-1).setIcon(R.drawable.bottom05);
                    tabIdLayout.getTabAt(position-2).setIcon(R.drawable.bottom03);
                    tabIdLayout.getTabAt(position-3).setIcon(R.drawable.bottom01);
                }
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
        tabIdLayout.addTab(tabIdLayout.newTab().setText("首页").setIcon(R.drawable.bottom02));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("投资").setIcon(R.drawable.bottom03));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("我的资产").setIcon(R.drawable.bottom05));
        tabIdLayout.addTab(tabIdLayout.newTab().setText("更多").setIcon(R.drawable.bottom07));
    }

    @Override
    public void initData() {

    }
}
