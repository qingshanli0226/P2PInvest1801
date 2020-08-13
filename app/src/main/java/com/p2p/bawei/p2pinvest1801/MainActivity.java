package com.p2p.bawei.p2pinvest1801;

import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.net.connecct.NetConnect;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.bean.TabBean;
import com.p2p.bawei.p2pinvest1801.frist.view.FirstFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;
import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private ViewPager mComFragmentVp;
    private CommonTabLayout mComTab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {


        mComFragmentVp = (ViewPager) findViewById(R.id.com_fragment_vp);
        mComTab = (CommonTabLayout) findViewById(R.id.com_tab);
        list.add(new TabBean("首页", R.drawable.b1, R.drawable.a1));
        list.add(new TabBean("投资", R.drawable.b4, R.drawable.a4));
        list.add(new TabBean("我的资产", R.drawable.b2, R.drawable.a2));
        list.add(new TabBean("更多", R.drawable.b3, R.drawable.a3));
        fragments.add(new FirstFragment());
        fragments.add(new FirstFragment());
        fragments.add(new FirstFragment());
        fragments.add(new FirstFragment());
        mComTab.setTabData(list);
        mComFragmentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mComTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mComFragmentVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void initData() {
        boolean networkConnected = NetConnect.isNetworkConnected(this);
        if (!networkConnected) {
            Toast.makeText(this, "当前网络未连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已连接网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }
}
 