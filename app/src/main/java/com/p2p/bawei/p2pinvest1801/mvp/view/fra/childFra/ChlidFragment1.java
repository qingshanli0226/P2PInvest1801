package com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra;

import android.graphics.Color;
import android.os.Bundle;
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

public class ChlidFragment1 extends BaseFragment implements AllContact.View {
    private AllPre allPre;
    private MyAllAdapter myAllAdapter;
    private RecyclerView ff1Rv;
    private MyDivider myDivider;
    @Override
    public int banlayout() {
        return R.layout.ff1;
    }

    @Override
    public void initView() {
        ff1Rv = findViewById(R.id.ff1_rv);
    }

    @Override
    public void initData() {
        allPre=new AllPre(new AllModel(),this);
        allPre.allList();

        myAllAdapter=new MyAllAdapter(Share.Alllist);
        ff1Rv.setAdapter(myAllAdapter);
        ff1Rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ff1Rv.addItemDecoration(new MyDivider(getContext()));
    }

    @Override
    public void initAll(AllBean allBean) {
        for(int i = 0;i <allBean.getResult().size() ;i++){
            Share.Alllist.add(allBean.getResult().get(i));
        }
    }
}
