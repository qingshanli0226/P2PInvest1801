package com.bw.net;

import android.content.Context;
import android.content.SharedPreferences;

public class SpManager {

    private SharedPreferences sp;

    private SpManager() {

    }

    private static SpManager spManager;

    public static SpManager getInstance() {
        if (spManager == null) {
            synchronized (String.class) {
                if (spManager == null) {
                    spManager = new SpManager();
                }
            }
        }
        return spManager;
    }

    public void getSp(Context context, String spname) {
        sp = context.getSharedPreferences(spname, Context.MODE_PRIVATE);
    }

    public void addContent(String key,String name) {
        SharedPreferences.Editor edit = sp.edit();

        String string = sp.getString(key, "");
        if (string.equals("")){
            edit.remove(key);
        }
        edit.putString(key, name);
        edit.commit();
    }

    public String getContent(String key) {
        String token = sp.getString(key, "");
        return token;
    }

    public boolean isHaveToken() {
        String token = getContent("token");
        if (token.equals("")) {
            return false;
        }
        return true;
    }

    public void remove(String key){
        SharedPreferences.Editor edit = sp.edit();

        edit.remove(key);
        edit.commit();
    }
}
