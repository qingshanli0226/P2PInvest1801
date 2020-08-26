package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.common.mvp.view.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAdapter;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentfragmentAdapter;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyInvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.MyInvestModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MyGetInvestPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyFragment_Fly extends BaseFragment<MyGetInvestPresenter>implements MyInvestContract.mView {
    private StellarMap stellarMap;

    private List<String> list=new ArrayList<>();
    @Override
    public int BondLayout() {
        return R.layout.myfragmentfly;
    }

    @Override
    public void initview() {

        stellarMap = (StellarMap) findViewById(R.id.stellarMap);
        int padding = getResources().getDimensionPixelSize(R.dimen.dp15);//里面的文字对于布局的边缘还是有距离
        int bottomPadding = padding + getResources().getDimensionPixelSize(R.dimen.dp50);
        stellarMap.setInnerPadding(padding, padding, padding, bottomPadding);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void initInJect() {
        mPresenter=new MyGetInvestPresenter(new MyInvestModel(),this);
        mPresenter.getInvest();
    }
    @Override
    public void getinvest(MyInestEntivity myInestEntivity) {
        List<MyInestEntivity.ResultBean> beans = myInestEntivity.getResult();
        for (int i = 0; i < beans.size(); i++) {
            list.add(beans.get(i).getName());
            Log.e("fly", "getinvest: "+beans.get(i).getName() );
        }

        MyAdapter myAdapter = new MyAdapter(getContext(),list);
        stellarMap.setAdapter(myAdapter);
    }
}
