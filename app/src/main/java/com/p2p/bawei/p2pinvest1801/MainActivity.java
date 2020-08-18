package com.p2p.bawei.p2pinvest1801;

import android.Manifest;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.net.connecct.NetConnect;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.bean.TabBean;
import com.p2p.bawei.p2pinvest1801.frist.view.FirstFragment;
import com.p2p.bawei.p2pinvest1801.invest.view.InvestFragment;
import com.p2p.bawei.p2pinvest1801.more.view.MoreFragment;
import com.p2p.bawei.p2pinvest1801.user.UserFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private FirstFragment firstFragment;
    private InvestFragment investFragment;
    private UserFragment userFragment;
    private MoreFragment moreFragment;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();


    @Override
    public void onClick(View v) {
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 25) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            },100258);
        }

        CommonTabLayout mComTab = (CommonTabLayout) findViewById(R.id.com_tab);
        firstFragment = new FirstFragment();
        investFragment = new InvestFragment();
        userFragment = new UserFragment();
        moreFragment = new MoreFragment();
        list.add(new TabBean("首页", R.drawable.bottom02, R.drawable.bottom01));
        list.add(new TabBean("投资", R.drawable.bottom04, R.drawable.bottom03));
        list.add(new TabBean("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        list.add(new TabBean("更多", R.drawable.bottom08, R.drawable.bottom07));
        mComTab.setTabData(list);
        initFragment();
        mComTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        changeFragment(firstFragment);
                        break;
                    case 1:
                        changeFragment(investFragment);
                        break;
                    case 2:
                        changeFragment(userFragment);
                        break;
                    case 3:
                        changeFragment(moreFragment);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.com_box, firstFragment)
                .add(R.id.com_box, investFragment)
                .add(R.id.com_box, userFragment)
                .add(R.id.com_box, moreFragment)
                .commit();
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(firstFragment)
                .hide(moreFragment)
                .hide(userFragment)
                .hide(investFragment)
                .show(fragment)
                .commit();
    }

    @Override
    public void initData() {
        boolean networkConnected = NetConnect.isNetworkConnected(this);
        if (!networkConnected) {
            Toast.makeText(this, "当前网络未连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已连接网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }
}
 