package com.bw.common;

import com.bw.net.bean.HomeBean;

public class HomeDataManager  {

    private HomeBean homeBean;
    private HomeDataManager() {

    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public boolean isHave(){

        return homeBean!=null;
    }

    private static HomeDataManager homeDataManager;
    public static HomeDataManager getInstance(){
        if (homeDataManager==null){
            synchronized (String.class){
                if (homeDataManager==null){
                    homeDataManager=new HomeDataManager();
                }
            }
        }
        return homeDataManager;
    }


}
