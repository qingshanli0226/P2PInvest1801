package com.p2p.bawei.p2pinvest1801.more.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.common.Llog;
import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.more.adapter.AboutFinanceAdapter;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;
import com.p2p.bawei.p2pinvest1801.more.mvp.contract.AboutFinanceContract;
import com.p2p.bawei.p2pinvest1801.more.mvp.model.AboutFinanceModel;
import com.p2p.bawei.p2pinvest1801.more.mvp.presenter.AboutFinancePresenter;

import java.util.List;

public class AboutFinanceActivity extends BaseActivity<AboutFinancePresenter> implements AboutFinanceContract.IAboutFinanceView {


    private RecyclerView activityAboutFinanceRv;
    private AboutFinanceAdapter aboutFinanceAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_about_finance;
    }

    @Override
    public void initView() {
        activityAboutFinanceRv = findViewById(R.id.activity_about_finance_rv);

        mPresenter = new AboutFinancePresenter(new AboutFinanceModel(), this);

        aboutFinanceAdapter = new AboutFinanceAdapter();
        activityAboutFinanceRv.setLayoutManager(new LinearLayoutManager(this));
        activityAboutFinanceRv.setAdapter(aboutFinanceAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onAboutFinance(AboutFinanceBean financeBeanList) {
        for (int i = 0; i < financeBeanList.getResult().size(); i++) {
            Llog.d("onAboutFinance: " + financeBeanList.getResult().get(i).getCoverImg());
        }
        aboutFinanceAdapter.setNewData(financeBeanList.getResult());

    }
}
