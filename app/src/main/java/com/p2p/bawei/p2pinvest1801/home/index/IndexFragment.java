package com.p2p.bawei.p2pinvest1801.home.index;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.framework2.manager.CacheManager;
import com.example.net.activity_bean.IndexBean;
import com.p2p.bawei.p2pinvest1801.myview.ProgressView;
import com.p2p.bawei.p2pinvest1801.R;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {
    private Banner banner;
    private View inflate;
    private List<String> url = new ArrayList<>();
    private IndexBean indexBean;
    private ProgressView index_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_index, container, false);
        banner = inflate.findViewById(R.id.index_banner);
        index_progress=inflate.findViewById(R.id.index_progress);
        index_progress.setProgress(Integer.parseInt(CacheManager.getInstance().getIndexBean().getResult().getProInfo().getProgress()));
        initBanner();
        return inflate;
    }

    private void initBanner() {
        url.clear();
        indexBean = CacheManager.getInstance().getIndexBean();
        List<IndexBean.ResultBean.ImageArrBean> imageArr = indexBean.getResult().getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            url.add(imageArr.get(i).getIMAURL());
        }
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setImages(url);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                intent.putExtra("url",indexBean.getResult().getImageArr().get(position).getIMAPAURL());
                startActivity(intent);
            }
        });
    }

}
