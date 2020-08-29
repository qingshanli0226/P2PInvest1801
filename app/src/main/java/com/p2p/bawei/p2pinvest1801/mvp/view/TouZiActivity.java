package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.common.ToolBar;
import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.LinechartDemoView;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyScollows;
//折线图界面
public class TouZiActivity extends BaseActivity implements ToolBar.ClicksListener {
    private MyScollows touziScollow;
    private ToolBar toolbar;
    @Override
    public void initViews() {
        touziScollow = (MyScollows) findViewById(R.id.touzi_scollow);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setClicksListener(this);
    }
    @Override
    public void initDatas() {

    }

    @Override
    public int bandLayout() {
        return R.layout.touzi;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void showMsg(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(this, ""+code+"--"+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void leftclick() {
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void rightclick() {

    }
}
