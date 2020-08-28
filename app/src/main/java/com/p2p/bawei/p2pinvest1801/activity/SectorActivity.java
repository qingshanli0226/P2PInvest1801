package com.p2p.bawei.p2pinvest1801.activity;

import android.graphics.Color;

import com.example.framework.base.BaseActivity;
import com.lixs.charts.PieChartView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

public class SectorActivity extends BaseActivity {

    private PieChartView pieCharView;
    //数据源
    //所占比例的大小
    private ArrayList<Float> mRatios = new ArrayList<>();
    //具体的描述
    private ArrayList<String> mDescription = new ArrayList<>();
    //所对应的颜色
    private ArrayList<Integer> mArcColors = new ArrayList<>();


    @Override
    protected void initData() {
        //添加数据
        mRatios.add(0.326f);
        mRatios.add(0.270f);
        mRatios.add(0.145f);
        mRatios.add(0.260f);

        mDescription.add("三星");
        mDescription.add("华为");
        mDescription.add("oppo");
        mDescription.add("vivo");

        mArcColors.add(Color.parseColor("#C0FF8C"));
        mArcColors.add(Color.parseColor("#FFF78C"));
        mArcColors.add(Color.parseColor("#FFD08C"));
        mArcColors.add(Color.parseColor("#8CEAFF"));

        //点击开启动画
        pieCharView.setCanClickAnimation(true);
        pieCharView.setDatas(mRatios,mArcColors,mDescription);

    }

    @Override
    protected void initView() {
        pieCharView = (PieChartView) findViewById(R.id.pieCharView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sector;
    }
}
