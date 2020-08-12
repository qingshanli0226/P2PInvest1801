package com.p2p.bawei.p2pinvest1801;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.FileUtils;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.net.mvp.view.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.entity.CustomEnitty;
import com.p2p.bawei.p2pinvest1801.fragment.Fragment1;
import com.p2p.bawei.p2pinvest1801.fragment.Fragment2;
import com.p2p.bawei.p2pinvest1801.fragment.Fragment3;
import com.p2p.bawei.p2pinvest1801.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private com.next.easynavigation.view.EasyNavigationBar en;
    private String[] list_title={"首页","投资","我的资产","更多"};
    private int [] select={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};
    private int [] unselect={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        en = (EasyNavigationBar) findViewById(R.id.en);


//        网络
            connnetivity();

//        底部导航
            tablayout();


    }


    @Override
    public void inJect() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void LoadLayout() {

    }

    @Override
    public void hideLayout() {

    }

    private void tablayout() {
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());

        en.titleItems(list_title).normalIconItems(unselect).selectIconItems(select).canScroll(true)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager()).mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() {
                    @Override
                    public void onTabLoadCompleteEvent() {
                        en.setMsgPointCount(2,100);
                        en.setHintPoint(1,true);
                    }
                }).build();
        en.setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
            @Override
            public boolean onTabSelectEvent(View view, int position) {
                if(position==1){
                    en.setHintPoint(1,false);
                }else if(position==2){
                    en.setMsgPointCount(2,0);
                }


                return false;
            }

            @Override
            public boolean onTabReSelectEvent(View view, int position) {


                return false;
            }
        });

    }

    private void connnetivity() {
        ConnectivityManager systemService = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean connected = networkInfo.isConnected();
        if (connected) {
            Toast.makeText(this, "wife", Toast.LENGTH_SHORT).show();
        }
        NetworkInfo networkInfo1 = systemService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean connected1 = networkInfo1.isConnected();
        if (connected1) {
            Toast.makeText(this, "手机网络", Toast.LENGTH_SHORT).show();

        }


    }
}
