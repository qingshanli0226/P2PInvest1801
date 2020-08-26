package com.p2p.bawei.p2pinvest1801.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.MyFragment_Fly;
import com.p2p.bawei.p2pinvest1801.utils.ColorUtil;

import java.util.List;
import java.util.Random;

//实现自己的内部接口StellarMap.Adapter
public class MyAdapter implements StellarMap.Adapter {
    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
//getGroupCount获取组的数量

    /**
     * 获取组的数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return 2;
    }


    //getCount返回每组有多少个item

    /**
     * 返回每组多少个
     *
     * @param group
     * @return
     */
    @Override
    public int getCount(int group) {
        return 5;
    }


    //getView返回每个组的每个item 的对象

    /**
     * 返回每个组的view对象
     *
     * @param group       当前是第几组
     * @param position    表示组中的position，并不是list中的position
     * @param convertView
     * @return
     */
    //getNextGroupOnPan—没用
    //getNextGroupOnZoom返回的是下一组要加载的数据
    /**
     * 当执行完缩放动画后，下一组加载哪一组的数据
     *
     * @param group    当前是第几组
     * @param isZoomIn
     * @return 返回的是下一组要加载的数据
     */
    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {

        //0->1->2->0
        //返回下一组加载哪一组的数据
        return (group + 1) % getGroupCount();
    }
    @Override
    public View getView ( int group, int position, View convertView){
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
                Toast.makeText(context,textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return textView;
    }

}

