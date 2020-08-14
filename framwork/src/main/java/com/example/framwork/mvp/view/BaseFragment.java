package com.example.framwork.mvp.view;

import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framwork.R;
import com.example.framwork.mvp.presenter.IPresenter;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView,IFragment {
    protected P mPresenter;
    private View rootview;
//    protected AnimationDrawable animationDrawable;
//    protected ImageView image;
//    protected TextView basetext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(bandLayout(), container,false);
//        animationDrawable = (AnimationDrawable)rootview.findViewById(R.id.image).getBackground();
//        basetext = rootview.findViewById(R.id.basetext);
//        image = rootview.findViewById(R.id.image);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        initDatas();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return rootview.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.destroy();
            mPresenter = null;
        }
    }
}
