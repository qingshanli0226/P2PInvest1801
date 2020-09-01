package com.p2p.bawei.p2pinvest1801.invest.mvp.view.fragment;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.common.Llog;
import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.framwork.mvp.view.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.adapter.InvestAllAdapter;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;
import com.p2p.bawei.p2pinvest1801.invest.mvp.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.invest.mvp.model.InvestModel;
import com.p2p.bawei.p2pinvest1801.invest.mvp.presenter.InvestPresenter;

/**
 * 全部
 */
public class InvestAllFragment extends BaseFragment<InvestPresenter> implements InvestContract.IInvestContractView {
    private RecyclerView investAllRv;
    private InvestAllAdapter investAllAdapter;

    public InvestAllFragment() {
        // Required empty public constructor
    }


    @Override
    public int bandLayout() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public void initView() {
        investAllRv = findViewById(R.id.invest_all_rv);
    }

    @Override
    public void initData() {
        mPresenter = new InvestPresenter(new InvestModel(), this);
        investAllAdapter = new InvestAllAdapter();
        investAllRv.setAdapter(investAllAdapter);
        investAllRv.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorPrimary)));
        investAllRv.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onInvestAllBean(InvestAllBean investAllBean) {
        Llog.d( "onInvestAllBean: ." + investAllBean.getResult().get(0).getName());

        investAllAdapter.setNewData(investAllBean.getResult());
        investAllAdapter.notifyDataSetChanged();
    }
}
