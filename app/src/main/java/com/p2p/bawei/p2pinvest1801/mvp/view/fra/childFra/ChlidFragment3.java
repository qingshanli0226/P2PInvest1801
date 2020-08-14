package com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.p2p.bawei.p2pinvest1801.R;

public class ChlidFragment3 extends Fragment {
     View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ff3,container,false);
        return view;
    }
}
