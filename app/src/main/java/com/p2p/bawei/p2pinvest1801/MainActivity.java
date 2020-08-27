package com.p2p.bawei.p2pinvest1801;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.net.connecct.NetConnect;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.bean.TabBean;
import com.p2p.bawei.p2pinvest1801.first.view.FirstFragment;
import com.p2p.bawei.p2pinvest1801.invest.view.InvestFragment;
import com.p2p.bawei.p2pinvest1801.lock.LockFragment;
import com.p2p.bawei.p2pinvest1801.more.view.MoreFragment;
import com.p2p.bawei.p2pinvest1801.user.UserFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private FirstFragment firstFragment;
    private InvestFragment investFragment;
    private UserFragment userFragment;
    private MoreFragment moreFragment;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();

    private long thisTime;

    @Override
    public void onClick(View v) {
    }

    @Override
    public void initView() {
        SharedPreferences lockFlag = getSharedPreferences("lock", MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= 25) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
            }, 100258);
        }

        CommonTabLayout mComTab = initTab();
        initFragment();
        if (lockFlag.getBoolean("lockFlag", false)) {
            startActivity(new Intent(this, LockFragment.class));
        }
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

    private CommonTabLayout initTab() {
        CommonTabLayout mComTab =  findViewById(R.id.com_tab);
        firstFragment = new FirstFragment();
        investFragment = new InvestFragment();
        userFragment = new UserFragment();
        moreFragment = new MoreFragment();
        list.add(new TabBean("首页", R.drawable.bottom02, R.drawable.bottom01));
        list.add(new TabBean("投资", R.drawable.bottom04, R.drawable.bottom03));
        list.add(new TabBean("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        list.add(new TabBean("更多", R.drawable.bottom08, R.drawable.bottom07));
        mComTab.setTabData(list);
        return mComTab;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long l = System.currentTimeMillis();
        long jian = l - thisTime;
        if (jian > 1000) {
            thisTime = l;
            showMessage("再次点击退出应用");
        } else {
            finish();
        }
        return false;
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }
}
 