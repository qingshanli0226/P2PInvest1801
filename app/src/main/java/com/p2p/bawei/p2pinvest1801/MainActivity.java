package com.p2p.bawei.p2pinvest1801;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager systemService = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = systemService.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean connected = networkInfo.isConnected();
        if(connected){
            Toast.makeText(this, "wife", Toast.LENGTH_SHORT).show();
        }
        NetworkInfo networkInfo1 = systemService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean connected1 = networkInfo1.isConnected();
        if(connected1){
            Toast.makeText(this, "手机网络", Toast.LENGTH_SHORT).show();

        }


    }
}
