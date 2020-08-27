package com.p2p.bawei.p2pinvest1801.user.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView ivTitleBack;
    private TextView tvTitle;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        photoview = findViewById(R.id.photoview);
        photoViewAttacher = new PhotoViewAttacher(photoview);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("头像");

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
