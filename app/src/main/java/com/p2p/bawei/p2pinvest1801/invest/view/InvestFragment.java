package com.p2p.bawei.p2pinvest1801.invest.view;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment {
    private TextView mAllInvest;
    private TextView mTuiInvest;
    private TextView mHotInvest;
    private ViewPager mListVp;


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.all_invest:
                upTab(mAllInvest);
                mListVp.setCurrentItem(0);
                break;
            case R.id.tui_invest:
                upTab(mTuiInvest);
                mListVp.setCurrentItem(1);
                break;
            case R.id.hot_invest:
                upTab(mHotInvest);
                mListVp.setCurrentItem(2);
                break;
        }
    }

    private void upTab(TextView txt) {
        int color = getResources().getColor(R.color.tabSelect);
        mTuiInvest.setBackgroundColor(Color.WHITE);
        mHotInvest.setBackgroundColor(Color.WHITE);
        mAllInvest.setBackgroundColor(Color.WHITE);
        mTuiInvest.setTextColor(Color.BLACK);
        mHotInvest.setTextColor(Color.BLACK);
        mAllInvest.setTextColor(Color.BLACK);
        txt.setBackgroundColor(color);
        txt.setTextColor(Color.RED);
    }


    @Override
    public void initView() {
        mListVp = (ViewPager) findViewById(R.id.list_vp);
        mAllInvest = (TextView) findViewById(R.id.all_invest);
        mTuiInvest = (TextView) findViewById(R.id.tui_invest);
        mHotInvest = (TextView) findViewById(R.id.hot_invest);
        mAllInvest.setOnClickListener(this);
        mTuiInvest.setOnClickListener(this);
        mHotInvest.setOnClickListener(this);

        final List<Fragment> list = new ArrayList<>();
        list.add(new InvestFirstFragment());
        list.add(new InvestFirstFragment());
        list.add(new InvestHotFragment());
        mListVp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        mListVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        upTab(mAllInvest);
                        break;
                    case 1:
                        upTab(mTuiInvest);
                        break;
                    case 2:
                        upTab(mHotInvest);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.invest_layout;
    }
}
