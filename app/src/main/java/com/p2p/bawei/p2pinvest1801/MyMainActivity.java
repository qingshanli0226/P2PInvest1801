package com.p2p.bawei.p2pinvest1801;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentAdapter;
import com.p2p.bawei.p2pinvest1801.fragment.Myfragment;

import java.util.ArrayList;
import java.util.List;

public class MyMainActivity extends BaseActivity {
    private ViewPager vp;
    private TabLayout tl;
    private List<String> list_title=new ArrayList<>();
    private List<Fragment> list_fragment=new ArrayList<>();
    @Override
    public int BondLayout() {
        return R.layout.myactivity;
    }

    @Override
    public void initview() {
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
        list_title.add("首页");
        list_title.add("我的");
        list_title.add("会员");
        list_title.add("首页");
        list_fragment.add(new Myfragment());
        list_fragment.add(new Myfragment());
        list_fragment.add(new Myfragment());
        list_fragment.add(new Myfragment());
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), list_title, list_fragment);
        vp.setAdapter(myFragmentAdapter);
        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setIcon(R.drawable.product_part1_icon);
        tl.getTabAt(1).setIcon(R.drawable.product_part1_icon);
        tl.getTabAt(2).setIcon(R.drawable.product_part1_icon);
        tl.getTabAt(3).setIcon(R.drawable.product_part1_icon);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void initInJect() {

    }
}
