package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.common.mvp.view.BaseActivity;
import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyContract;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.MyInvest;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.Myfragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyMainActivity extends BaseActivity  {
    private EasyNavigationBar es;

    private List<String> list_title=new ArrayList<>();
    private List<Fragment> list_fragment=new ArrayList<>();
    @Override
    public int BondLayout() {
        return R.layout.myactivity;
    }
    private String[] list_ttt={"首页","投资","我的资产","更多"};
    private int[] list_pic={R.drawable.bottom01,R.drawable.bottom03,R.drawable.bottom05,R.drawable.bottom07};
    private int[] list_pic_t={R.drawable.bottom02,R.drawable.bottom04,R.drawable.bottom06,R.drawable.bottom08};
    @Override
    public void initview() {

        es = (EasyNavigationBar) findViewById(R.id.es);

        list_fragment.add(new Myfragment());
        list_fragment.add(new MyInvest());
        list_fragment.add(new Myfragment());
        list_fragment.add(new Myfragment());

        es.titleItems(list_ttt).normalIconItems(list_pic).selectIconItems(list_pic_t)
                .fragmentList(list_fragment)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .normalTextColor(Color.parseColor("#535151"))
                .selectTextColor(Color.parseColor("#1686F3"))
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() {
                    @Override
                    public void onTabLoadCompleteEvent() {
                        es.setMsgPointCount(0,66);
                        es.setMsgPointCount(1,109);
                        es.setHintPoint(3,true);
                    }
                }).build();
        es.setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
            @Override
            public boolean onTabSelectEvent(View view, int position) {
                nmumberafter(position);
                return false;
            }

            private void nmumberafter(int position) {
                es.setMsgPointCount(position,0);
                es.setHintPoint(position,false);
            }

            @Override
            public boolean onTabReSelectEvent(View view, int position) {
                return false;
            }
        });



    }

    @Override
    public void initdata() {


    }

    @Override
    public void initInJect() {

    }

}
