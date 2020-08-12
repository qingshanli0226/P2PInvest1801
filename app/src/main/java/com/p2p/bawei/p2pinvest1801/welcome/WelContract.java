package com.p2p.bawei.p2pinvest1801.welcome;

import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.mvp.model.IModel;
import com.p2p.bawei.p2pinvest1801.mvp.view.IView;

public interface WelContract {
    interface View extends IView {
        void upAppCode(String str);
    }

    interface Model extends IModel {
        void DownLoadApp(BaseObserver<String> baseObserver);
    }
}
