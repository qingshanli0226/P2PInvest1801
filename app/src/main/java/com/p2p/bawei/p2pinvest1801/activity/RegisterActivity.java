package com.p2p.bawei.p2pinvest1801.activity;

import com.bw.common.view.ToolBar;
import com.bw.framwork.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;

public class RegisterActivity extends BaseActivity implements ToolBar.OnToolBarClick {
    private ToolBar toolBar;

    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        toolBar=findViewById(R.id.register_toolbar);
        toolBar.setOnToolBarClick(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeActivity(this);
    }
}
