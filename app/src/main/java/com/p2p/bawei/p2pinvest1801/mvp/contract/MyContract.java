package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.EdtivitnEnitiy;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;

import java.util.List;

import io.reactivex.Observer;

public interface MyContract {
interface mModle extends IModel{
    void getbanner(Observer<MyBannerEntity> observer);
    void getedtivity(Observer<EdtivitnEnitiy> observer);
}
interface mView extends IView{
    void getbanner(MyBannerEntity myBannerEntity);
    void getedtivity(EdtivitnEnitiy edtivitnEnitiy);
}
}
