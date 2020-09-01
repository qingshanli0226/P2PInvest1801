package com.p2p.bawei.p2pinvest1801.invest.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;

import java.util.List;
import java.util.Random;

public class StellarMapAdapter implements StellarMap.Adapter {

    private Context context;
    private List<String> list;

    public StellarMapAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getCount(int i) {
        return 7;
    }

    @Override
    public View getView(int i, int i1, View view) {

        final TextView textView = new TextView(context);
        //计算出每组需要展现的数据索引
        int listPosi = i * getCount(i) + i1;
        textView.setText(list.get(listPosi));


        Random random = new Random();
        //1.设置随机的字体大小--包左不包右
        int textSize = random.nextInt(14) + 12;
        textView.setTextSize(textSize);


        //2.上色儿，设置随机颜色
        textView.setTextColor(ColorUtil.randomBeautifulColor());

        //3.设置点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return textView;
    }

    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        return (i + 1) % getGroupCount();
    }

}
