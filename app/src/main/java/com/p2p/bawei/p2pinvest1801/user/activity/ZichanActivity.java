package com.p2p.bawei.p2pinvest1801.user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.view.PiechartDemoView;
import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * 扇形图demo
 *
 */
public class ZichanActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private PiechartDemoView piecharview;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        piecharview = findViewById(R.id.piecharview);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("饼状图demo");

        double math = (double) (Math.random() * 50);
        printLog("math--->" + math);

        piecharview.setDatas(new double[]{math, 50 - math, math, 50 - math});
        piecharview.setTexts(new String[]{"三星", "华为", "oppo", "vivo"});
        piecharview.setMaxNum(4);
        piecharview.setRadius(800);
        piecharview.setTextSize(100);
        piecharview.invalidate();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zichan;
    }
}
