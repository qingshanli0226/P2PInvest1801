package com.p2p.bawei.p2pinvest1801.main.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.view.HomeFragment;
import com.p2p.bawei.p2pinvest1801.invest.InvestFragment;
import com.p2p.bawei.p2pinvest1801.main.entity.CommonEntity;
import com.p2p.bawei.p2pinvest1801.more.view.MoreFragment;
import com.p2p.bawei.p2pinvest1801.user.activity.LoginActivity;
import com.p2p.bawei.p2pinvest1801.user.view.UserFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private CommonTabLayout mainCommon;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private UserFragment userFragment;
    private MoreFragment moreFragment;
    private long exitTime = 0;

    //数据
    private final ArrayList<CustomTabEntity> customTabEntityList = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    "android.permission.CALL_PHONE",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.ACCESS_NETWORK_STATE",
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_WIFI_STATE",
                    "android.permission.WAKE_LOCK",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.BROADCAST_PACKAGE_ADDED",
                    "android.permission.BROADCAST_PACKAGE_CHANGED",
                    "android.permission.BROADCAST_PACKAGE_INSTALL",
                    "android.permission.BROADCAST_PACKAGE_REPLACED",
                    "android.permission.GET_TASKS",
                    "android.permission.RECEIVE_BOOT_COMPLETED"
            }, 10101010);
        }
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
                        //判断是否有用户登录
                        isLogin();
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

    private void isLogin() {
        SharedPreferences user_info = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = user_info.getString("name", "");
        if (TextUtils.isEmpty(name)) {
            //本地没有保存过用户信息，给出提示：登录
            doLogin();
        } else {
            //已经登录过，则直接加载用户的信息并显示
        }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //判断用户两次按键的时间差是否在一个预期值之内，是的话直接直接退出，不是的话提示用户再按一次后退键退出。
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再点击一次，退出当前应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                //当返回true时，表示已经完整地处理了这个事件，并不希望其他的回调方法再次进行处理，而当返回false时，
                // 表示并没有完全处理完该事件，更希望其他回调方法继续对其进行处理，
                return true;
            } else {
                removeAll(); //结束当前activity
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    //给出提示：登录
    private void doLogin() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("您还没有登录哦！么么~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //进入登录页面
                        launchActivity(LoginActivity.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
