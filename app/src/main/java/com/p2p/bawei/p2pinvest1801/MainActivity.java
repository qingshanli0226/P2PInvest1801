package com.p2p.bawei.p2pinvest1801;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnThread).setOnClickListener(this);
        Log.d("LQS", "1801学习git");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThread:
                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
                break;
        }
    }
}
