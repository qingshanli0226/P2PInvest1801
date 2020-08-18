package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MainActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class AssetsFragment extends BaseFragment {
    private Button mainAssetsRecharge;
    private Button mainAssetsWithdrawDeposit;
    @Override
    public void initView() {
        //充值
        mainAssetsRecharge = (Button) findViewById(R.id.main_assets_recharge);
        //提现
        mainAssetsWithdrawDeposit = (Button) findViewById(R.id.main_assets_withdraw_deposit);

        mainAssetsRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    setTranslucentStatus(true);
                    SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
                    tintManager.setStatusBarTintEnabled(true);
                    tintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
                }
                PopupWindow popupWindow = new PopupWindow();
                View view= LayoutInflater.from(getContext()).inflate(R.layout.assets_pop_layout, null);
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow .setBackgroundDrawable(null);
                popupWindow .setFocusable(true);//解决点击view重新打开popwindow的问题
                popupWindow .setOutsideTouchable(true);//点击外部popupwindow消失
                popupWindow.showAtLocation(mainAssetsRecharge, Gravity.BOTTOM, 0, 0);

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.main_assets_layout_fragment;
    }

    @TargetApi(23)
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
