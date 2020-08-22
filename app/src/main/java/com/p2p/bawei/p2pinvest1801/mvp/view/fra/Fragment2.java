package com.p2p.bawei.p2pinvest1801.mvp.view.fra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.mylibrary.mvp.view.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyChildAdapter;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.MainContact;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MainPre;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra.ChlidFragment1;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra.ChlidFragment2;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra.ChlidFragment3;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends BaseFragment implements MainContact.View {
    View view;
    private MyChildAdapter myChildAdapter;
    private TabLayout f2Tab;
    private ViewPager f2Pager;
//    private MarqueeView marqueeView;
    private List<String> stringList=new ArrayList<>();
    public static List<Fragment> fList=new ArrayList<>();



    @Override
    public int banlayout() {
        return R.layout.f2;
    }

    @Override
    public void initView() {
        f2Tab = findViewById(R.id.f2_tab);
        f2Pager = findViewById(R.id.f2_pager);
//        marqueeView = findViewById(R.id.marqueeView);
        stringList.add("全部理财");
        stringList.add("推荐理财");
        stringList.add("热门理财");

        fList.add(new ChlidFragment1());
        fList.add(new ChlidFragment2());
        fList.add(new ChlidFragment3());
    }

    @Override
    public void initData() {
        inittab(stringList);
        initpager();
        initScoll();
    }




    private void initpager() {
        myChildAdapter=new MyChildAdapter(getChildFragmentManager());
        f2Pager.setAdapter(myChildAdapter);
    }

    private void inittab(List<String> sList) {
        for (String s:sList){
            f2Tab.addTab(f2Tab.newTab().setText(s));
        }
    }
    private void initScoll() {
        f2Tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                f2Pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        f2Pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                f2Tab.setScrollPosition(position,positionOffset,false);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initBanner(BannerBean bannerBean) {

    }


}
