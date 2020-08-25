package com.p2p.bawei.p2pinvest1801.user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.view.LinechartDemoView;
import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * 折线图
 * 投资管理
 */
public class TouziActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private LinechartDemoView linechar;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        linechar = findViewById(R.id.linechar);

        //设置数据集
        linechar.setInfo(
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
