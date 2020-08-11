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

public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    private View baseView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return baseView = inflater.inflate(getLayoutId(),container,false);
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
        intent.putExtra("bundle",bundle);
        intent.setClass(getContext(),activityClass);
        startActivity(intent);
    }

    public <T extends View> T findViewById(@IdRes int id){
        return baseView.findViewById(id);
    }
}
