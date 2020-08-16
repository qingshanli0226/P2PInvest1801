package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.common.mvp.view.BaseFragment;
import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyInvest extends BaseFragment {
    private ViewPager vp;
    private TabLayout tl;
    private List<String> list_title=new ArrayList<>();
    private List<Fragment> list_fragment_fragment=new ArrayList<>();
    @Override
    public int BondLayout() {
        return R.layout.myfragmentinvest;
    }

    @Override
    public void initview() {
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
    }

    @Override
    public void initdata() {
        list_title.add("全部理财");
        list_title.add("推荐理财");
        list_title.add("热门理财");
        list_fragment_fragment.add(new MyFragment_fragment());
        list_fragment_fragment.add(new Myfragment());
        list_fragment_fragment.add(new MyFragment_FlowLayout());
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getChildFragmentManager(), list_title, list_fragment_fragment);
        vp.setAdapter(myViewPageAdapter);
        tl.setupWithViewPager(vp);
        tl.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void initInJect() {

    }
}
