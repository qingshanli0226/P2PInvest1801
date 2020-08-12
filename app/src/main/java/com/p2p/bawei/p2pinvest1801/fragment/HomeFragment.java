package com.p2p.bawei.p2pinvest1801.fragment;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeContractView {

    private Banner homeBanner;
    private List<String> imageUrls;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
//        homeBanner = findViewById(R.id.home_banner);


    }

    @Override
    public void initData() {
        mPresenter = new HomePresenter(new HomeModel(), this);
    }

    @Override
    public void onHomeBean(HomeBean homeBean) {
        Log.i("liuxuanxxx", "onNext: " + homeBean.getCode() + "     size" + homeBean.getResult().getImageArr().size());

//        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
//        imageUrls = new ArrayList<>();
//        for (int i = 0; i < imageArr.size(); i++) {
//
//            imageUrls.add(imageArr.get(i).getIMAURL());
//
//        }
//        homeBanner.setImageLoader(new ImageLoader() {
//                    @Override
//                    public void displayImage(Context context, Object path, ImageView imageView) {
//                        Glide.with(context).load(path).into(imageView);
//                    }
//                })
//                .isAutoPlay(true)
//                .setDelayTime(1000)
//                .setImages(imageUrls)
//                .start();
    }

    
}
