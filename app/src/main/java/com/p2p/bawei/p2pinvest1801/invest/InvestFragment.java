package com.p2p.bawei.p2pinvest1801.invest;


import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.all.view.IAllFragment;
import com.p2p.bawei.p2pinvest1801.invest.all.view.MyViewPager;
import com.p2p.bawei.p2pinvest1801.invest.commend.view.ICommendFragment;
import com.p2p.bawei.p2pinvest1801.invest.hot.view.IHotFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends BaseFragment {
    private final List<String> titleList = new ArrayList<>();
    private final List<Fragment> fragmentList = new ArrayList<>();
    private boolean isToast = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        TabLayout tablayout = findViewById(R.id.tablayout);
        MyViewPager viewpager = findViewById(R.id.viewpager);

        fragmentList.add(new IAllFragment());
        fragmentList.add(new ICommendFragment());
        fragmentList.add(new IHotFragment());

        titleList.add("全部理财");
        titleList.add("推荐理财");
        titleList.add("热门理财");

        InvestAdapter investAdapter = new InvestAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
        viewpager.setAdapter(investAdapter);
        tablayout.setupWithViewPager(viewpager);
        tvTitle.setText("投资");

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public void onPageSelected(int position) {
                if (position == 1 && isToast) {
                    //自定义Toast提升用户体验
                    Toast toast = new Toast(getContext());
                    //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
                    toast.setGravity(Gravity.CENTER, 0, 70);
                    //设置显示时间
                    toast.setDuration(5000);
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.my_toast, null, false);
                    toast.setView(view);
                    toast.show();
                    isToast = false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isToast = true;
    }
}
