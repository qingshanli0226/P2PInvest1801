package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.example.net.BaseObserver;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface RegisterContract {
    interface RegisterView extends IView{
        void registerView(String result);
        String username();
        String pwd();
    }
    interface RegisterModel extends IModel{
        void requestregiste(String username, String pwd, BaseObserver<String> observer, Consumer<Disposable> consumer);
    }
}
