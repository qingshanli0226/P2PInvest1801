package com.p2p.bawei.p2pinvest1801.main.view;

import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.entity.CommonEntity;
import com.p2p.bawei.p2pinvest1801.home.view.HomeFragment;
import com.p2p.bawei.p2pinvest1801.invest.InvestFragment;
import com.p2p.bawei.p2pinvest1801.more.view.MoreFragment;
import com.p2p.bawei.p2pinvest1801.user.view.UserFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private FrameLayout mainFrameLayout;
    private CommonTabLayout mainCommon;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private UserFragment userFragment;
    private MoreFragment moreFragment;

    //数据
    private ArrayList<CustomTabEntity> customTabEntityList = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mainFrameLayout = findViewById(R.id.main_framelayout);
        mainCommon = findViewById(R.id.main_common);

        //初始化common
        initCommon();

    }

    private void initCommon() {
        initFragment();

        customTabEntityList.add(new CommonEntity("首页", R.drawable.bottom02, R.drawable.bottom01));
        customTabEntityList.add(new CommonEntity("投资", R.drawable.bottom04, R.drawable.bottom03));
        customTabEntityList.add(new CommonEntity("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        customTabEntityList.add(new CommonEntity("更多", R.drawable.bottom08, R.drawable.bottom07));

        mainCommon.setTabData(customTabEntityList);
        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        showFragment(homeFragment);
                        break;
                    case 1:
                        showFragment(investFragment);
                        break;
                    case 2:
                        mainCommon.getTitleView(2).setTextColor(Color.RED);
                        showFragment(userFragment);
                        break;
                    case 3:
                        showFragment(moreFragment);
                        break;
                    default:
                        showFragment(homeFragment);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.hide(homeFragment);
        transaction.hide(investFragment);
        transaction.hide(userFragment);
        transaction.hide(moreFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        userFragment = new UserFragment();
        moreFragment = new MoreFragment();

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.main_framelayout, homeFragment);
        transaction.add(R.id.main_framelayout, investFragment);
        transaction.add(R.id.main_framelayout, userFragment);
        transaction.add(R.id.main_framelayout, moreFragment);
        transaction.hide(investFragment);
        transaction.hide(userFragment);
        transaction.hide(moreFragment);
        transaction.commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
