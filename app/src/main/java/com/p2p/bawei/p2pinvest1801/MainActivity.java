package com.p2p.bawei.p2pinvest1801;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.fragment.InvestFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MeFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MoreFragment;
import com.p2p.bawei.p2pinvest1801.mode.CommonCustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private CommonTabLayout common;
    //common数据源
    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    //banner数据源
    private ArrayList<String> stringArrayList;

    @Override
    protected void initData() {
        arrayList.add("首页");
        arrayList.add("投资");
        arrayList.add("我的资产");
        arrayList.add("更多");
        //添加common数据
        customTabEntities.add(new CommonCustomTabEntity(getTabView(0),R.mipmap.bottom02,R.mipmap.bottom01));
        customTabEntities.add(new CommonCustomTabEntity(getTabView(1),R.mipmap.bottom04,R.mipmap.bottom03));
        customTabEntities.add(new CommonCustomTabEntity(getTabView(2),R.mipmap.bottom06,R.mipmap.bottom05));
        customTabEntities.add(new CommonCustomTabEntity(getTabView(3),R.mipmap.bottom08,R.mipmap.bottom07));
        //设置数据
        common.setTabData(customTabEntities);
        //监听
        common.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                        showFragment(homeFragment);
                        break;
                    case 1:
                        showFragment(investFragment);
                        break;
                    case 2:
                        showFragment(meFragment);
                        TextView titleView = common.getTitleView(position);
                        titleView.setTextColor(Color.RED);
                        break;
                    case 3:
                        showFragment(moreFragment);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initView() {
        common = (CommonTabLayout) findViewById(R.id.common);
        //获取传递过来的banner数据
        Bundle bundleExtra = getIntent().getBundleExtra(FinanceConstant.BUNDLE);
        stringArrayList = bundleExtra.getStringArrayList(FinanceConstant.BUNDLE_BANNER);

        //创建Fragment实例对象
        createFragment();

        addFragment(homeFragment);
    }

    //显示Fragment的方法
    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(investFragment)
                .hide(meFragment)
                .hide(moreFragment)
                .replace(R.id.frame,fragment)
                .show(fragment)
                .commit();
    }

    //默认显示Fragment的方法
    public void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame,fragment)
                .commit();
    }

    private void createFragment() {
        if(homeFragment == null){
            homeFragment = new HomeFragment(stringArrayList);
        }
        if(investFragment == null){
            investFragment = new InvestFragment();
        }
        if(meFragment == null){
            meFragment = new MeFragment();
        }
        if(moreFragment == null){
            moreFragment = new MoreFragment();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public TextView getTabView(int position){
        View inflate = LayoutInflater.from(this).inflate(R.layout.common_layout, null);
        TextView textView = inflate.findViewById(R.id.commonTv);
        textView.setText(arrayList.get(position));
        return textView;
    }

}
