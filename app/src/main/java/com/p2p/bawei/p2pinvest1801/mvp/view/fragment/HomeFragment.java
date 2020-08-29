package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framwork.mvp.view.BaseFragment;
import com.example.common.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyDialog;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//首页
public class HomeFragment extends BaseFragment implements CacheManager.IDataChangeListener{
    private Banner homeBanner;
    List<HomeBean.ResultBean.ImageArrBean> list;
    List<String> list_banner = new ArrayList<>();
    String[] text = new String[]{"分享砍学费","人脉总动员","想不到你是这样的app","购物节,爱不单行"};
    private TextView homeTitle;
    private ProgressView homeProgress;
    List<HomeBean.ResultBean.ProInfoBean> list_proinfobean;
    private Button homeNull;
    private TextView homeAnticipate;
    private MyDialog ss;
    @Override
    public void initViews() {
        homeBanner = (Banner) findViewById(R.id.home_banner);
        homeTitle = (TextView) findViewById(R.id.home_title);
        homeProgress = (ProgressView) findViewById(R.id.home_progress);
        homeAnticipate = (TextView) findViewById(R.id.home_anticipate);
        homeNull = (Button) findViewById(R.id.home_null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeProgress.removehandler();
    }

    @Override
    public void initDatas() {
        homeNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss.showCancel(View.VISIBLE);
            }
        });
        list = CacheManager.getInstance().getList();
        list_proinfobean = CacheManager.getInstance().getList_proinfobean();
        if(list.size() != 0 && list_proinfobean.size() != 0){
            list_banner.clear();
            homeTitle.setText(list_proinfobean.get(0).getName());
            String progress = list_proinfobean.get(0).getProgress();
            homeProgress.setProgress(Integer.parseInt(progress));
            homeAnticipate.setText("预期年利率:  "+list_proinfobean.get(0).getYearRate()+"%");
            for (int i = 0; i < list.size(); i++) {
                String imaurl = list.get(i).getIMAURL();
                list_banner.add(imaurl);
            }
            homeBanner.setImages(list_banner);
            homeBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity())
                            .load(path)
                            .into(imageView);
                }
            });
            homeBanner.setBannerTitles(Arrays.asList(text));
            homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            homeBanner.setBannerAnimation(Transformer.DepthPage);
            homeBanner.start();
        }
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
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onChange() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unRegisterDataChangeListener(this);
    }
}
