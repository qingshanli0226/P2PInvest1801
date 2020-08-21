package com.p2p.bawei.p2pinvest1801.fragment.fragmentfour;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.p2p.bawei.p2pinvest1801.activity.registerfragment.RegisterActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

public class FragmentFour extends BaseFragment{
    private TextView tvToRegister;
    private RelativeLayout toLxKf;
    private TextView tvToYhfk;
    private TextView tvToShare;
    private TextView tvToAbout;
    private boolean isClicked = false;
    private ImageView moreToggle;

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_four;
    }

    @Override
    public void initView() {
        tvToRegister = (TextView) findViewById(R.id.tv_to_register);
        toLxKf = (RelativeLayout) findViewById(R.id.to_lx_kf);
        tvToYhfk = (TextView) findViewById(R.id.tv_to_yhfk);
        tvToShare = (TextView) findViewById(R.id.tv_to_share);
        tvToAbout = (TextView) findViewById(R.id.tv_to_about);
        moreToggle = (ImageView) findViewById(R.id.more_toggle);
    }

    @Override
    public void initData() {
        moreToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked){
                    Glide.with(getContext()).load(R.drawable.toggle_off).into(moreToggle);
                    isClicked = false;
                }else {
                    Glide.with(getContext()).load(R.drawable.toggle_on).into(moreToggle);
                    isClicked =true;
                }
            }
        });

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }

}
