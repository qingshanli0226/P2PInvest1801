package com.p2p.bawei.p2pinvest1801;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.framework2.mvp.view.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.home.asset.AssetFragment;
import com.p2p.bawei.p2pinvest1801.home.index.IndexFragment;
import com.p2p.bawei.p2pinvest1801.home.invest.InvestFragment;
import com.p2p.bawei.p2pinvest1801.home.more.MoreFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private TextView me_title;
    private ViewPager me_vp;
    private TabLayout me_tab;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView asset_set;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        fragmentList.add(new IndexFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new AssetFragment());
        fragmentList.add(new MoreFragment());
        me_title = findViewById(R.id.me_title);
        me_tab = findViewById(R.id.me_tab);
        me_vp = findViewById(R.id.me_vp);
        asset_set=findViewById(R.id.asset_set);
        initTab();
        initVp();
    }

    private void initVp() {
        me_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        me_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                me_tab.setScrollPosition(position, positionOffset, false);
//                me_tab.selectTab(me_tab.getTabAt(position));

            }

            @Override
            public void onPageSelected(int position) {
                setMe_title(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTab() {
        me_tab.addTab(me_tab.newTab().setText("首页").setIcon(R.drawable.index_select));
        me_tab.addTab(me_tab.newTab().setText("投资").setIcon(R.drawable.investk_select));
        me_tab.addTab(me_tab.newTab().setText("我的资产").setIcon(R.drawable.asset_select));
        me_tab.addTab(me_tab.newTab().setText("更多").setIcon(R.drawable.more_select));
//        me_tab.setupWithViewPager(me_vp);
        me_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                me_vp.arrowScroll(tab.getPosition());
                me_vp.setCurrentItem(tab.getPosition());
                setMe_title(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setMe_title(int position) {
        switch (position) {
            case 0:
                me_title.setText("首页");
                asset_set.setVisibility(View.GONE);
                break;
            case 1:
                me_title.setText("投资");
                asset_set.setVisibility(View.GONE);
                break;
            case 2:
                me_title.setText("我的资产");
                asset_set.setVisibility(View.VISIBLE);
                break;
            case 3:
                me_title.setText("更多");
                asset_set.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    private long time;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long timeMillis = System.currentTimeMillis();
            if (timeMillis - time > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                time = timeMillis;
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
