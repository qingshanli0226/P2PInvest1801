package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAllAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.model.MyAllModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.util.List;

/**
 * 全部理财页面
 */
public class AllManagementFragment extends BaseFragment<MyAllPresenter> implements MyAllContract.View {

    private RecyclerView recyclerView;
    private MyAllAdapter myAllAdapter;


    @Override
    public int bandLayout() {
        return R.layout.fragment_all_management;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myAllAdapter = new MyAllAdapter(R.layout.layout_fragment_management,null);
        recyclerView.setAdapter(myAllAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAllAdapter.notifyDataSetChanged();



    }

    @Override
    public void initInject() {

        mPresenter = new MyAllPresenter(new MyAllModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_all();
    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

        List<MyAllBean.ResultBean> data = myAllAdapter.getData();
        data.addAll(myAllBean.getResult());
        myAllAdapter.notifyDataSetChanged();
    }


}
