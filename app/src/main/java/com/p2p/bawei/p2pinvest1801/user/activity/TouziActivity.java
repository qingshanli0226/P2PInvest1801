package com.p2p.bawei.p2pinvest1801.user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.lixs.charts.LineChartView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 * 投资管理
 */
public class TouziActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private LineChartView linechar;


    @Override
    protected void initData() {

        List<Double> data = new ArrayList<>();
        List<String> name = new ArrayList<>();

        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));
        data.add((double) Math.round(Math.random() * 100));


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

        linechar.setShowNum(5);
        linechar.setDatas(data,name);

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        linechar = findViewById(R.id.linechar);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("折线图demo");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_touzi;
    }
}
