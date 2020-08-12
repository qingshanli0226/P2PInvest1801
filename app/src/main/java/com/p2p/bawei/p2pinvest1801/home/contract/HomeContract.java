package com.p2p.bawei.p2pinvest1801.home.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.HomeBean;

public class HomeContract {

    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }

    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }


}
