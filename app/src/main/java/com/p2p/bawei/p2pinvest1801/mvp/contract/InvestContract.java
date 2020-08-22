package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.example.net.BaseObserver;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface InvestContract {
    interface InvestModel extends IModel{
        void requestInvest(BaseObserver<InvestBean> observer, Consumer<Disposable> consumer, Action action);
    }
    interface InvestView extends IView {
        void invest(InvestBean investbean);

    }
}
