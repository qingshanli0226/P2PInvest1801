package com.p2p.bawei.p2pinvest1801.home.mvp.view.fragment;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.common.Llog;
import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.custom.ProgressView;
import com.p2p.bawei.p2pinvest1801.home.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.home.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.home.mvp.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeContractView {
    private List<String> imageUrls;
    private ProgressView progressView;
    private TextView homeName;
    private TextView homeYearRate;
    private Banner homeBanner;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        homeBanner = findViewById(R.id.home_banner);

        progressView = findViewById(R.id.progress_view);
        homeName = findViewById(R.id.home_name);
        homeYearRate = findViewById(R.id.home_yearRate);

//        Bundle bundle = getArguments();
//        HomeBean home = bundle.getParcelable("home");
////        Log.i("liuxuan", "initView: "+home.getResult().getProInfo().getName());
//        Log.i("liuxuan", "initView: "+home.getMsg());
    }

    @Override
    public void initData() {
        mPresenter = new HomePresenter(new HomeModel(), this);
        mPresenter.getHome();


    }

    @Override
    public void onHomeBean(HomeBean homeBean) {
        Llog.d("onNext: " + homeBean.getCode() + "     size" + homeBean.getResult().getImageArr().size());

        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        imageUrls = new ArrayList<>();
        for (int i = 0; i < imageArr.size(); i++) {

            imageUrls.add(imageArr.get(i).getIMAURL());

        }


        HomeBean.ResultBean.ProInfoBean proInfo = homeBean.getResult().getProInfo();
        int parseInt = Integer.parseInt(proInfo.getProgress());
        progressView.setProgress(parseInt);
        homeName.setText(proInfo.getName());
        homeYearRate.setText("预期年利率:" + proInfo.getYearRate() + "%");


        homeBanner.setImages(imageUrls);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public void onUpDateBean(UpDateBean upDateBean) {

    }


}
