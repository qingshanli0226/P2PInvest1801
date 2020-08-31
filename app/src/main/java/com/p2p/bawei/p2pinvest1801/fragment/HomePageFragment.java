package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.HomeDataManager;
import com.bw.common.view.MyProgressBar;
import com.bw.framwork.view.BaseFragment;
import com.bw.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment {
    private MyProgressBar myProgressBar;
    private Banner banner;
    private ArrayList<String> imgs=new ArrayList<>();   //储存图片路径
    private ArrayList<String> imgsText=new ArrayList<>(); //储存图片的提示文字
    private TextView maintext,textnum;

    @Override
    public void initView() {
        banner= (Banner) findViewById(R.id.main_banner);
        maintext= (TextView) findViewById(R.id.main_text);
        myProgressBar= (MyProgressBar) findViewById(R.id.myPro);
        textnum= (TextView) findViewById(R.id.main_text_num);

    }

    @Override
    public void initData() {
        imgs.clear();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = HomeDataManager.getInstance().getHomeBean().getResult().getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            HomeBean.ResultBean.ImageArrBean imageArrBean = imageArr.get(i);
            imgs.add(imageArrBean.getIMAURL());
        }

        banner.setImages(imgs);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }

        });

        imgsText.clear();
        imgsText.add("分享砍学费");
        imgsText.add("人脉总动员");
        imgsText.add("想不到你是这样的app");
        imgsText.add("购物街爱不单行");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setBannerTitles(imgsText);
        banner.setDelayTime(2000);
        banner.start();

        HomeBean.ResultBean.ProInfoBean proInfo = HomeDataManager.getInstance().getHomeBean().getResult().getProInfo();

        maintext.setText(proInfo.getName()+"");

        int progress = Integer.parseInt(proInfo.getProgress());
        myProgressBar.setProgress(progress);
        myProgressBar.showProgressBarAnimation();

        textnum.setText("预期年利率为："+proInfo.getYearRate()+"%");

    }

    @Override
    public int bandLayout() {
        return R.layout.homepagerfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myProgressBar.unMyProgressBar();
    }
}
