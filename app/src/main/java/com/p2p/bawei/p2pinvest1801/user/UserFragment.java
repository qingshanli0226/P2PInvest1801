package com.p2p.bawei.p2pinvest1801.user;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baselibrary.mvp.view.BaseFragment;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.Picture;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.YuanAct;
import com.p2p.bawei.p2pinvest1801.ZhuAct;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

@Route(path = ARouterCode.USER_FRAGMENT)
public class UserFragment extends BaseFragment {
    private ImageView mHeadPic;
    private boolean flag = false;
    private boolean flag2 = false;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.user_head_pic) {

            if (!flag) {
                PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                flag = true;
            } else {
                if (!flag2) {
                    Bitmap bitmap = samplePic(100, 100);
                    mHeadPic.setImageBitmap(bitmap);
                    flag2 = true;
                } else {
                    Intent intent = new Intent(getContext(), Picture.class);
                    startActivity(intent);
                }

            }
        }
        if (view.getId() == R.id.zhu) {
            Intent intent = new Intent(getContext(), ZhuAct.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.yuan) {
            Intent intent = new Intent(getContext(), YuanAct.class);
            startActivity(intent);
        }
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(getContext());

        findViewById(R.id.zhu).setOnClickListener(this);
        findViewById(R.id.yuan).setOnClickListener(this);

        mHeadPic = (ImageView) findViewById(R.id.user_head_pic);
        mHeadPic.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    private Bitmap samplePic(int width, int height) {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeResource(getResources(), R.mipmap.a, options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight / sampleSize > height || picWidth / sampleSize > width) {
            sampleSize = sampleSize * 2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


        return BitmapFactory.decodeResource(getResources(), R.mipmap.a, options);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.user_layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    mHeadPic.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    mHeadPic.setImageURI(pictureBean.getUri());
                }
                Log.e("hq", "onActivityResult: " + pictureBean.getPath());
                //使用 Glide 加载图片
                Glide.with(this)
                        .load(pictureBean.getPath())
                        .into(mHeadPic);
            }
        }
    }
}
