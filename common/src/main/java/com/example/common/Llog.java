package com.example.common;

import android.util.Log;

public class Llog {

    private static boolean IS_DEGUB = true;
    private static final String TAG = "Llog";

    public static void d(String msg) {
        if (IS_DEGUB) {
            Log.d(TAG, msg);
        }
    }
}
