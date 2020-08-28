package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;

import java.util.ArrayList;
import java.util.Random;

public class MyAdapter implements StellarMap.Adapter {
    private Context context;
    private ArrayList<String> list;

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getCount(int i) {
        return 8;
    }

    @Override
    public View getView(int group, int position, View view) {
        final TextView textView = new TextView(context);
        //list:33个数据 分成3组 每组11个数据
        //计算出每组需要展现的数据索引
        int listPosi = group * getCount(group) + position;
        //设置text
        textView.setText(list.get(listPosi));

        Random random = new Random();
        //1.设置随机的字体大小--包左不包右
        final int textSize = random.nextInt(14) + 12;//12-24
        textView.setTextSize(textSize);

        //2.上色儿，设置随机颜色
        textView.setTextColor(ColorUtil.randomBeautifulColor());

        //3.设置点击事件
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
        return 0;
    }

}
