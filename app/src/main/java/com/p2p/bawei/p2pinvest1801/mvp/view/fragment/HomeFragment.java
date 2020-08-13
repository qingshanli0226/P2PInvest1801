package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framwork.mvp.view.BaseFragment;
import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.p2p.bawei.p2pinvest1801.mvp.view.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

public class HomeFragment extends BaseFragment {
    private Banner homeBanner;
    List<HomeBean.ResultBean.ImageArrBean> list;
    List<String> list_banner = new ArrayList<>();
    String[] text = new String[]{"分享砍学费","人脉总动员","想不到你是这样的app","购物节,爱不单行"};
    private TextView homeTitle;
    private ProgressView homeProgress;
    List<HomeBean.ResultBean.ProInfoBean> list_proinfobean;
    @Override
    public void initViews() {
        homeBanner = (Banner) findViewById(R.id.home_banner);
        homeTitle = (TextView) findViewById(R.id.home_title);
        homeProgress = (ProgressView) findViewById(R.id.home_progress);
        list_proinfobean = CacheManager.getInstance().getList_proinfobean();
        Log.i("----listsize", list_proinfobean.size()+"");
        if(list_proinfobean.size() != 0){
            homeTitle.setText(list_proinfobean.get(0).getName());
            String progress = list_proinfobean.get(0).getProgress();
            homeProgress.setProgress(Integer.parseInt(progress));
        }else{
            Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initDatas() {
        list = CacheManager.getInstance().getList();
        for (int i = 0; i < list.size(); i++) {
            String imaurl = list.get(i).getIMAURL();
            list_banner.add(imaurl);
            Log.i("----list", list_banner.get(i));
        }
        homeBanner.setImages(list_banner);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .into(imageView);
            }
        });
        homeBanner.setBannerTitles(Arrays.asList(text));
        homeBanner.start();
    }

    @Override
    public int bandLayout() {
        return R.layout.homefragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }
}
