package com.p2p.bawei.p2pinvest1801.mine.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;

import io.reactivex.Observer;

public interface UploadContract {

    interface IUploadContractView extends IView {
        void onPic(StringBean stringBean);
    }

    interface IUploadContractMolde extends IModel {

        void getPic(Observer<StringBean> observer,String path);

    }


}
