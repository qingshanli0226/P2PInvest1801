package com.p2p.bawei.p2pinvest1801.mode;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CommonCustomTabEntity implements CustomTabEntity {

    private TextView textView;
    private int selectIcon;
    private int unSelectIcon;

    public CommonCustomTabEntity(TextView textView, int selectIcon, int unSelectIcon) {
        this.textView = textView;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return textView.getText().toString().trim();
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectIcon;
    }

}
