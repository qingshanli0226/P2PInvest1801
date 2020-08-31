package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.view.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyDialog;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.CommonAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment.InvestFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment.MoreFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment.PropertyFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.user.LoginActivity;

import java.util.ArrayList;

//主页面
public class MainActivity extends BaseActivity {
    private FrameLayout mainFramlayout;
    private CommonTabLayout mainCommon;
    public static final int Home_INDEX = 0;
    public static final int InvestFragment_INDEX = 1;
    public static final int PropertyFragment_INDEX = 2;
    public static final int MoreFragment_INDEX = 3;
    private Fragment currentFragment;
    private Fragment[] fragments = new Fragment[] {new HomeFragment(), new InvestFragment(), new PropertyFragment(),new MoreFragment()};
    ArrayList<CustomTabEntity> list = new ArrayList<>();
    @Override
    public void initViews() {

        mainFramlayout = (FrameLayout) findViewById(R.id.main_framlayout);
        mainCommon = (CommonTabLayout) findViewById(R.id.main_common);

    }
    @Override
    public void initDatas() {
        showFragment(Home_INDEX);
        //底部导航栏
        list.add(new CommonAdapter("首页", R.drawable.bottom02, R.drawable.bottom01));
        list.add(new CommonAdapter("投资", R.drawable.bottom04, R.drawable.bottom03));
        list.add(new CommonAdapter("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        list.add(new CommonAdapter("更多", R.drawable.bottom08, R.drawable.bottom07));
        mainCommon.setTabData(list);
        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //点击每一项点击或者隐藏fragment
                switch (position){
                    case 0:
                        showFragment(Home_INDEX);
                        break;
                    case 1:
                        showFragment(InvestFragment_INDEX);
                        break;
                    case 2:
                        showFragment(PropertyFragment_INDEX);
                        TextView titleView = mainCommon.getTitleView(position);
                        titleView.setTextColor(Color.RED);
                        if(UserManagers.getInstance().isUserLogin()){

                        }else{
                            if(!MainActivity.this.isFinishing()){
                                startDiaslog();
                            }
                        }
                        break;
                    case 3:
                        showFragment(MoreFragment_INDEX);
                        break;
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    //点击资产弹框
    private void startDiaslog() {
        final MyDialog myDialog = new MyDialog(this);
        myDialog.setDasilogEnsureListeren(new MyDialog.DasilogEnsureListeren() {
            @Override
            public void onEnsureOnclick() {
                launchActivity(LoginActivity.class, new Bundle());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle extras = getIntent().getExtras();
        Log.i("----111",extras.toString());
        int index1 = extras.getInt("index",-1);
        Log.i("----111",index1+"");
        showFragment(index1);
        mainCommon.setCurrentTab(0);
    }

    //显示当前点击的fragment
    public void showFragment(int selectFragment){
        Fragment fragment = fragments[selectFragment];
        if(currentFragment == fragment){
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if(currentFragment != null){
            transaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment).commit();
        } else {
            transaction.add( R.id.main_framlayout, fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
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
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
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
