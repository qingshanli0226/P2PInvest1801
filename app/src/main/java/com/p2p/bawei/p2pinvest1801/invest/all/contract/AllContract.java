package com.p2p.bawei.p2pinvest1801.invest.all.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.ProductBean;

public class AllContract {

    public interface IAllView extends IView {
        void onAllData(ProductBean productBean);
    }

    public static abstract class AllPresenter extends BasePresenter<IAllView> {
        public abstract void getAllData();
    }


}
