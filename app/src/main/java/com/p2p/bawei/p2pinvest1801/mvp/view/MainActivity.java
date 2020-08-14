package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.framwork.mvp.view.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.CommonAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.InvestFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.MoreFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.PropertyFragment;

import java.util.ArrayList;
import java.util.List;

//主页面
public class MainActivity extends BaseActivity {
    private FrameLayout mainFramlayout;
    private CommonTabLayout mainCommon;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;
    ArrayList<CustomTabEntity> list = new ArrayList<>();
    @Override
    public void initViews() {
        mainFramlayout = (FrameLayout) findViewById(R.id.main_framlayout);
        mainCommon = (CommonTabLayout) findViewById(R.id.main_common);

        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        moreFragment = new MoreFragment();
        propertyFragment = new PropertyFragment();
        addFragment();
    }

    @Override
    public void initDatas() {
        list.add(new CommonAdapter("首页", R.drawable.bottom02, R.drawable.bottom01));
        list.add(new CommonAdapter("投资", R.drawable.bottom04, R.drawable.bottom03));
        list.add(new CommonAdapter("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        list.add(new CommonAdapter("更多", R.drawable.bottom08, R.drawable.bottom07));
        mainCommon.setTabData(list);
        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
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
                        showFragment(propertyFragment);
                        TextView titleView = mainCommon.getTitleView(position);
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
    public void addFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_framlayout, homeFragment)
                .add(R.id.main_framlayout, investFragment)
                .add(R.id.main_framlayout, moreFragment)
                .add(R.id.main_framlayout, propertyFragment)
                .hide(investFragment)
                .hide(moreFragment)
                .hide(propertyFragment)
                .commit();
    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(investFragment)
                .hide(moreFragment)
                .hide(propertyFragment)
                .show(fragment)
                .commit();
    }



    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(this, "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
