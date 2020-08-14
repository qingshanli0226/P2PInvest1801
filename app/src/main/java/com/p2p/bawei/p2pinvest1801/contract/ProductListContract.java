package com.p2p.bawei.p2pinvest1801.contract;

import com.example.framework.base.BasePresenter;
import com.example.framework.base.IView;
import com.example.net.mode.InvestListBean;

public class ProductListContract {

    public interface ProductListView extends IView{
        void onListData(InvestListBean investListBean);
    }

    public static abstract class ProductListPresenter extends BasePresenter<ProductListView>{
        public abstract void onGetListData();
    }
}
