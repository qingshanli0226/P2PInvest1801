package com.p2p.bawei.p2pinvest1801.user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.view.HistogramDemoView;
import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 投资管理（直观）
 * 柱状图demo
 */
public class InvestmentActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private HistogramDemoView histogram;


    @Override
    protected void initData() {
        List<Long> data = new ArrayList<>();
        List<String> name = new ArrayList<>();

        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));
        data.add((long) (Math.random() * 100));

        name.add("Jan");
        name.add("Feb");
        name.add("Mar");
        name.add("Apr");
        name.add("May");
        name.add("Jun");
        name.add("Jul");
        name.add("Aug");
        name.add("Sept");
        name.add("Oct");
        name.add("Now");
        name.add("Dec");

        histogram.updateThisData(data, name);
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);

        histogram = findViewById(R.id.histogram);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("柱状图demo");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_investment;
    }
}
