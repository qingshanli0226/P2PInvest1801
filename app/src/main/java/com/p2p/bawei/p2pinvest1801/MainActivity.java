package com.p2p.bawei.p2pinvest1801;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView ivIdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ljl", "1801学习git");
        
        ivIdActivity = findViewById(R.id.iv_id_activity);
    }
}
