package com.example.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.FinanceConstant;
import com.example.common.view.ToolBar;
import com.example.framework.R;

public abstract class BaseFragment extends Fragment implements ToolBar.IToolBarClickListner {

    private static final String TAG = "BaseFragment";
    private View baseView;
    ToolBar toolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(getLayoutId(),container,false);
        toolBar = findViewById(R.id.toolBar);
        toolBar.setiToolBarClickListner(this);
        return baseView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    public void showMessage(String message){
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String message){
        Log.i(TAG, "printLog: "+message);
    }

    public void lunachActivity(Class activityClass,Bundle bundle){
        Intent intent = new Intent();
        intent.putExtra(FinanceConstant.BUNDLE,bundle);
        intent.setClass(getContext(),activityClass);
        startActivity(intent);
    }

    public <T extends View> T findViewById(@IdRes int id){
        return baseView.findViewById(id);
    }

    @Override
    public void onLeftClick() {
        getActivity().finish();
    }

    @Override
    public void onRightClick() {

    }
}
