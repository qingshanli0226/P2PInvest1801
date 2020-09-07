package com.p2p.bawei.p2pinvest1801.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class ConnectManager extends BroadcastReceiver {

    private ArrayList<MConnectChangeListener> mConnectChangeListeners=new ArrayList<>();
    private ConnectManager() {
    }


    private static ConnectManager connectManager;

    public static ConnectManager getInstance() {
        if (connectManager == null) {
            synchronized (String.class) {
                if (connectManager == null) {
                    connectManager = new ConnectManager();
                }
            }
        }
        return connectManager;
    }

    private boolean isConnected=false;

    public boolean isConnected() {
        return isConnected;
    }

    public void init(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetworkInfo.isConnected();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Context.CONNECTIVITY_SERVICE);

        context.registerReceiver(this,intentFilter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Context.CONNECTIVITY_SERVICE)){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = activeNetworkInfo.isConnected();

            for (int i = 0; i < mConnectChangeListeners.size(); i++) {
                MConnectChangeListener mConnectChangeListener = mConnectChangeListeners.get(i);
                mConnectChangeListener.connectChanged(isConnected);
            }
        }
    }

    public interface MConnectChangeListener{
        void connectChanged(boolean ischange);
    }

    public void addMConnectChangeListener(MConnectChangeListener mConnectChangeListener){
        synchronized (String.class){
            if (!mConnectChangeListeners.contains(mConnectChangeListener)){
                mConnectChangeListeners.add(mConnectChangeListener);
            }
        }
    }

    public void removeMConnectChangeListener(MConnectChangeListener mConnectChangeListener){
        synchronized (String.class){
            if (mConnectChangeListeners.contains(mConnectChangeListener)){
                mConnectChangeListeners.remove(mConnectChangeListener);
            }
        }
    }
}
