package com.p2p.bawei.p2pinvest1801.more.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.view.MainActivity;

public class GuiGuInvestActivity extends BaseActivity {

    private TextView tvTitle;
    private ImageView ivTitleBack;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tv_title);
        ivTitleBack = findViewById(R.id.iv_title_back);

        tvTitle.setText("关于硅谷理财");
        ivTitleBack.setVisibility(View.VISIBLE);

        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                launchActivity(MainActivity.class, null);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_gui_gu_invest;
    }

}
