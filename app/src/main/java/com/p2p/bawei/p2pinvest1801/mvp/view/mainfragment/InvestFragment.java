package com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framwork.mvp.view.BaseFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.CommonAdapter;
import com.p2p.bawei.p2pinvest1801.adapter.ViewpagerAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.view.infragment.AllPagerFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.infragment.CommendFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.infragment.HotFragment;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment {
    private CommonTabLayout investCommon;
    ArrayList<CustomTabEntity> list_common = new ArrayList<>();
    ViewpagerAdapter viewpagerAdapter;
    List<Fragment> list = new ArrayList<>();
    private ViewPager investViewpage;
    @Override
    public void initViews() {
        investCommon = (CommonTabLayout) findViewById(R.id.invest_common);
        investViewpage = (ViewPager) findViewById(R.id.invest_viewpage);
        viewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(), 1,list);
        investViewpage.setAdapter(viewpagerAdapter);
        viewpagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDatas() {
        //顶部导航
        list_common.clear();
        list_common.add(new CommonAdapter("全部理财", 0, 0));
        list_common.add(new CommonAdapter("推荐理财", 0, 0));
        list_common.add(new CommonAdapter("热门理财", 0, 0));
        investCommon.setTabData(list_common);
        list.add(new AllPagerFragment());
        list.add(new CommendFragment());
        list.add(new HotFragment());
        viewpagerAdapter.notifyDataSetChanged();
        //顶部导航和viewpager联动
        investCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                investViewpage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        investViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                investCommon.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int bandLayout() {
        return R.layout.investfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }



}
