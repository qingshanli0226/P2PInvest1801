package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.PatternActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.user.RegisterActivity;

import java.util.regex.Pattern;

public class MoreFragment extends BaseFragment {
    private ImageView morePassword;
    boolean flag = true;
    private LinearLayout moreRegister;
    @Override
    public void initViews() {
        morePassword = (ImageView) findViewById(R.id.more_password);
        moreRegister = (LinearLayout) findViewById(R.id.more_register);
    }

    @Override
    public void initDatas() {
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
    }

    private void initPatter() {
        Intent intent = new Intent(getContext(), PatternActivity.class);
        startActivity(intent);
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

    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }
}
