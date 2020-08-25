package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {

    private SharedPreferences sharedPreferences;//取
    private SharedPreferences.Editor editor;//存


    private static CacheManager cacheManager;

    private CacheManager() {
    }

    public static CacheManager getCacheManager() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }
}
