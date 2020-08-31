package com.p2p.bawei.p2pinvest1801.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.framwork.view.BaseFragment;
import com.bw.net.bean.AllLCBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.Mine_LCAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.contract.AllContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.AllModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.AllPresenter;

import java.util.ArrayList;

public class All_LC extends BaseFragment<AllPresenter> implements AllContract.View {
    private RecyclerView mineLcRv;
    private Mine_LCAdapter adapter;
    private ArrayList<AllLCBean.ResultBean> data = new ArrayList<>();
    private ImageView dogeimg;
    private AnimationDrawable dogeimgBackground;
    private TextView loadingtext,paoma;


    @Override
    public void initView() {
        mineLcRv = (RecyclerView) findViewById(R.id.mine_lc_rv);
        dogeimg = findViewById(R.id.doge_img);
        dogeimgBackground = (AnimationDrawable) dogeimg.getBackground();
        loadingtext=findViewById(R.id.loading_text);

        paoma=findViewById(R.id.paoma);
        paoma.setText("硅谷金融豪运到头，首投返现最高达188元。奥利给，干了兄弟们！");

        adapter = new Mine_LCAdapter(R.layout.minelcrv_item, data);
        mineLcRv.setAdapter(adapter);
        mineLcRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter = new AllPresenter(new AllModel(), this);
        mPresenter.data();

    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_mine_lc;
    }

    @Override
    public void setData(AllLCBean allLCBean) {
        data.clear();
        data.addAll(allLCBean.getResult());
        data.addAll(allLCBean.getResult());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        paoma.setVisibility(View.GONE);
        mineLcRv.setVisibility(View.GONE);
        dogeimg.setVisibility(View.VISIBLE);
        loadingtext.setVisibility(View.VISIBLE);
        dogeimgBackground.start();
    }

    @Override
    public void hideLoading() {
        paoma.setVisibility(View.VISIBLE);
        mineLcRv.setVisibility(View.VISIBLE);
        dogeimg.setVisibility(View.GONE);
        loadingtext.setVisibility(View.GONE);
        dogeimgBackground.stop();
    }

    @Override
    public void showError(int code, String message) {

    }
}
