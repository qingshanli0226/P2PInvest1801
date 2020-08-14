package com.p2p.bawei.p2pinvest1801;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseActivity;
import com.example.net.mode.BannerBean;
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

    private long firstTime = 0;

    //banner数据源
    private ArrayList<String> stringArrayList;
    private BannerBean bannerBean;

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
        bannerBean = bundleExtra.getParcelable("hj");
//        printLog("123123");
//        printLog(bannerBean.getResult().getImageArr().get(0).getIMAURL());

        //创建Fragment实例对象
        createFragment();
        initFragment();

    }

    //显示Fragment的方法
    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(investFragment)
                .hide(meFragment)
                .hide(moreFragment)
                .show(fragment)
                .commit();
    }

    public void initFragment(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,homeFragment);
        fragmentTransaction.add(R.id.frame,investFragment);
        fragmentTransaction.add(R.id.frame,meFragment);
        fragmentTransaction.add(R.id.frame,moreFragment);
        fragmentTransaction.hide(investFragment);
        fragmentTransaction.hide(meFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();
    }

    private void createFragment() {
        if(homeFragment == null){
            homeFragment = new HomeFragment(stringArrayList);
            Bundle bundle = new Bundle();
            bundle.putParcelable("hj1",bannerBean);
            homeFragment.setArguments(bundle);
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
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
