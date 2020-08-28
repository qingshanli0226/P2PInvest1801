package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.FinanceConstant;
import com.example.framework.base.BaseFragment;
import com.example.common.CacheManager;
import com.example.framework.base.manager.UserManager;
import com.example.framework.base.service.FinanceService;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.ColumnarActivity;
import com.p2p.bawei.p2pinvest1801.activity.MyInvestActivity;
import com.p2p.bawei.p2pinvest1801.activity.SectorActivity;
import com.p2p.bawei.p2pinvest1801.activity.UserMessageActivity;
import com.p2p.bawei.p2pinvest1801.ui.MyLineChartDemoView;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView meUserImg;
    private TextView meName;

    private boolean isLogin;
    private String imgAddress;
    private FinanceService.FinanceBinder binder;

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
            //投资管理  折现图
            case R.id.meInvest:
                lunachActivity(MyInvestActivity.class,null);
                break;
                //资产管理  扇形图
            case R.id.meAsset:
                lunachActivity(SectorActivity.class,null);
                break;
                //投资管理 直观 柱状图
            case R.id.meInvestManager:
                lunachActivity(ColumnarActivity.class,null);
                break;
        }
    }

    @Override
    protected void initView() {
        binder = UserManager.getInstance().getBinder();
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();
        meUserImg = (ImageView) findViewById(R.id.meUserImg);
        meName = (TextView) findViewById(R.id.meName);
        findViewById(R.id.meInvest).setOnClickListener(this);
        findViewById(R.id.meAsset).setOnClickListener(this);
        findViewById(R.id.meInvestManager).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取登录的状态
        synchronized (this){
            isLogin = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        }
        if(isLogin){
            //登录过 改变ui
            meUserImg.setImageResource(R.drawable.my_user_bg_icon);
            synchronized (this){
                meName.setText(sharedPreferences.getString(FinanceConstant.NAME,""));
                imgAddress = sharedPreferences.getString(FinanceConstant.IMG_ADDRESS, "");
            }

            Bitmap bitmap = binder.getFinanceService().samplePicPath(meUserImg.getWidth(), meUserImg.getHeight(), imgAddress);
            meUserImg.setImageBitmap(bitmap);
//            Glide.with(getContext()).load(address).into(meUserImg);

        } else{
            //未登录 改变ui
            meUserImg.setImageResource(R.drawable.my_user_default);
            meName.setText("Hi,welcome!");

        }
    }


    @Override
    public void onRightClick() {
        super.onRightClick();
        //点击跳转到用户信息页面
        lunachActivity(UserMessageActivity.class,null);
    }


}
