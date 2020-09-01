package com.p2p.bawei.p2pinvest1801.more.mvp.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.MainActivity;
import com.p2p.bawei.p2pinvest1801.more.mvp.contract.AboutFinanceContract;
import com.p2p.bawei.p2pinvest1801.more.mvp.view.activity.AboutFinanceActivity;
import com.p2p.bawei.p2pinvest1801.more.mvp.view.activity.GesturePasswordActivity;
import com.p2p.bawei.p2pinvest1801.user.mvp.view.activity.RegisterActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MoreFragment extends Fragment implements View.OnClickListener {
    private TextView fragmentMoreNotescontact;
    private TextView fragmentMoreUserFeedback;
    private TextView fragmentMoreUserRegister;
    private Switch switchView;
    private TextView fragmentMimeShare;
    private TextView fragmentMoreAboutFinance;



    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }
        switchView = view.findViewById(R.id.switch_view);
        fragmentMoreUserRegister = view.findViewById(R.id.fragment_more_user_register);
        fragmentMoreNotescontact = view.findViewById(R.id.fragment_more_notescontact);
        fragmentMoreUserFeedback = view.findViewById(R.id.fragment_more_user_feedback);
        fragmentMoreAboutFinance = view.findViewById(R.id.fragment_more_about_finance);

        fragmentMimeShare = view.findViewById(R.id.fragment_mime_share);

        fragmentMimeShare.setOnClickListener(this);
        fragmentMoreAboutFinance.setOnClickListener(this);
        fragmentMoreUserRegister.setOnClickListener(this);
        fragmentMoreNotescontact.setOnClickListener(this);
        fragmentMoreUserFeedback.setOnClickListener(this);

        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (switchView.isChecked()) {
                    startActivity(new Intent(getContext(), GesturePasswordActivity.class));
                } else {
                    switchView.setChecked(false);
                }
            }
        });



    }

    private void popup_notescontact() {
// 用于PopupWindow的View
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_notescontact, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 680, 280, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAsDropDown(fragmentMoreNotescontact, 120, -50);

        Button popupNotescontactBtnAffirm = contentView.findViewById(R.id.popup_notescontact_btn_affirm);
        Button popupNotescontactBtnCancel = contentView.findViewById(R.id.popup_notescontact_btn_cancel);

        popupNotescontactBtnAffirm.setOnClickListener(this);
        popupNotescontactBtnCancel.setOnClickListener(this);

    }

    private void popup_user_feedback() {
// 用于PopupWindow的View
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_user_feedback, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 680, 350, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAsDropDown(fragmentMoreNotescontact, 120, -50);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_more_user_register:
                startActivity(new Intent(getContext(), RegisterActivity.class));
                break;
            case R.id.fragment_more_notescontact:
                popup_notescontact();
                break;
            case R.id.fragment_more_user_feedback:
                popup_user_feedback();
                break;
            case R.id.popup_notescontact_btn_affirm:
                Toast.makeText(getContext(), "确认", Toast.LENGTH_SHORT).show();
                break;
            case R.id.popup_notescontact_btn_cancel:
                Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_mime_share:
                new ShareAction(getActivity()).withText("hello").setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
            case R.id.fragment_more_about_finance:
                startActivity(new Intent(getContext(), AboutFinanceActivity.class));
                break;
        }

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), "取消 了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }



}
