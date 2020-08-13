package com.p2p.bawei.p2pinvest1801.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentAdapter;
import com.p2p.bawei.p2pinvest1801.view.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EasyNavigationBar easyBar;

    private List<Fragment> list_fragment = new ArrayList<>();
    private String[] list_title = {"首页","投资","我的投资","更多"};
    private int[] list_unselect = {R.drawable.bottom01,R.drawable.bottom03,R.drawable.bottom05,R.drawable.bottom07};
    private int[] list_select = {R.drawable.bottom02,R.drawable.bottom04,R.drawable.bottom06,R.drawable.bottom08};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
        initView();


        Log.d("LQS", "1801学习git");
        Log.d("LQS", "1801wby.....");
    }

    private void initTab() {
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());


    }

    private void initView() {
        easyBar = (EasyNavigationBar) findViewById(R.id.easyBar);
        easyBar.titleItems(list_title)
                .fragmentList(list_fragment)
                .fragmentManager(getSupportFragmentManager())
                .normalIconItems(list_unselect)
                .selectIconItems(list_select)
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .canScroll(true)
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() {
            @Override
            public void onTabLoadCompleteEvent() {
                easyBar.setMsgPointCount(1,66);
                easyBar.setHintPoint(3,true);
            }
        }).build();



        easyBar.setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
            @Override
            public boolean onTabSelectEvent(View view, int position) {

                if(position==1){
                    easyBar.setMsgPointCount(1,0);
                }else if(position==3){
                    easyBar.setHintPoint(3,false);
                }
                return false;
            }

            @Override
            public boolean onTabReSelectEvent(View view, int position) {
                return false;
            }
        });

    }


}
