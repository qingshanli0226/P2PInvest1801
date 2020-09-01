package com.p2p.bawei.p2pinvest1801.mine.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.common.Llog;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity.ColumnChartActivity;
import com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity.LineChartActivity;
import com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity.PieChartActivity;
import com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity.UserInfoActivity;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.view.activity.LoginActivity;
import com.p2p.bawei.p2pinvest1801.user.mvp.view.activity.RegisterActivity;

import lecho.lib.hellocharts.view.ColumnChartView;

public class MineFragment extends Fragment implements View.OnClickListener {

    private LinearLayout fragmentMimeLlLogin;
    private ImageView fragmentMineUserPic;
    private TextView fragmentMineUserName;
    private ImageView fragmentMineUserSetting;
    private RelativeLayout fragmentMimeRl1;
    private RelativeLayout fragmentMimeRl2;
    private RelativeLayout fragmentMimeRl3;


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        SPUtils spUtils = SPUtils.getInstance();
        String name = spUtils.getString("name");
        String avatar = spUtils.getString("avatar");

        Llog.d("onStart: " + name + avatar);
        if (name != null && avatar != null) {
            Glide.with(getActivity())
                    .applyDefaultRequestOptions(new RequestOptions().circleCrop())
                    .load("http://49.233.93.155:8080/atguigu" + avatar)
                    .into(fragmentMineUserPic);
            fragmentMineUserName.setText(name);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentMineUserPic = view.findViewById(R.id.fragment_mine_user_pic);
        fragmentMineUserName = view.findViewById(R.id.fragment_mine_user_name);
        fragmentMimeLlLogin = view.findViewById(R.id.fragment_mime_ll_login);
        fragmentMineUserSetting = view.findViewById(R.id.fragment_mine_user_setting);
        fragmentMimeRl1 = view.findViewById(R.id.fragment_mime_rl1);
        fragmentMimeRl2 = view.findViewById(R.id.fragment_mime_rl2);
        fragmentMimeRl3 = view.findViewById(R.id.fragment_mime_rl3);
        fragmentMimeRl1.setOnClickListener(this);
        fragmentMimeRl2.setOnClickListener(this);
        fragmentMimeRl3.setOnClickListener(this);
        fragmentMimeLlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));

            }
        });

        fragmentMineUserSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UserInfoActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_mime_rl1:
                startActivity(new Intent(getContext(), LineChartActivity.class));
                break;
            case R.id.fragment_mime_rl2:
                startActivity(new Intent(getContext(), ColumnChartActivity.class));
                break;
            case R.id.fragment_mime_rl3:
                startActivity(new Intent(getContext(), PieChartActivity.class));
                break;
        }
    }
}
