package com.example.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {
    private View rootView;
    private String TAG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG="RH:"+getClass().getSimpleName();
        rootView=inflater.inflate(getLayoutId(),container,false);
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void inView();

    //    findViewById   @IdRes此注解表示一个资源id,不能随便传递一个整型
    public <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }


    //    log打印
    protected void printLog(String message){
        Log.d(TAG,message);
    }

    //    toast
    protected void showMessage(String message){
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    //    传值加跳转
    protected void launchActivity(Class launchActivityClass,Bundle bundle){
        Intent intent = new Intent();
        if(bundle==null){
            bundle=new Bundle();
        }
        intent.putExtras(bundle);
        intent.setClass(getContext(),launchActivityClass);
        startActivity(intent);
    }




}
