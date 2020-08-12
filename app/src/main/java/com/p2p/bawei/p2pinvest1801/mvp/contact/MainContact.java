package com.p2p.bawei.p2pinvest1801.mvp.contact;

import com.example.mylibrary.mvp.model.IModel;
import com.example.mylibrary.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;
import com.youth.banner.Banner;

import java.util.List;

import io.reactivex.Observer;

public interface MainContact {
    interface Model extends IModel {
        void requestBanner( Observer<BannerBean> observer);
    }
    interface View extends IView {
        void initBanner(BannerBean bannerBean);

    }
}
