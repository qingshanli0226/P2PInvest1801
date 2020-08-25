package com.p2p.bawei.p2pinvest1801.user.view;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.framework.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user.activity.UserInfoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {
    private ImageView ivMeIcon;
    private TextView tvMeName;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        ImageView ivTitleSetting = findViewById(R.id.iv_title_setting);
        //登录的用户信息
        ivMeIcon = findViewById(R.id.iv_me_icon);
        tvMeName = findViewById(R.id.tv_me_name);

        //按钮
        recharge = findViewById(R.id.recharge);
        withdraw = findViewById(R.id.withdraw);
        llTouzi = findViewById(R.id.ll_touzi);
        llTouziZhiguan = findViewById(R.id.ll_touzi_zhiguan);
        llZichan = findViewById(R.id.ll_zichan);

        ivTitleSetting.setOnClickListener(this);
        ivMeIcon.setOnClickListener(this);
        recharge.setOnClickListener(this);
        withdraw.setOnClickListener(this);
        llTouzi.setOnClickListener(this);
        llTouziZhiguan.setOnClickListener(this);
        llZichan.setOnClickListener(this);

        //标题
        tvTitle.setText("我的资产");
        ivTitleSetting.setVisibility(View.VISIBLE);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_setting:
                //跳转用户信息页面
                launchActivity(UserInfoActivity.class, new Bundle());
                break;
            case R.id.iv_me_icon:
                //跳转用户信息页面
                launchActivity(UserInfoActivity.class, new Bundle());
                break;
            case R.id.recharge:
                break;
            case R.id.withdraw:
                break;
            case R.id.ll_touzi:
                break;
            case R.id.ll_touzi_zhiguan:
                break;
            case R.id.ll_zichan:
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        printLog("更新用户信息");
        SharedPreferences sharedPreferences = CacheManager.getCacheManager().getSharedPreferences();
        String name = sharedPreferences.getString(InvestConstant.SP_USERNAME, "未登录");
        tvMeName.setText(name);

        String icon = sharedPreferences.getString(InvestConstant.SP_ICON, "");
        if (icon != null) {
            //更新头像
            Glide.with(getContext())
                    .load(icon)
                    .transform(new CircleCrop())
                    .into(ivMeIcon);
        }
    }
}
