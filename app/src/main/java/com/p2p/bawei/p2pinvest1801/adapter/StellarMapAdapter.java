package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.util.ColorUntil;

import java.util.List;
import java.util.Random;

public class StellarMapAdapter implements StellarMap.Adapter {
    Context context;
    List<String> list;
    public StellarMapAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getCount(int i) {
        return 5;
    }

    @Override
    public View getView(int group, int position, View view) {
        final TextView textView = new TextView(context);
        int listPosi = group * getCount(group) + position;
        textView.setText(list.get(listPosi));
        Random random = new Random();

        int textsize = random.nextInt(14 + 12) + 12;
        textView.setTextSize(textsize);
        textView.setTextColor(ColorUntil.randomBeautifulColor());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }

    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        return (i + 1) % getGroupCount();
    }

}
