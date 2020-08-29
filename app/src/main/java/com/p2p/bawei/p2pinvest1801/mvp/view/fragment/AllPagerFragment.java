package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framwork.mvp.view.BaseFragment;
import com.example.common.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.HomeAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.InvestModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.InvestPresenter;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyView;

import java.util.ArrayList;
import java.util.List;

public class AllPagerFragment extends BaseFragment<InvestPresenter> implements InvestContract.InvestView  {
    List<InvestBean.ResultBean> list_invest = new ArrayList<>();
    HomeAdapter homeAdapter;
    private RecyclerView allRv;
    MyView myView;
    private ImageView image;
    AnimationDrawable background;
    private TextView basetext;
    boolean flag =false;
    @Override
    public void initViews() {
        allRv = (RecyclerView) findViewById(R.id.all_rv);
        mPresenter = new InvestPresenter(new InvestModel(), this);
        homeAdapter = new HomeAdapter(R.layout.listview,list_invest);
        image = (ImageView) findViewById(R.id.image);
        basetext = (TextView) findViewById(R.id.basetext);
        background = (AnimationDrawable) image.getBackground();
    }


    @Override
    public void initDatas() {
        //只请求一次网络数据
        if(!flag){
            mPresenter.investList();
            flag = true;
        }
        allRv.setLayoutManager(new LinearLayoutManager(getContext()));
        allRv.setAdapter(homeAdapter);
    }

    @Override
    public int bandLayout() {
        return R.layout.allpage;
    }
    //小狗摇头显示
    @Override
    public void showLoading() {
        image.setVisibility(View.VISIBLE);
        basetext.setVisibility(View.VISIBLE);
        background.start();
    }
    //小狗摇头隐藏
    @Override
    public void hideLoading() {
        image.setVisibility(View.GONE);
        basetext.setVisibility(View.GONE);
        background.stop();
    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invest(InvestBean investbean) {
        list_invest.clear();
        list_invest.addAll(investbean.getResult());
        homeAdapter.notifyDataSetChanged();
    }


}
