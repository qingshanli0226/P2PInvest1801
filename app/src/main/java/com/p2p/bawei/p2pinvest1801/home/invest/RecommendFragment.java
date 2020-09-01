package com.p2p.bawei.p2pinvest1801.home.invest;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework2.manager.CacheManager;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {
    private HashMap<Integer, List<String>> map;

    private StellarMap stellar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        stellar=view.findViewById(R.id.recommend_stellar);
        map=new HashMap<>();

        List<String> mess=new ArrayList<>();
        for (int i = 0; i < CacheManager.getInstance().getInvestBean().getResult().size(); i++) {
            mess.add(CacheManager.getInstance().getInvestBean().getResult().get(i).getName());
        }
        map.put(0,mess);
        List<String> mess1=new ArrayList<>();
        for (int i = 0; i < CacheManager.getInstance().getInvestBean().getResult().size(); i++) {
            mess1.add(CacheManager.getInstance().getInvestBean().getResult().get(i).getName());
        }
        map.put(1,mess1);
        stellar.setAdapter(new RecommendAdapter(getActivity(),map));
        stellar.setGroup(0,true);
        stellar.setInnerPadding(10,10,10,10);
        stellar.setRegularity(30,15);
        return view;
    }

}
