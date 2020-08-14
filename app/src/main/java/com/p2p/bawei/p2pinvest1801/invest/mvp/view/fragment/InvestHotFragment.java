package com.p2p.bawei.p2pinvest1801.invest.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.p2p.bawei.p2pinvest1801.R;

/**
 * 全部
 */
public class InvestHotFragment extends Fragment {


    public InvestHotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_hot, container, false);
    }


}
