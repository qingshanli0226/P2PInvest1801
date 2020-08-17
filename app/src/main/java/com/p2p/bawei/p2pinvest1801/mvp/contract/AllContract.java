package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.bw.framwork.model.IModel;
import com.bw.framwork.view.IView;
import com.bw.net.bean.AllLCBean;
import com.bw.net.callback.MyCallBack;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface AllContract {

    interface View extends IView{
        void setData(AllLCBean allLCBean);
    }

    interface Model extends IModel{
        void getData(MyCallBack<AllLCBean> callBack,  Consumer<Disposable> consumer,Action action);
    }
}
