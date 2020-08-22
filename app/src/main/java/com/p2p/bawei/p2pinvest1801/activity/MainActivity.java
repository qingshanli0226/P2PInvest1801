package com.p2p.bawei.p2pinvest1801;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.home.mvp.view.fragment.HomeFragment;
import com.p2p.bawei.p2pinvest1801.invest.mvp.view.fragment.InvestFragment;
import com.p2p.bawei.p2pinvest1801.mine.mvp.view.fragment.MineFragment;
import com.p2p.bawei.p2pinvest1801.more.mvp.view.fragment.MoreFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EasyNavigationBar easyBar;
    private String[] titleItems = {"首页", "投资", "我的资产", "更多"};
    private int[] normalIconItems = {R.drawable.bottom01, R.drawable.bottom03, R.drawable.bottom05, R.drawable.bottom07};
    private int[] selectIconItems = {R.drawable.bottom02, R.drawable.bottom04, R.drawable.bottom06, R.drawable.bottom08};
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easyBar = findViewById(R.id.easyBar);

//        getIntent()
//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        HomeBean home = bundle.getParcelable("home");
//        Log.i("liuxuan", "MainActivity: "+home.getMsg());


//        HomeBean home1 = getIntent().getParcelableExtra("home");
//        HomeBean home = bundle.getParcelable("home");

//        Log.i("liuxuan", "onCreate:  getCode" + home.getCode());


        fragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
//        homeFragment.setArguments(bundle);

        fragmentList.add(homeFragment);
        fragmentList.add(new InvestFragment());
        fragmentList.add(new MineFragment());
        fragmentList.add(new MoreFragment());

        easyBar.titleItems(titleItems)
                .normalIconItems(normalIconItems)
                .selectIconItems(selectIconItems)
                .fragmentList(fragmentList)
                .canScroll(false)//侧滑切换
//                .centerImageRes(R.mipmap.ic_launcher_round) //最中间图片
                .fragmentManager(getSupportFragmentManager())
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        easyBar.setMsgPointCount(0,9);
                        easyBar.setMsgPointCount(1,314);
                        easyBar.setMsgPointCount(2,42);
                        easyBar.setHintPoint(3, true);
                    }
                })
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        easyBar.clearMsgPoint(position); //消息量消失
                        easyBar.clearHintPoint(position);//红点消失
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        return false;
                    }
                })
                .build();

    }
}
