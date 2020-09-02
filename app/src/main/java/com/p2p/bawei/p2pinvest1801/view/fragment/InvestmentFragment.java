package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 投资
 */
public class InvestmentFragment extends BaseFragment<MyAllPresenter> implements MyAllContract.View {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentAdapter myFragmentAdapter;
    private List<String> list_string = new ArrayList<>();
    private List<Fragment> list_fragment = new ArrayList<>();


    @Override
    public int bandLayout() {
        return R.layout.fragment_money;
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

        initTab();

    }

    private void initTab() {
        list_fragment.add(new AllManagementFragment());
        list_fragment.add(new CacheFragment());
        list_fragment.add(new RecommendFragment());
        list_fragment.add(new PopularFragment());
        list_string.add("全部理财");
        list_string.add("三级缓存");
        list_string.add("推荐理财");
        list_string.add("热门理财");

        myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(),list_fragment,list_string);
        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        tabLayout.setSelectedTabIndicatorHeight(300);
        viewPager.setCurrentItem(0);
    }


    @Override
    public void initAdapter(MyAllBean myAllBean) {

    }
}
