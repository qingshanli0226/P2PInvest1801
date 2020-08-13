package com.p2p.bawei.p2pinvest1801.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabBean implements CustomTabEntity {
    private String title;
    private int select;
    private int unSelect;

    public TabBean(String title, int select, int unSelect) {
        this.title = title;
        this.select = select;
        this.unSelect = unSelect;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return select;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelect;
    }
}
