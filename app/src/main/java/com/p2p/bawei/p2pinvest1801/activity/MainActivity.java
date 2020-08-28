package com.p2p.bawei.p2pinvest1801.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bw.common.view.BottomBar;
import com.bw.framwork.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.p2p.bawei.p2pinvest1801.fragment.HomePageFragment;
import com.p2p.bawei.p2pinvest1801.fragment.InvestmentFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MindFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MoreFragment;

public class MainActivity extends BaseActivity {    //王俊豪练习项目

    private FragmentTransaction transaction;
    private BottomBar bottomBar;
    private HomePageFragment homePageFragment;
    private InvestmentFragment investmentFragment;
    private MindFragment mindFragment;
    private MoreFragment moreFragment;


    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        setFragment(0);  //默认显示首页

        bottomBar = findViewById(R.id.myBottomBar);
        bottomBar.setBottomBarSelectListener(new BottomBar.IBottomBarSelectListener() {
            @Override
            public void onBottomBarSelected(int selectIndex) {
                setFragment(selectIndex);
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
            case 0:
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.main_vp, homePageFragment);
                }


                transaction.show(homePageFragment);


                break;
            case 1:
                if (investmentFragment == null) {
                    investmentFragment = new InvestmentFragment();
                    transaction.add(R.id.main_vp, investmentFragment);
                }

                transaction.show(investmentFragment);


                break;
            case 2:
                if (mindFragment == null) {
                    mindFragment = new MindFragment();
                    transaction.add(R.id.main_vp, mindFragment);
                }

                transaction.show(mindFragment);
                if (!UserManager.getInstance().isLogin()) {  //判断是否已经登录了。
                    initDalog();
                }
                break;
            case 3:
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
        if (num==0){
            setFragment(0);
            bottomBar.setView(0);
        }
    }
}
