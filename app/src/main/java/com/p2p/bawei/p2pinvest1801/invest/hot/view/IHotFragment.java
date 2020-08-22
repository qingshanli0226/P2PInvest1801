package com.p2p.bawei.p2pinvest1801.invest.hot.view;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPFragment;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.hot.contract.ProductContract;
import com.p2p.bawei.p2pinvest1801.invest.hot.presenter.ProductPresenterImpl;
import com.p2p.bawei.p2pinvest1801.invest.hot.adapter.HotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IHotFragment extends BaseMVPFragment<ProductPresenterImpl, ProductContract.IProductView> implements ProductContract.IProductView {
    private MyLoadingBar loadingBar;
    private final List<ProductBean.ResultBean> resultBeanList = new ArrayList<>();

    private HotAdapter hotAdapter;

    @Override
    protected void initHttpData() {
        ihttpPresenter.getProductData();
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new ProductPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ihot;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);
        RecyclerView hotRv = findViewById(R.id.hot_rv);
        resultBeanList.clear();
        hotAdapter = new HotAdapter(R.layout.invest_hot_item, resultBeanList);

        hotRv.setAdapter(hotAdapter);
        hotRv.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));

        hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showMessage(resultBeanList.get(position).getName());
            }
        });
    }

    @Override
    public void onProductData(ProductBean productBean) {
        List<ProductBean.ResultBean> result = productBean.getResult();
        resultBeanList.addAll(result);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String code, String message) {
        showError(code, message);
    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

}
