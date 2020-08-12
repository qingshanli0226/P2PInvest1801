package com.p2p.bawei.p2pinvest1801.main.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CommonEntity implements CustomTabEntity {
    private String titles;
    private int selectIcon;
    private int unSelectIcon;

    public CommonEntity(String titles, Integer selectIcon, Integer unSelectIcon) {
        this.titles = titles;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return titles;
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
