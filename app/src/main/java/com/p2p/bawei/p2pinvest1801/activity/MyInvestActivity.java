package com.p2p.bawei.p2pinvest1801.activity;

import com.example.framework.base.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.ui.MyLineChartDemoView;

public class MyInvestActivity extends BaseActivity {

    private MyLineChartDemoView lineChar;

    @Override
    protected void initData() {
        //设置数据集
        lineChar.setInfo(
                new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Now", "Dec"},
                new String[]{"0", "20", "40", "60", "80", "100"},
                new String[]{
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100)
                },
                ""
        );

    }

    @Override
    protected void initView() {
        lineChar = (MyLineChartDemoView) findViewById(R.id.lineChar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_invest;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
