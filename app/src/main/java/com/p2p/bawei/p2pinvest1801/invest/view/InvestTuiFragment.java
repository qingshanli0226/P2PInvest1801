package com.p2p.bawei.p2pinvest1801.invest.view;

import android.view.View;

import com.example.baselibrary.mvp.view.BaseFragment;
import com.example.baselibrary.view.FLView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvestTuiFragment extends BaseFragment {

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        String[] data = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
                "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
                "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
        };
        FLView mFl = (FLView) findViewById(R.id.fl);
        List<String> list = new ArrayList<>(Arrays.asList(data));
        mFl.setList(list);
        mFl.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.tui;
    }
}
