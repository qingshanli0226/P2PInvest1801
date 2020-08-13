package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.mysef.MyPragessagerView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Myfragment extends BaseFragment {
    private Banner bann;
    private MyPragessagerView pv;
    private List<String> list_pic=new ArrayList<>();

    @Override
    public int BondLayout() {
        return R.layout.myfragment;
    }

    @Override
    public void initview() {
        bann = (Banner) findViewById(R.id.bann);
        pv = (MyPragessagerView) findViewById(R.id.pv);
    }

    @Override
    public void initdata() {
        pv.setProgress(90);
        Intent intent = getActivity().getIntent();
        MyBannerEntity myBannerEntity = (MyBannerEntity) intent.getSerializableExtra("banner");
        List<MyBannerEntity.ResultBean.ImageArrBean> imageArr = myBannerEntity.getResult().getImageArr();
        Log.e("list_size", "initdata: "+imageArr.get(0).getIMAPAURL() );
        for (int i = 0; i < imageArr.size(); i++) {
            list_pic.add(imageArr.get(i).getIMAURL());
        }
        bann.setImages(list_pic);
        bann.setBannerAnimation(Transformer.DepthPage);
        bann.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        });
        bann.start();


    }

    @Override
    public void initInJect() {

    }
}
