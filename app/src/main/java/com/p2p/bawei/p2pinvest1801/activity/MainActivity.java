package com.p2p.bawei.p2pinvest1801.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.bw.common.view.BottomBar;
import com.bw.framwork.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.p2p.bawei.p2pinvest1801.cacheError.CacheErrorManager;
import com.p2p.bawei.p2pinvest1801.fragment.HomePageFragment;
import com.p2p.bawei.p2pinvest1801.fragment.InvestmentFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MindFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MoreFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {    //王俊豪练习项目

    private FragmentTransaction transaction;
    private BottomBar bottomBar;
    private HomePageFragment homePageFragment;
    private InvestmentFragment investmentFragment;
    private MindFragment mindFragment;
    private MoreFragment moreFragment;

    private static final int HOMEPAGE = 0;   //首页
    private static final int INVESTMENT = 1; //投资
    private static final int MIND = 2;       //我的资产
    private static final int MORE = 3;        //更多


    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        setFragment(HOMEPAGE);  //默认显示首页

        bottomBar = findViewById(R.id.myBottomBar);
        bottomBar.setBottomBarSelectListener(new BottomBar.IBottomBarSelectListener() {
            @Override
            public void onBottomBarSelected(int selectIndex) {
                setFragment(selectIndex);
            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        //动态权限申请
        new RxPermissions(this).request(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (!aBoolean) {
                            showMsg("将无法完成文件储存。。");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }


    private void setFragment(int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        transaction = supportFragmentManager.beginTransaction();

        hideFragment();
        switch (i) {
            case HOMEPAGE:
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.main_vp, homePageFragment);
                }


                transaction.show(homePageFragment);


                break;
            case INVESTMENT:
                if (investmentFragment == null) {
                    investmentFragment = new InvestmentFragment();
                    transaction.add(R.id.main_vp, investmentFragment);
                }

                transaction.show(investmentFragment);


                break;
            case MIND:
                if (mindFragment == null) {
                    mindFragment = new MindFragment();
                    transaction.add(R.id.main_vp, mindFragment);
                }

                transaction.show(mindFragment);
                if (!UserManager.getInstance().isLogin()) {  //判断是否已经登录了。
                    initDalog();
                }
                break;
            case MORE:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_vp, moreFragment);
                }

                transaction.show(moreFragment);

                break;
        }

        transaction.commit();
    }

    private void hideFragment() {  //隐藏所有的fragment
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }

        if (investmentFragment != null) {
            transaction.hide(investmentFragment);
        }

        if (mindFragment != null) {
            transaction.hide(mindFragment);
        }

        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }

    private void initDalog() {  //弹出提示登录的对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("你还没有登录哦,请先登录");
        builder.setTitle("提示");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }

    @Override
    protected void onNewIntent(Intent intent) {  //登录成功后返回主页面展示第一个fragment
        super.onNewIntent(intent);
        int num = intent.getIntExtra("num", 5);
        if (num == HOMEPAGE) {
            setFragment(HOMEPAGE);
            bottomBar.setView(HOMEPAGE);
        }
    }

    @Override
    protected void onDestroy() {  //内存优化，防止内存泄漏
        super.onDestroy();
        bottomBar.unBottomBarSelectListener();
        UserManager.getInstance().removeActivity(this);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {   //双击返回键退出
            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 2000) {
                showMsg("再摁一次退出应用。");
                firstTime = secondTime;
            } else {
                CacheErrorManager.getInstance().killProcess(false);
            }
        }
        return false;
    }
}
