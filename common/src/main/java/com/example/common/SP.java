package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    private Context context;
    private static SP getSp;

    public static SP getSp() {
        if (getSp == null) {
            getSp = new SP();
        }
        return getSp;
    }

    public Context getContext() {
        return context;
    }

    public boolean setMessage(String key, Object object) {
        String str = (String) object;
        SharedPreferences sp = context.getSharedPreferences("SP", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, str);
        return edit.commit();
    }

    public String getMessage(String key) {
        SharedPreferences sp = context.getSharedPreferences("SP", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        return sp.getString(key, "");
    }
}
