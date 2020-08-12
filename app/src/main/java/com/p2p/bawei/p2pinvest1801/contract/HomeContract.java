package com.p2p.bawei.p2pinvest1801.contract;

import com.example.framework.base.BasePresenter;
import com.example.framework.base.IView;
import com.example.net.mode.BannerBean;
import com.example.net.mode.VersionBean;

public class HomeContract {

    public interface HomeView extends IView{
        void onGetBannerData(BannerBean bannerBean);
        void onGetVersionData(VersionBean versionBean);
    }

    public static abstract class HomePresenter extends BasePresenter<HomeView>{
        public abstract void onBannerData();
        public abstract void onVersionData();
    }
}
