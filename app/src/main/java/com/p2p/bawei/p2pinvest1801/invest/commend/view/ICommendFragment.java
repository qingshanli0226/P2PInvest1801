package com.p2p.bawei.p2pinvest1801.invest.commend.view;


import com.example.framework.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.commend.adapter.StellarMapAdapter;

import java.util.ArrayList;

public class ICommendFragment extends BaseFragment {
    private StellarMap stellarmap;
    private ArrayList<String> stellList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_icommend;
    }

    @Override
    protected void initData() {
        stellList.add("新手福利计划");
        stellList.add("山海经异兽");
        stellList.add("银行理财");
        stellList.add("贪玩蓝月");
        stellList.add("1秒9999刀");
        stellList.add("财神道90天计划");
        stellList.add("大唐逍遥驸马爷");
        stellList.add("七个大佬争着宠我");
        stellList.add("硅谷钱包计划");
        stellList.add("他的小祖宗甜爆了");
        stellList.add("热门电视剧");
        stellList.add("30天理财计划(加息2%)");
        stellList.add("网页微信");
        stellList.add("LaunchMode的作用和使用方法");
        stellList.add("180天理财计划(加息5%)");
        stellList.add("六间房直播");
        stellList.add("携程旅行网");
        stellList.add("月月升理财计划(加息10%)");
        stellList.add("搜狗理财");
        stellList.add("中情局投资商业经营");
        stellList.add("乘风破浪的姐姐");
        stellList.add("变态9999亿");
        stellList.add("大学老师购买车辆");
        stellList.add("0充值私服");
        stellList.add("屌丝下海经商计划");
        stellList.add("BOSS直聘");
        stellList.add("笑话 | 机票");
        stellList.add("NBA");
        stellList.add("美人鱼影视拍摄投资");
        stellList.add("中国总共出了408位皇帝");
        stellList.add("唯 品 会");
        stellList.add("Android培训老师自己周转");
        stellList.add("芒果热门综艺");
        stellList.add("搜狗商城");
        stellList.add("养猪场扩大经营");
        stellList.add("7k7k小游戏");
        stellList.add("9块9包邮");
        stellList.add("开局一条鲲");
        stellList.add("旅游公司扩大规模");
        stellList.add("环球新军事");
        stellList.add("铁路局回款计划");
        stellList.add("米尔军事网");
        stellList.add("紧急军情");
        stellList.add("屌丝迎娶白富美计划");
        stellList.add("一刀满神装");

        stellarmap.setAdapter(new StellarMapAdapter(getContext(), stellList));
    }

    @Override
    protected void initView() {
        stellarmap = findViewById(R.id.stellarmap);
        int padding = getResources().getDimensionPixelSize(R.dimen.margin15);//里面的文字对于布局的边缘还是有距离
        int bottomPadding = padding + getResources().getDimensionPixelSize(R.dimen.margin50);
        stellarmap.setInnerPadding(padding, padding, padding, bottomPadding);
    }

}
