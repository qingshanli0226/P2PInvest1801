package com.p2p.bawei.p2pinvest1801.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.framework.base.BaseMVPFragment;
import com.example.net.mode.InvestListBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.ProductListAdapter;
import com.p2p.bawei.p2pinvest1801.contract.ProductListContract;
import com.p2p.bawei.p2pinvest1801.presenter.ProductListPresenterImpl;
import com.p2p.bawei.p2pinvest1801.ui.MyLoadingView;

import java.util.ArrayList;

public class ProductListFragment extends BaseMVPFragment<ProductListPresenterImpl, ProductListContract.ProductListView> implements ProductListContract.ProductListView {

    private RecyclerView investListRv;
    private ImageView listImg;
    private AnimationDrawable animationDrawable;
    private MyLoadingView listLoadingView;



    //数据源
    private ArrayList<InvestListBean.ResultBean> resultBeans = new ArrayList<>();
    //适配器
    private ProductListAdapter productListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initData() {
        //创建适配器
        productListAdapter = new ProductListAdapter(R.layout.item_product_list,resultBeans);
        //设置设配器
        investListRv.setAdapter(productListAdapter);
        investListRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initView() {
        investListRv = (RecyclerView) findViewById(R.id.investListRv);
//        listImg = (ImageView) findViewById(R.id.listImg);
//        animationDrawable = (AnimationDrawable) listImg.getDrawable();
        listLoadingView = (MyLoadingView) findViewById(R.id.listLoadingView);

    }

    @Override
    protected void initGetData() {
            //获取数据
            iHttpPresenter.onGetListData();

    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new ProductListPresenterImpl();
    }

    @Override
    public void onListData(InvestListBean investListBean) {
//        printLog(investListBean.toString()+"-----");
        //清除所以数据
        resultBeans.clear();
        resultBeans.addAll(investListBean.getResult());
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoading() {
        listLoadingView.setVisibility(View.VISIBLE);
//        listImg.setVisibility(View.VISIBLE);
//        animationDrawable.start();
    }

    @Override
    public void hideLoading() {
        listLoadingView.setVisibility(View.GONE);
        listLoadingView.stop();
//        listImg.setVisibility(View.GONE);
//        animationDrawable.stop();
    }
}
