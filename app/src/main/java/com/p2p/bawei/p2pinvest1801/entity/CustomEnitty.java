package com.p2p.bawei.p2pinvest1801.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CustomEnitty implements CustomTabEntity {
    String title;
    int select;
    int unselect;

    public CustomEnitty(String title, int select, int unselect) {
        this.title = title;
        this.select = select;
        this.unselect = unselect;
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
        return unselect;
    }
}
