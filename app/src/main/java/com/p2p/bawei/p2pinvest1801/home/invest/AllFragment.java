package com.p2p.bawei.p2pinvest1801.home.invest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.p2p.bawei.p2pinvest1801.CacheManager;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    private View inflate;
    private RecyclerView invest_all_rcv;
    private InvestAllAdapter allAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_all, container, false);
        invest_all_rcv=inflate.findViewById(R.id.invest_all_rcv);
        invest_all_rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        allAdapter=new InvestAllAdapter(CacheManager.getInstance().getInvestBean().getResult());
        invest_all_rcv.setAdapter(allAdapter);
        return inflate;
    }

}
