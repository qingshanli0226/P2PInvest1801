package com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.Share;
import com.p2p.bawei.p2pinvest1801.adapter.MyAllAdapter;
import com.p2p.bawei.p2pinvest1801.adapter.divider.MyDivider;
import com.p2p.bawei.p2pinvest1801.bean.AllBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.AllContact;
import com.p2p.bawei.p2pinvest1801.mvp.contact.MainContact;
import com.p2p.bawei.p2pinvest1801.mvp.model.AllModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.AllPre;
import com.sunfusheng.marqueeview.MarqueeView;

public class ChlidFragment1 extends BaseFragment implements AllContact.View {
    private AllPre allPre;
    private MyAllAdapter myAllAdapter;
    private RecyclerView ff1Rv;
    private MarqueeView marqueeView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("zcxfra", "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("zcxfra", "onCreate: ");
    }

    private MyDivider myDivider;
    @Override
    public int banlayout() {
        return R.layout.ff1;
    }

    @Override
    public void initView() {
        ff1Rv = findViewById(R.id.ff1_rv);
        marqueeView = findViewById(R.id.marqueeView);
    }

    @Override
    public void initData() {
        allPre=new AllPre(new AllModel(),this);
        allPre.allList();

        myAllAdapter=new MyAllAdapter(Share.Alllist);
        ff1Rv.setAdapter(myAllAdapter);
        ff1Rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ff1Rv.addItemDecoration(new MyDivider(getContext()));
        initanim();
    }
    private void initanim() {
        String notice = "硅谷金融壕运当头，首投返现最高达188元";
        marqueeView.startWithText(notice);
        // 在代码里设置自己的动画
        marqueeView.startWithText(notice, R.anim.anim_left_out, R.anim.anim_right_in);
    }

    @Override
    public void initAll(AllBean allBean) {
        for(int i = 0;i <allBean.getResult().size() ;i++){
            Share.Alllist.add(allBean.getResult().get(i));
        }


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("zcxfra", "onActivityCreated: ");
    }
    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
        Log.i("zcxfra", "onStart: ");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("zcxfra", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("zcxfra", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("zcxfra", "onStop: ");
        marqueeView.stopFlipping();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("zcxfra", "onDestroyView: ");
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        Log.i("zcxfra", "onDestroyOptionsMenu: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("zcxfra", "onDetach: ");
    }
}
