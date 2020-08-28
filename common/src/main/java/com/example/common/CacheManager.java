package com.example.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CacheManager {

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private CacheManager() {
    }

    private static CacheManager cacheManager;

    public static CacheManager getInstance(){
        if(cacheManager == null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public void init(final Context context){
        //初始化Sp文件
        sharedPreferences = context.getSharedPreferences(FinanceConstant.SP_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
