package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.StellarMapAdapter;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyView;

import java.util.ArrayList;
import java.util.List;

public class CommendFragment extends BaseFragment {
    private StellarMap commend;
    StellarMapAdapter stellarMapAdapter;
    List<String> list = new ArrayList<>();
    @Override
    public void initViews() {
        commend = (StellarMap) findViewById(R.id.commend);

    }
    @Override
    public void initDatas() {
        list.add("30天理财计划(加息2%)");
        list.add("30天理财计划(加息2%)");
        list.add("财神道90天计划");
        list.add("财神道90天计划");
        list.add("硅谷钱包计划");
        list.add("硅谷钱包计划");
        list.add("月月升理财计划(加息10%)");
        list.add("月月升理财计划(加息10%)");
        list.add("中情局投资商业经营");
        list.add("中情局投资商业经营");
        list.add("新手福利计划");
        list.add("新手福利计划");
        list.add("180天理财计划(加息5%)");
        list.add("180天理财计划(加息5%)");
        list.add("180天理财计划(加息5%)");
        list.add("180天理财计划(加息5%)");
        stellarMapAdapter = new StellarMapAdapter(getContext(), list);
        commend.setAdapter(stellarMapAdapter);
    }


    @Override
    public int bandLayout() {
        return R.layout.commendfragment;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }
}
