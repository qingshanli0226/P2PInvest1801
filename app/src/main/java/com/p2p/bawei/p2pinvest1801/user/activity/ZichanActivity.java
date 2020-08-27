package com.p2p.bawei.p2pinvest1801.user.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.lixs.charts.PieChartView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 扇形图demo
 */
public class ZichanActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private PieChartView pieView;

    private List<Float> datas = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();
    private List<String> description = new ArrayList<>();


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        pieView = findViewById(R.id.pieView);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("饼状图demo");

        float random = (float) ((float) Math.random()*0.5);
        float random2 = (float) ((float) Math.random()*0.5);

        datas.add(random);
        datas.add((float) Math.abs(0.5 - random));
        datas.add(random2);
        datas.add((float) Math.abs(0.5 - random2));

        colors.add(Color.parseColor("#FFC65B"));
        colors.add(Color.parseColor("#FD5998"));
        colors.add(Color.parseColor("#8971FB"));
        colors.add(Color.parseColor("#676974"));

        description.add("三星");
        description.add("华为");
        description.add("oppo");
        description.add("vivo");

        pieView.setCanClickAnimation(true);
        pieView.setDatas(datas, colors, description);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zichan;
    }
}
