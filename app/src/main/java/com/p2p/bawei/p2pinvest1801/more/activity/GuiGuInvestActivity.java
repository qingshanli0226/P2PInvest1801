package com.p2p.bawei.p2pinvest1801.more.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class GuiGuInvestActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        ImageView ivTitleBack = findViewById(R.id.iv_title_back);

        tvTitle.setText("关于硅谷理财");
        ivTitleBack.setVisibility(View.VISIBLE);

        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCurrentActivity();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_gui_gu_invest;
    }


}
