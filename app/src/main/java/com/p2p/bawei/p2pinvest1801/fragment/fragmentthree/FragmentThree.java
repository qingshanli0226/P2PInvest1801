package com.p2p.bawei.p2pinvest1801.fragment.fragmentthree;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

public class FragmentThree extends BaseFragment {
    private ImageView ivIdF3;
    @Override
    public int banLayout() {
        return R.layout.fragment_layout_three;
    }

    @Override
    public void initView() {
        ivIdF3 = (ImageView) findViewById(R.id.iv_id_f3);
    }

    @Override
    public void initData() {
        Glide.with(this).load(R.drawable.tx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivIdF3);
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
