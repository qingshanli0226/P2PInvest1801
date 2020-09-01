package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RecommendAdapter implements StellarMap.Adapter {
    private Context context;
    private HashMap<Integer, List<String>> map;

    public RecommendAdapter(Context context, HashMap<Integer, List<String>> map) {
        this.context = context;
        this.map = map;
    }

    @Override
    public int getGroupCount() {

        return map.size();
    }

    @Override
    public int getCount(int i) {
        return map.get(i).size();
    }

    @Override
    public View getView(int i, int i1, View view) {
        final TextView tv = new TextView(context);
        tv.setText(map.get(i).get(i1));
        Random random = new Random();
        int textSize = random.nextInt(8)+15;
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
        int red=random.nextInt(211);
        int green=random.nextInt(211);
        int blue=random.nextInt(211);
        tv.setTextColor(Color.rgb(red,green,blue));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return tv;
    }

    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        return (i+1)%getGroupCount();
    }
}
