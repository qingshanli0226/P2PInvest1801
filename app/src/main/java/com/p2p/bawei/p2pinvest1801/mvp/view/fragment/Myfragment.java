package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
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
    private TextView jh;
    private TextView lir;
    @Override
    public int BondLayout() {
        return R.layout.myfragment;
    }

    @Override
    public void initview() {
        bann = (Banner) findViewById(R.id.bann);
        pv = (MyPragessagerView) findViewById(R.id.pv);
        jh = (TextView) findViewById(R.id.jh);
        lir = (TextView) findViewById(R.id.lir);
    }

    @Override
    public void initdata() {
        Intent intent = getActivity().getIntent();
        MyBannerEntity myBannerEntity = (MyBannerEntity) intent.getSerializableExtra("banner");
        List<MyBannerEntity.ResultBean.ImageArrBean> imageArr = myBannerEntity.getResult().getImageArr();
        Log.e("list_size", "initdata: "+imageArr.get(0).getIMAPAURL() );
        for (int i = 0; i < imageArr.size(); i++) {
            list_pic.add(imageArr.get(i).getIMAURL());
        }
        jh.setText(myBannerEntity.getResult().getProInfo().getName());
        pv.setProgress(Integer.parseInt(myBannerEntity.getResult().getProInfo().getProgress()) );
        lir.setText("预期年利率:"+myBannerEntity.getResult().getProInfo().getYearRate());
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
    private Bitmap getpic(int height,int width){
        ImageView imageView = new ImageView(getContext());
        int picHeightmpleSize = imageView.getMeasuredHeight();
        int picWidthmpleSize = imageView.getMeasuredWidth();
        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeResource(getResources(), R.mipmap.adr,options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeightmpleSize>height || picWidthmpleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束
        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        return BitmapFactory.decodeResource(getResources(),R.mipmap.adr, options);
    }
    @Override
    public void initInJect() {

    }
}
