package com.bw.common;

import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;

public class HomeDataManager {

    private HomeBean homeBean = null;
    private UpdataBean updataBean = null;

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public UpdataBean getUpdataBean() {
        return updataBean;
    }

    private HomeDataManager() {

    }

    public void saveUpdataBean(UpdataBean updataBean) {
        this.updataBean = updataBean;
    }

    public void saveHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public boolean isHave() {

        return homeBean != null;
    }

    public boolean isHaveUpdate() {

        return updataBean != null;
    }

    private static HomeDataManager homeDataManager = null;

    public static HomeDataManager getInstance() {
        if (homeDataManager == null) {
            synchronized (String.class) {
                if (homeDataManager == null) {
                    homeDataManager = new HomeDataManager();
                }
            }
        }
        return homeDataManager;
    }


}
