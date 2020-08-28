package com.p2p.bawei.p2pinvest1801;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.CacheManager;
import com.example.common.FinanceConstant;
import com.example.framework.base.BaseActivity;
import com.example.framework.base.manager.UserManager;
import com.example.net.mode.BannerBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.activity.LoginActivity;
import com.p2p.bawei.p2pinvest1801.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.fragment.InvestFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MeFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MoreFragment;
import com.p2p.bawei.p2pinvest1801.mode.CommonCustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    public static final int REQUESTCODE = 120;

    private SharedPreferences sharedPreferences;

    private CommonTabLayout common;

    //common数据源
    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    private long firstTime = 0;
    //储存Fragment的集合
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private Fragment currentFragment;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int index = getIntent().getBundleExtra(FinanceConstant.BUNDLE).getInt(FinanceConstant.INDEX, 1);
        switchFragment(fragmentArrayList.get(index));
        common.getIconView(index).setImageResource(R.mipmap.bottom02);
        common.getIconView(2).setImageResource(R.mipmap.bottom05);
    }

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
                        switchFragment(homeFragment);
                        break;
                    case 1:
                        switchFragment(investFragment);
                        break;
                    case 2:
                        switchFragment(meFragment);
                        TextView titleView = common.getTitleView(position);
                        titleView.setTextColor(Color.RED);
                        //判断登录状态
                        isLogin();
                        break;
                    case 3:
                        switchFragment(moreFragment);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void isLogin() {
        //获取登录状态
        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        if(aBoolean){
            //登录过
        } else{
            //第一次登录
            //弹出对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = LayoutInflater.from(this).inflate(R.layout.myview_login, null);
            builder.setView(inflate);
            final AlertDialog alertDialog = builder.create();
            //设置外部点击不可取消
//            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();

            //获取yesLogin按钮
            Button yesLogin = inflate.findViewById(R.id.yesLogin);

            //点击跳转到登录页面
            yesLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    lunachActivity(LoginActivity.class,null);
                }
            });
        }
    }

    @Override
    protected void initView() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //获取权限
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUESTCODE);

        }

        //获取sp的实例
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();

        common = (CommonTabLayout) findViewById(R.id.common);

        //创建Fragment实例对象
        createFragment();
        //添加数据
        fragmentArrayList.add(homeFragment);
        fragmentArrayList.add(investFragment);
        fragmentArrayList.add(meFragment);
        fragmentArrayList.add(moreFragment);
        switchFragment(homeFragment);

    }
    //判断权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUESTCODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0){
                    //有权限
                } else{
                    showMessage("没有此权限");
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

}

    private void initState() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        //设置底部导航栏不会遮挡布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    //显示Fragment的方法
    private void switchFragment(Fragment fragment) {
        if(currentFragment == fragment){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment!=null) {
            fragmentTransaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add( R.id.frame, fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
    }


    private void createFragment() {
        if(homeFragment == null){
            homeFragment = new HomeFragment();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - firstTime > 2000){
                showMessage("请在两秒内再按一次退出App");
                firstTime = System.currentTimeMillis();
            } else{
                //解绑服务
                UserManager.getInstance().unBindFinanceService();
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
