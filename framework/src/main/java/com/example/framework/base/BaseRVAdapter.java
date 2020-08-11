package com.example.framework.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder> {
    private BaseViewHolder.IRecylerViewItemClickListener iRecylerViewItemClickListener;
    private ArrayList<T> dataList=new ArrayList<>();

    public void updataData(List<T> datas){
        if(datas == null || datas.size() ==0){
            return;
        }
        dataList.clear();
        dataList.addAll(datas);
        notifyDataSetChanged();
    }


    //定义一个ViewHolder，可以适配不同的UI
    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        //Integer是id，View是控件，所有的控件的父类都是View,使用HashMap来存储这些Item里的所有View.findViewById,是比较耗时的
        HashMap<Integer,View> viewHashMap=new HashMap<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        //泛型方法，可以通过它获取view，并且强制转换成需要的view类型,可以参考系统的findViewById
        public <V extends View>V getView(@IdRes int id){
            View view = viewHashMap.get(id);
            if(view==null){
                view=itemView.findViewById(id);
                viewHashMap.put(id,view);
            }
            return (V)view;
        }

        public interface IRecylerViewItemClickListener{
            void onItemClick(int position);
        }

//        返回一种对应的布局类型，让子类来指定




    }
}
