package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.view.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.view.fragment.MoneyFragment;
import com.p2p.bawei.p2pinvest1801.view.fragment.MoreFragment;
import com.p2p.bawei.p2pinvest1801.view.fragment.MyMoneyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EasyNavigationBar easyBar;
    private LinearLayout linear;

    private List<Fragment> list_fragment = new ArrayList<>();
    private String[] list_title = {"首页","投资","我的资产","更多"};
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
        list_fragment.add(new MoneyFragment());
        list_fragment.add(new MyMoneyFragment());
        list_fragment.add(new MoreFragment());

    }

    private void initView() {
        linear = (LinearLayout) findViewById(R.id.linear);
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
                }else if(position==2){
                    final PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setHeight(700);
                    popupWindow.setWidth(1100);
                    popupWindow.setOutsideTouchable(true);
                    View inflate = getLayoutInflater().inflate(R.layout.layout_my_more, null);
                    popupWindow.setContentView(inflate);

                    Button bt_sure = inflate.findViewById(R.id.bt_sure);
                    popupWindow.showAtLocation(linear,Gravity.CENTER,0,0);



                    bt_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            popupWindow.dismiss();
                        }
                    });
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
