package com.p2p.bawei.p2pinvest1801.invest.all.view;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPFragment;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.all.adapter.AllAdapter;
import com.p2p.bawei.p2pinvest1801.invest.all.contract.ProductContract;
import com.p2p.bawei.p2pinvest1801.invest.all.presenter.ProductPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IAllFragment extends BaseMVPFragment<ProductPresenterImpl, ProductContract.IProductView> implements ProductContract.IProductView {

    private MyLoadingBar loadingBar;
    private AllAdapter allAdapter;
    private List<ProductBean.ResultBean> resultBeanList = new ArrayList<>();

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
        return R.layout.fragment_iall;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);
        RecyclerView allRv = findViewById(R.id.all_rv);

        allAdapter = new AllAdapter(R.layout.invest_all_item, resultBeanList);
        allRv.setAdapter(allAdapter);
        allRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onProductData(ProductBean productBean) {
        List<ProductBean.ResultBean> result = productBean.getResult();
        resultBeanList.addAll(result);
        allAdapter.notifyDataSetChanged();
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
