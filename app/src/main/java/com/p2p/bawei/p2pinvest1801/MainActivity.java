package com.p2p.bawei.p2pinvest1801;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.net.connecct.NetConnect;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean networkConnected = NetConnect.isNetworkConnected(this);
        if (!networkConnected) {
            Toast.makeText(this, "当前网络未连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已连接网络", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {

    }
}
 