package com.p2p.bawei.p2pinvest1801.fragment;

import android.view.MotionEvent;
import android.view.View;

import com.bw.common.view.MyHotDanmuView;
import com.bw.framwork.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

public class Recommend_LC extends BaseFragment {
    private MyHotDanmuView myHotDanmuView;
    private ArrayList<String> titles=new ArrayList<>();


    @Override
    public void initView() {
        myHotDanmuView= (MyHotDanmuView) findViewById(R.id.myHotDanmuView);

        myHotDanmuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        myHotDanmuView.setTitles(titles);
                        myHotDanmuView.invalidate();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        titles.clear();
                        titles.add("Giao");
                        titles.add("wwwww");
                        titles.add("揭棺而起！");
                        titles.add("Giao");
                        titles.add("wwwww");
                        titles.add("揭棺而起！");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_recommend_lc;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myHotDanmuView.ondestoryView();
    }
}
