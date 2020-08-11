package com.p2p.bawei.p2pinvest1801;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelComeAct extends AppCompatActivity {
   Handler handler =new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
           Intent intent = new Intent(WelComeAct.this, MainActivity.class);
           startActivity(intent);
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        handler.sendEmptyMessageDelayed(0,2000);

    }
}
