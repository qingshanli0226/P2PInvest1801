package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.lib_core.view.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.model.MyAllModel;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 投资
 */
public class RecommendFragment extends BaseFragment<MyAllPresenter> implements MyAllContract.View {

    private StellarMap stellarMap;
    private List<String> list = new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.fragment_popular;
    }

    @Override
    public void initView() {

        stellarMap = (StellarMap) findViewById(R.id.stellarMap);

    }

    @Override
    public void initInject() {

        mPresenter = new MyAllPresenter(new MyAllModel(),this);
    }

    @Override
    public void initData() {

        mPresenter.get_all();
    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

        List<MyAllBean.ResultBean> result = myAllBean.getResult();
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i).getName());
        }

        stellarMap.setAdapter(new MyAdapter(getContext(),list));
    }


}

//实现自己的内部接口StellarMap.Adapter
class MyAdapter implements StellarMap.Adapter {

    private Context context;
    private List<String> list;
    //getGroupCount获取组的数量


    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

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
    @Override
    public View getView(int group, int position, View convertView) {


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
                Toast.makeText(context, ""+textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return textView;
    }


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
}

  class ColorUtil {
    /**
     * 生成漂亮的颜色
     * @return
     */
    public static int randomBeautifulColor() {
        //值小一点--这样随机生成的颜色尽量都比较暗(因为在较亮的背景上--亮色看不明显)
        Random random = new Random();
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return Color.rgb(red, green, blue);
    }
}
