package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.lib_core.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.RegisterModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.RegisterPresenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;



public class MoreFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View ,View.OnClickListener {
    private LinearLayout moreRegister;
    private PatternLockView mPatternLockView;
    private RelativeLayout moreSecret;
    private Switch moreToggle;
    private LinearLayout moreReset;
    private RelativeLayout moreContact;
    private LinearLayout moreSms;
    private LinearLayout moreShear;
    private LinearLayout moreAbout;
    private PopupWindow registerPopupWindow;
    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            String patternToString = PatternLockUtils.patternToString(mPatternLockView, pattern);
            String mima = "24678";

            if(!TextUtils.isEmpty(patternToString)){
                if(patternToString.equals(mima)){
                    Toast.makeText(getContext(),"您绘制的密码是："+patternToString+"\n"+"密码正确，正在进入首页...",Toast.LENGTH_SHORT).show();
                    pattern.clear();
                }else {
                    Toast.makeText(getContext(),"您绘制的密码是："+patternToString+"\n"+"密码错误，请重新绘制",Toast.LENGTH_SHORT).show();
                    pattern.clear();
                }
            }

        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    public void initView() {
        moreRegister = (LinearLayout) findViewById(R.id.more_register);
        moreSecret = (RelativeLayout) findViewById(R.id.more_secret);
        moreToggle = (Switch) findViewById(R.id.more_toggle);
        moreReset = (LinearLayout) findViewById(R.id.more_reset);
        moreContact = (RelativeLayout) findViewById(R.id.more_contact);
        moreSms = (LinearLayout) findViewById(R.id.more_sms);
        moreShear = (LinearLayout) findViewById(R.id.more_shear);
        moreAbout = (LinearLayout) findViewById(R.id.more_about);

        moreRegister.setOnClickListener(this);
        moreSecret.setOnClickListener(this);
        moreToggle.setOnClickListener(this);
        moreReset.setOnClickListener(this);
        moreContact.setOnClickListener(this);
        moreSms.setOnClickListener(this);
        moreAbout.setOnClickListener(this);
        moreShear.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.main_more_layout_fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more_register:
                //注册
                register();
                break;
            case R.id.more_secret:
                //手势密码
            case R.id.more_toggle:
                //手势密码图片
                toggle();
                break;
            case R.id.more_reset:
                //重置手势密码
                break;
            case R.id.more_contact:
                //联系客服
                contact();
                break;
            case R.id.more_sms:
                //用户反馈
                sms();
                break;
            case R.id.more_shear:
                //分享给好友
                new ShareAction(getActivity()).withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
                break;
            case R.id.more_about:
                //关于硅谷理财
                about();
                break;
        }
    }

    //手势密码
    private void secret() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.more_pop_secret, null);
        PopupWindow popupWindow = new PopupWindow();
        mPatternLockView = inflate.findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);
        popupWindow.setFocusable(true);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(inflate);
        popupWindow.showAsDropDown(moreRegister, Gravity.BOTTOM, 0, 0);

    }

    //关于硅谷理财
    private void about() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.more_pop_about, null);
        ImageView morePopBack = inflate.findViewById(R.id.more_pop_back);
        final PopupWindow popupWindow = new PopupWindow();
        morePopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(inflate);
        popupWindow.showAsDropDown(moreRegister, Gravity.BOTTOM, 0, 0);
    }

    //用户反馈
    private void sms() {
        View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.more_pop_sms, null);
        PopupWindow popupWindow1 = new PopupWindow();
        Button smsConfirmBtn = inflate1.findViewById(R.id.sms_confirm_btn);
        Button smsCancelBtn = inflate1.findViewById(R.id.sms_cancel_btn);
        smsOnClick(popupWindow1,smsConfirmBtn,smsCancelBtn);
        popupWindow1.setWidth(450);
        popupWindow1.setHeight(350);
        popupWindow1.setContentView(inflate1);
        popupWindow1 .setFocusable(true);//解决点击view重新打开popupWindow的问题
        popupWindow1 .setOutsideTouchable(true);//点击外部popupWindow消失
        popupWindow1.showAsDropDown(inflate1, 350,700 , 0);
    }
    //用户反馈点击事件
    private void smsOnClick(final PopupWindow popupWindow1, Button smsConfirmBtn, Button smsCancelBtn){
        smsConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
                Toast.makeText(getContext(), "已进行反馈", Toast.LENGTH_SHORT).show();
            }
        });
        smsCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
    }

    //联系客服
    private void contact() {
        View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.more_pop_contact, null);
        PopupWindow popupWindow1 = new PopupWindow();

        Button moreContactPopConfirm = inflate1.findViewById(R.id.more_contact_pop_confirm);
        Button moreContactPopCancel = inflate1.findViewById(R.id.more_contact_pop_cancel);
        contactOnCliock(popupWindow1,moreContactPopConfirm,moreContactPopCancel);
        popupWindow1.setWidth(350);
        popupWindow1.setHeight(350);
        popupWindow1.setContentView(inflate1);
        popupWindow1 .setFocusable(true);//解决点击view重新打开popupWindow的问题
        popupWindow1 .setOutsideTouchable(true);//点击外部popupWindow消失
        popupWindow1.showAsDropDown(inflate1, 350,700 , 0);
    }
    //联系客服点击事件
    private void contactOnCliock(final PopupWindow popupWindow1, Button moreContactPopConfirm, Button moreContactPopCancel) {
        moreContactPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secret();
            }
        });
        moreContactPopCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
    }

    //手势密码图片
    private void toggle() {
        moreToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("aaaaaaaaa", "onCheckedChanged: "+isChecked );
                if (isChecked){
                    contact();
                }
            }
        });
    }

    //重置手势密码
    private void reset() {

    }

    //注册
    private void register(){
        registerPopupWindow = new PopupWindow();
        registerPopupWindow.setFocusable(true);
        //注册
        final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.more_pop_register, null);
        ImageView morePopBack = inflate.findViewById(R.id.more_pop_back);
        final EditText moreRegisterPhone = inflate.findViewById(R.id.more_register_phone);
        final EditText moreRegisterUsername = inflate.findViewById(R.id.more_register_username);
        final EditText moreRegisterPassword = inflate.findViewById(R.id.more_register_password);
        final EditText moreRegisterConfirmPassword = inflate.findViewById(R.id.more_register_confirm_password);
        final Button morePopRegisterBtn = inflate.findViewById(R.id.more_pop_register_btn);

        morePopRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterInflate(moreRegisterPhone,moreRegisterUsername,moreRegisterPassword,moreRegisterConfirmPassword);

            }
        });
        moreRegisterOnclick(registerPopupWindow,morePopBack,moreRegisterPhone,moreRegisterUsername,moreRegisterPassword,moreRegisterConfirmPassword,morePopRegisterBtn);
        registerPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        registerPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        registerPopupWindow.setContentView(inflate);
        registerPopupWindow.showAsDropDown(moreRegister, Gravity.BOTTOM, 0, 0);
    }

    //注册业务
    private void onRegisterInflate(EditText moreRegisterPhone, EditText moreRegisterUsername, EditText moreRegisterPassword, EditText moreRegisterConfirmPassword) {

         String phone = String.valueOf(moreRegisterPhone.getText());//手机号
         String name = String.valueOf(moreRegisterUsername.getText());//用户名
         String pwd = String.valueOf(moreRegisterPassword.getText());//密码
         String rePwd = String.valueOf(moreRegisterConfirmPassword.getText());//再次输入密码
        if (phone.length()<=0 && name.length() <= 0 && pwd.length() <= 0 && rePwd.length() <= 0){
            Toast.makeText(getContext(), "以上条件不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter = new RegisterPresenter(new RegisterModel(),MoreFragment.this);
        mPresenter.register(phone,name,pwd,rePwd);

    }

    //注册页面的点击事件
    private void moreRegisterOnclick(final PopupWindow popupWindow, ImageView morePopBack, EditText moreRegisterPhone, EditText moreRegisterUsername, EditText moreRegisterPassword, EditText moreRegisterConfirmPassword, Button morePopRegisterBtn) {
        morePopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    @Override
    public void success() {
        registerPopupWindow.dismiss();
    }


}
