package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.baseview.BaseProgressView;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    private Banner mainHomeViewBanner;
    private TextView mainHomeViewBannerText;
    private List<String> banner_data_list = new ArrayList<>();
    private BaseProgressView mainHomeViewBaseProgressView;
    @Override
    public void initView() {
        mainHomeViewBanner = (Banner) findViewById(R.id.main_home_view_banner);
        mainHomeViewBannerText = (TextView) findViewById(R.id.main_home_view_banner_text);
        mainHomeViewBaseProgressView = (BaseProgressView) findViewById(R.id.main_home_view_BaseProgressView);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {
        mPresenter = new HomePresenter(new HomeModel(),this);
        mPresenter.getData();
    }

    @Override
    public int BandLayout() {
        return R.layout.main_home_layout_fragment;
    }

    @Override
    public void initHomeData(HomeBean homeBean) {
        //请求到的home数据
        HomeBean.ResultBean result = homeBean.getResult();
        //banner数据
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();
        initBannerData(imageArr);

        HomeBean.ResultBean.ProInfoBean proInfo = result.getProInfo();
        int i = Integer.parseInt(proInfo.getProgress());
        mainHomeViewBaseProgressView.setProgress(i);
        Log.e("HomeFragment -> initHomeData", "initHomeData: "+i );
    }

    //获取banner数据并启动banner
    private void initBannerData(List<HomeBean.ResultBean.ImageArrBean> list){
        for (int i = 0 ; i < list.size() ; i++){
            banner_data_list.add(list.get(i).getIMAURL());
        }
        if (banner_data_list.size() >= 0){
            mainHomeViewBanner.setBannerAnimation(Transformer.DepthPage);
            mainHomeViewBanner.setImages(banner_data_list);
            mainHomeViewBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            mainHomeViewBanner.start();
        }
    }

}
