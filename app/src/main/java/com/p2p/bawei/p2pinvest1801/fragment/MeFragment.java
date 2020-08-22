package com.p2p.bawei.p2pinvest1801.fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseFragment;
import com.p2p.bawei.p2pinvest1801.CacheManager;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.LoginActivity;
import com.p2p.bawei.p2pinvest1801.activity.UserMessageActivity;

public class MeFragment extends BaseFragment {

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
    protected void initView() {
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();
        meUserImg = (ImageView) findViewById(R.id.meUserImg);
        meName = (TextView) findViewById(R.id.meName);
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
