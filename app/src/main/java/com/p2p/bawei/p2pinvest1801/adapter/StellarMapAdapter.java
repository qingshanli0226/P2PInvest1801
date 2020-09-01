package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;

import java.util.List;
import java.util.Random;

public class StellarMapAdapter implements StellarMap.Adapter {

    private List<String> list;
    private Context context;

    public StellarMapAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getCount(int i) {
        return 10;
    }

    @Override
    public View getView(int i, int i1, View view) {
        TextView textView = new TextView(context);
        final int i2 =i*getCount(i)+i1;
        Log.e("aaaaaaaaaaaaa", "getView: "+i+":"+":"+i1+":"+i2 );
        Random random = new Random();
        int a1 = random.nextInt(300);
        int a2 = random.nextInt(300);
        int a3 = random.nextInt(300);

        textView.setTextColor(Color.rgb(a1,a2,a3));
        textView.setText(list.get(i2));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+list.get(i2), Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }

    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        return (i+1)%getGroupCount();
    }
}
