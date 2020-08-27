package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.base.until.ColorUntil;
import com.leon.stellarmap.lib.StellarMap;

import java.util.ArrayList;
import java.util.Random;

public class ProductStellarMapAdapter implements StellarMap.Adapter {

    private Context context;
    private ArrayList<String> arrayList;

    public ProductStellarMapAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    //返回几组数据
    @Override
    public int getGroupCount() {
        return 3;
    }

    //每组有多少View
    @Override
    public int getCount(int i) {
        return 10;
    }

    @Override
    public View getView(int i, int i1, final View view) {
        final TextView textView;
        if(view == null){
            textView = new TextView(context);
        } else {
            textView = (TextView) view;
        }

        //list:33个数据 分成3组 每组11个数据
        //计算出每组需要展现的数据索引
        int listPosi = i * getCount(i) + i1;
        //设置TextView
        textView.setText(arrayList.get(listPosi));

        Random random = new Random();
        //1.设置随机的字体大小--包左不包右
        int textSize = random.nextInt(14) + 12;//12-24
        textView.setTextSize(textSize);

        //设置随机颜色
        textView.setTextColor(ColorUntil.randomBeautifulColor());

        //设置点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return textView;
    }

    @Override
    public int getNextGroupOnZoom(int i, boolean b) {
        return (i + 1) % getGroupCount();
    }
}
