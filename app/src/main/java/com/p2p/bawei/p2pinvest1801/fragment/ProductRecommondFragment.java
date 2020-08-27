package com.p2p.bawei.p2pinvest1801.fragment;

import com.example.common.CacheManager;
import com.example.framework.base.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.ProductStellarMapAdapter;

import java.util.ArrayList;

public class ProductRecommondFragment extends BaseFragment {

    private StellarMap productRecommond;
    //数据源
    private ArrayList<String> arrayList = new ArrayList<>();
    //适配器
    private ProductStellarMapAdapter productstellarMapAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommon;
    }

    @Override
    protected void initData() {
        //添加数据
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("180天理财计划");
        arrayList.add("月月升");
        arrayList.add("中情局投资商业经营");
        arrayList.add("屌丝下海经商计划");
        arrayList.add("美人鱼影视拍摄投资");
        arrayList.add("Android培训老师自己周转");
        arrayList.add("养猪场计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("养猪场计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("180天理财计划");
        arrayList.add("月月升");
        arrayList.add("中情局投资商业经营");
        arrayList.add("屌丝下海经商计划");
        arrayList.add("美人鱼影视拍摄投资");
        arrayList.add("Android培训老师自己周转");
        arrayList.add("养猪场计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("养猪场计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");
        arrayList.add("硅谷计划");
        arrayList.add("30天理财计划");
        arrayList.add("养猪场计划");
        arrayList.add("新手福利计划");
        arrayList.add("财神到90天计划");


        productstellarMapAdapter = new ProductStellarMapAdapter(getContext(),arrayList);
        productRecommond.setAdapter(productstellarMapAdapter);

    }

    @Override
    protected void initView() {
        productRecommond = (StellarMap) findViewById(R.id.productRecommond);
    }
}
