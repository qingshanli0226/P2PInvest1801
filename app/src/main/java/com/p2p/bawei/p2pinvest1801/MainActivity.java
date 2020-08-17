package com.p2p.bawei.p2pinvest1801;

import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.net.connecct.NetConnect;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.p2p.bawei.p2pinvest1801.bean.TabBean;
import com.p2p.bawei.p2pinvest1801.frist.view.FirstFragment;
import com.p2p.bawei.p2pinvest1801.invest.view.InvestFragment;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private FirstFragment firstFragment;
    private InvestFragment investFragment;
    private CommonTabLayout mComTab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();

    @Override
    public void onClick(View v) {
    }

    @Override
    public void initView() {
        mComTab = (CommonTabLayout) findViewById(R.id.com_tab);
        firstFragment = new FirstFragment();
        investFragment = new InvestFragment();
        list.add(new TabBean("首页", R.drawable.bottom02, R.drawable.bottom01));
        list.add(new TabBean("投资", R.drawable.bottom04, R.drawable.bottom03));
        list.add(new TabBean("我的资产", R.drawable.bottom06, R.drawable.bottom05));
        list.add(new TabBean("更多", R.drawable.bottom08, R.drawable.bottom07));
        mComTab.setTabData(list);
        initFragment();
        mComTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        changeFragment(firstFragment);
                        break;
                    case 1:
                        changeFragment(investFragment);
                        break;
                    case 2:
                        changeFragment(firstFragment);
                        break;
                    case 3:
                        changeFragment(firstFragment);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.com_box, firstFragment)
                .add(R.id.com_box, investFragment)
                .commit();
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(firstFragment)
                .hide(investFragment)
                .show(fragment)
                .commit();
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
 