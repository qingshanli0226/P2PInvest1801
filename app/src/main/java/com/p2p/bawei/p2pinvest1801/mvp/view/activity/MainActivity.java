package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.lib_core.mvp.view.BaseActivity;
import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.AssetsFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.InvestmentFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.MoreFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private EasyNavigationBar mainEasy;
    private int[] unSelect = {R.drawable.bottom01,R.drawable.bottom03,R.drawable.bottom05,R.drawable.bottom07};
    private int[] select = {R.drawable.bottom02,R.drawable.bottom04,R.drawable.bottom06,R.drawable.bottom08};
    private String[] title = {"首页","投资","我的资产","更多"};
    private List<Fragment> fragment_List = new ArrayList<>();
    @Override
    public void initView() {
        fragment_List.add(new HomeFragment());
        fragment_List.add(new InvestmentFragment());
        fragment_List.add(new AssetsFragment());
        fragment_List.add(new MoreFragment());
        mainEasy = (EasyNavigationBar) findViewById(R.id.main_easy);
        mainEasy.defaultSetting()
                .selectIconItems(select)
                .normalIconItems(unSelect)
                .titleItems(title)
                .fragmentList(fragment_List)
                .fragmentManager(getSupportFragmentManager())
                .normalTextColor(Color.parseColor("#666666"))
                .selectTextColor(Color.parseColor("#333333"))
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)  //同 ImageView的ScaleType
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        //Tab点击事件  return true 页面不会切换
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        //Tab重复点击事件
                        return false;
                    }
                })
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        mainEasy.setMsgPointCount(0, 0);
                        mainEasy.setMsgPointCount(1, 0);
                        mainEasy.setHintPoint(4, true);
                    }
                })
                .build();


    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.activity_main;
    }
}
