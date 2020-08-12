package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framwork.mvp.view.BaseFragment;
import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private Banner homeBanner;
    List<HomeBean.ResultBean.ImageArrBean> list;
    List<String> list_banner = new ArrayList<>();
    @Override
    public void initViews() {
        homeBanner = (Banner) findViewById(R.id.home_banner);
    }

    @Override
    public void initDatas() {
        list = CacheManager.getInstance().getList();
        for (int i = 0; i < list.size(); i++) {
            String imaurl = list.get(i).getIMAURL();
            list_banner.add(imaurl);
        }
        homeBanner.setImages(list_banner);
        Log.i("----", list.size()+"");
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .into(imageView);
            }
        });
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
