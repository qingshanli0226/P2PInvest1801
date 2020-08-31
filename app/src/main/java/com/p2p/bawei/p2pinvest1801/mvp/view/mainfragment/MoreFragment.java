package com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.MainActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.PatternActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.user.RegisterActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MoreFragment extends BaseFragment implements UMShareListener {
    private ImageView morePassword;
    boolean flag = true;
    private LinearLayout moreRegister;
    private LinearLayout moreshares;
    @Override
    public void initViews() {
        morePassword = (ImageView) findViewById(R.id.more_password);
        moreRegister = (LinearLayout) findViewById(R.id.more_register);
        moreshares = (LinearLayout) findViewById(R.id.moreshares);
    }

    @Override
    public void initDatas() {
        //手势密码切换
        morePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    morePassword.setImageResource(R.drawable.toggle_on);
                    initPatter();
                    flag = false;
                }else{
                    morePassword.setImageResource(R.drawable.toggle_off);
                    flag = true;
                }
            }
        });
        moreRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        moreshares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity()).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QZONE)
                        .setCallback(MoreFragment.this)
                        .open();
            }
        });
    }

    private void initPatter() {
       launchActivity(PatternActivity.class, new Bundle());
    }

    @Override
    public int bandLayout() {
        return R.layout.morefragment;
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Toast.makeText(getContext(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }
}
