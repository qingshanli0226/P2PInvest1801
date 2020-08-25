package com.p2p.bawei.p2pinvest1801.user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * 投资管理（直观）
 * 柱状图demo
 *
 */
public class InvestmentActivity extends BaseActivity {
    private ImageView ivTitleBack;
    private TextView tvTitle;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);

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
