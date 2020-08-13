package com.p2p.bawei.p2pinvest1801.invest.hot.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.ProductBean;

public class ProductContract {

    public interface IProductView extends IView {
        void onProductData(ProductBean productBean);
    }

    public static abstract class ProductPresenter extends BasePresenter<IProductView> {
        public abstract void getProductData();
    }


}
