package com.p2p.bawei.p2pinvest1801.fragment.fragmentthree;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheOneActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheThreeActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheTwoActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

public class FragmentThree extends BaseFragment {
    private ImageView ivIdF3;
    private Button btChongZhi;
    private Button btTiXian;
    private TextView tvFThreeFirst;
    private TextView tvFThreeSec;
    private TextView tvFThreeThe3;

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_three;
    }

    @Override
    public void initView() {

        ivIdF3 = (ImageView) findViewById(R.id.iv_id_f3);
        btChongZhi = (Button) findViewById(R.id.bt_ChongZhi);
        btTiXian = (Button) findViewById(R.id.bt_TiXian);
        tvFThreeFirst = (TextView) findViewById(R.id.tv_FThree_first);
        tvFThreeSec = (TextView) findViewById(R.id.tv_FThree_sec);
        tvFThreeThe3 = (TextView) findViewById(R.id.tv_FThree_the3);

    }

    @Override
    public void initData() {
        Glide.with(this).load(R.drawable.tx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivIdF3);

        tvFThreeFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheOneActivity.class);
                startActivity(intent);
            }
        });

        tvFThreeSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheTwoActivity.class);
                startActivity(intent);
            }
        });

        tvFThreeThe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheThreeActivity.class);
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
