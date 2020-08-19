package com.p2p.bawei.p2pinvest1801.fragmentfour;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

public class FragmentFour extends BaseFragment {
    private boolean isClicked = false;
    private ImageView moreToggle;

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_four;
    }

    @Override
    public void initView() {
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
