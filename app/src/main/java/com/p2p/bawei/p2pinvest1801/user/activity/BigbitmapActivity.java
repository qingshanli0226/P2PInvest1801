package com.p2p.bawei.p2pinvest1801.user.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.framework.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.p2p.bawei.p2pinvest1801.R;

/**
 * 大图模式
 */
public class BigbitmapActivity extends BaseActivity {
    private PhotoView photoview;
    private PhotoViewAttacher photoViewAttacher;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        photoview = findViewById(R.id.photoview);
        photoViewAttacher = new PhotoViewAttacher(photoview);

        SharedPreferences sharedPreferences = CacheManager.getCacheManager()
                .getSharedPreferences();

        String icon = sharedPreferences.getString(InvestConstant.SP_ICON, "");
        if (!icon.equals("")) {
            Bitmap bmp = BitmapFactory.decodeFile(icon);//filePath
            photoview.setImageBitmap(bmp);
        } else {
            //设置默认图
            photoview.setImageResource(R.drawable.my_user_default);
        }
        photoViewAttacher.update();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bigbitmap;
    }
}
