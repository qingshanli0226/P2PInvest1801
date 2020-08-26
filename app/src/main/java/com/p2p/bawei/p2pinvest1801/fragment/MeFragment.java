package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseFragment;
import com.example.common.CacheManager;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.MyInvestActivity;
import com.p2p.bawei.p2pinvest1801.activity.UserMessageActivity;
import com.p2p.bawei.p2pinvest1801.ui.MyLineChartDemoView;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView meUserImg;
    private TextView meName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //投资管理
            case R.id.meInvest:
                lunachActivity(MyInvestActivity.class,null);
                break;
        }
    }

    @Override
    protected void initView() {
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();
        meUserImg = (ImageView) findViewById(R.id.meUserImg);
        meName = (TextView) findViewById(R.id.meName);
        findViewById(R.id.meInvest).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取登录的状态
        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        if(aBoolean){
            //登录过 改变ui
            meUserImg.setImageResource(R.drawable.my_user_bg_icon);
            meName.setText(sharedPreferences.getString(FinanceConstant.NAME,""));

            String address = sharedPreferences.getString(FinanceConstant.IMG_ADDRESS, "");
            Bitmap bitmap = samplePicPath(meUserImg.getWidth(), meUserImg.getHeight(), address);
            meUserImg.setImageBitmap(bitmap);

        } else{
            //未登录 改变ui
            meUserImg.setImageResource(R.drawable.my_user_default);
            meName.setText("Hi,welcome!");

        }
    }

    //通过地址的二次采样
    private Bitmap samplePicPath(int width, int height, String filePath) {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeFile(filePath,options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>height || picWidth/sampleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


        return BitmapFactory.decodeFile(filePath, options);

    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        //点击跳转到用户信息页面
        lunachActivity(UserMessageActivity.class,null);
    }


}
