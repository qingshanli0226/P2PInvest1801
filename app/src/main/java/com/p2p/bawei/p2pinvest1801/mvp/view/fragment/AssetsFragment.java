package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

public class AssetsFragment extends BaseFragment {
    private Button mainAssetsRecharge;
    private Button mainAssetsWithdrawDeposit;
    private Button assetsPopTopUpBtn;
    private ImageView userImg;
    private TextView assetsWithdrawDepositPopBalance;
    private int sum = 0;
    @Override
    public void initView() {
        //弹出登录窗口
        Login();


        //点击换图片
        userImg = (ImageView) findViewById(R.id.user_img);
        //充值
        mainAssetsRecharge = (Button) findViewById(R.id.main_assets_recharge);
        //提现
        mainAssetsWithdrawDeposit = (Button) findViewById(R.id.main_assets_withdraw_deposit);
        mainAssetsRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    setTranslucentStatus(true);
                    FragmentActivity activity = getActivity();
                    if (activity!=null){
                        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                        tintManager.setStatusBarTintEnabled(true);
                        tintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
                    }

                }
                final PopupWindow popupWindow = new PopupWindow();
                View view= LayoutInflater.from(getContext()).inflate(R.layout.assets_pop_layout, null);
                ImageView assetsPopBack = view.findViewById(R.id.assets_pop_back);
                assetsPopBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            setTranslucentStatus(true);
                            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
                            tintManager.setStatusBarTintEnabled(true);
                            tintManager.setStatusBarTintResource(R.color.colorTransparent);//通知栏所需颜色
                        }
                    }
                });
                EditText assetsPopMoney = view.findViewById(R.id.assets_pop_money);
                TextView assetsPopBalance = view.findViewById(R.id.assets_pop_balance);
                String text = String.valueOf(assetsPopMoney.getText());
                assetsPopBalance.setText(text);
                assetsPopTopUpBtn = view.findViewById(R.id.assets_pop_top_up_btn);
                assetsPopMoney.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length()>0){
                            assetsPopTopUpBtn.setEnabled(true);
                            assetsPopTopUpBtn.setBackgroundResource(R.drawable.btn_01);
                        }else {
                            assetsPopTopUpBtn.setEnabled(false);
                            assetsPopTopUpBtn.setBackgroundResource(R.drawable.btn_02);
                        }
                    }
                });
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow .setBackgroundDrawable(null);
                popupWindow .setFocusable(true);//解决点击view重新打开popupWindow的问题
                popupWindow .setOutsideTouchable(true);//点击外部popupWindow消失
                popupWindow.showAtLocation(mainAssetsRecharge, Gravity.BOTTOM, 0, 0);
            }
        });
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(AssetsFragment.this,0).selectPicture(true);
            }
        });
        mainAssetsWithdrawDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow();
                View view= LayoutInflater.from(getContext()).inflate(R.layout.assets_pop_withdraw_deposit_layout, null);
                ImageView assetsWithdrawDepositPopBack = view.findViewById(R.id.assets_withdraw_deposit__pop_back);
                EditText assetsWithdrawDepositPopMoney = view.findViewById(R.id.assets_withdraw_deposit__pop_money);
                assetsWithdrawDepositPopBalance = view.findViewById(R.id.assets_withdraw_deposit__pop_balance);
                Button assetsWithdrawDepositPopTopUpBtn = view.findViewById(R.id.assets_withdraw_deposit__pop_top_up_btn);

                WithdrawDepositOnclick(popupWindow,assetsWithdrawDepositPopBack,assetsWithdrawDepositPopMoney,assetsWithdrawDepositPopBalance,assetsWithdrawDepositPopTopUpBtn);

                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow .setBackgroundDrawable(null);
                popupWindow .setFocusable(true);//解决点击view重新打开popupWindow的问题
                popupWindow .setOutsideTouchable(true);//点击外部popupWindow消失
                popupWindow.showAtLocation(mainAssetsRecharge, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    //登录popupWindow
    private void Login() {
        PopupWindow popupWindow = new PopupWindow();


    }

    private void WithdrawDepositOnclick(final PopupWindow popupWindow, ImageView assetsWithdrawDepositPopBack, final EditText assetsWithdrawDepositPopMoney, TextView assetsWithdrawDepositPopBalance, final Button assetsWithdrawDepositPopTopUpBtn){
        assetsWithdrawDepositPopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        assetsWithdrawDepositPopTopUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.valueOf(assetsWithdrawDepositPopMoney.getText());
                Message message = new Message();
                message.what = 1;
                message.obj = text;
                handler.sendMessage(message);
            }
        });
        assetsWithdrawDepositPopMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    assetsWithdrawDepositPopTopUpBtn.setEnabled(true);
                    assetsWithdrawDepositPopTopUpBtn.setBackgroundResource(R.drawable.btn_01);
                }else {
                    assetsWithdrawDepositPopTopUpBtn.setEnabled(false);
                    assetsWithdrawDepositPopTopUpBtn.setBackgroundResource(R.drawable.btn_02);
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                String a = (String) msg.obj;
                int i = Integer.parseInt(a);
                sum+=i;
                assetsWithdrawDepositPopBalance.setText(sum+"元");
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == 0) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    Context context = getContext();
                    if (context!=null){
                        Glide.with(context).load(pictureBean.getPath()).transform(new CircleCrop()).into(userImg);
                    }

                }
            }
        }
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
