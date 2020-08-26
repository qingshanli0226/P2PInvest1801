package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.LinechartDemoView;

public class TouZiActivity extends BaseActivity {
    private LinechartDemoView touzi;
    @Override
    public void initViews() {
        touzi = (LinechartDemoView) findViewById(R.id.touzi);
    }
    @Override
    public void initDatas() {
        touzi.setInfo(
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

    }

    @Override
    public void showError(String code, String message) {

    }
}
