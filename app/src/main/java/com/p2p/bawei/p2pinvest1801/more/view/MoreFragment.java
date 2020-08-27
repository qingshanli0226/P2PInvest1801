package com.p2p.bawei.p2pinvest1801.more.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.mvp.view.BaseFragment;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.lock.LockAct;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.Objects;

public class MoreFragment extends BaseFragment {
    private TextView mUserRegisterTxt;
    private TextView mHandChangeTxt;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_register_txt:
                ARouter.getInstance().build(ARouterCode.USER_REGISTER_ACT).navigation();
                break;
            case R.id.hand_change_txt:
                startActivity(new Intent(getContext(), LockAct.class));
                break;
            case R.id.count_phone_txt:
                break;
            case R.id.user_request_txt:
                Log.e("hq", "onClick: ");
                break;
            case R.id.share_fired_txt:
                Log.e("hq", "onClick: " + 1);

                break;
            case R.id.guiGu_about_txt:
                Log.e("hq", "onClick: " + 2);

                break;
        }
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(getContext());
        findViewById(R.id.user_register_txt).setOnClickListener(this);
        Switch mHandLockSwc = (Switch) findViewById(R.id.hand_lock_swc);
        findViewById(R.id.hand_change_txt).setOnClickListener(this);
        findViewById(R.id.count_phone_txt);
        findViewById(R.id.user_request_txt);
        findViewById(R.id.share_fired_txt);
        findViewById(R.id.guiGu_about_txt);
        final SharedPreferences lock = Objects.requireNonNull(getContext()).getSharedPreferences("lock", Context.MODE_PRIVATE);

        mHandLockSwc.setChecked(lock.getBoolean("lockFlag", false));
        mHandLockSwc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String lock_str = lock.getString("lock_str", null);
                if (lock_str == null) {
                    startActivity(new Intent(getContext(), LockAct.class));
                }
                SharedPreferences.Editor edit = lock.edit();
                edit.putBoolean("lockFlag", b);
                edit.apply();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.more_layout;
    }
}
