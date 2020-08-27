package com.p2p.bawei.p2pinvest1801.more.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.framework.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.more.activity.GuiGuInvestActivity;
import com.p2p.bawei.p2pinvest1801.more.activity.RegisterActivity;
import com.p2p.bawei.p2pinvest1801.more.activity.TogglemoreActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener, UMShareListener {
    private ToggleButton toggleMore;
    private boolean isChecked = false;
    private SharedPreferences box_isChecked;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("更多");
        tvTitle = findViewById(R.id.tv_title);
        TextView tvMoreRegist = findViewById(R.id.tv_more_regist);
        toggleMore = findViewById(R.id.toggle_more);
        TextView tvMoreReset = findViewById(R.id.tv_more_reset);
        RelativeLayout rlMoreContact = findViewById(R.id.rl_more_contact);
        TextView tvMorePhone = findViewById(R.id.tv_more_phone);
        TextView tvMoreFankui = findViewById(R.id.tv_more_fankui);
        TextView tvMoreShare = findViewById(R.id.tv_more_share);
        TextView tvMoreAbout = findViewById(R.id.tv_more_about);

        tvMoreRegist.setOnClickListener(this);
        toggleMore.setOnClickListener(this);
        tvMoreReset.setOnClickListener(this);
        rlMoreContact.setOnClickListener(this);
        tvMorePhone.setOnClickListener(this);
        tvMoreFankui.setOnClickListener(this);
        tvMoreShare.setOnClickListener(this);
        tvMoreAbout.setOnClickListener(this);

        box_isChecked = CacheManager.getCacheManager().getSharedPreferences();

        isChecked = box_isChecked.getBoolean(InvestConstant.SP_ISCHECKED, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_more_regist:
                //用户注册
                regist();
                break;
            case R.id.toggle_more:
                //设置手势密码
                togglemore();
                break;
            case R.id.tv_more_reset:
                //重置密码
                reset();
                break;
            case R.id.rl_more_contact:
                //联系客服
                contact();
                break;
            case R.id.tv_more_fankui:
                //用户反馈
                fankui();
                break;
            case R.id.tv_more_share:
                //社会化分享
                share();
                break;
            case R.id.tv_more_about:
                launchActivity(GuiGuInvestActivity.class, new Bundle());
                break;

        }
    }

    private void share() {
        new ShareAction(getActivity())
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.DINGTALK,
                        SHARE_MEDIA.DOUBAN, SHARE_MEDIA.DROPBOX, SHARE_MEDIA.EMAIL, SHARE_MEDIA.EVERNOTE,
                        SHARE_MEDIA.EVERNOTE, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.FACEBOOK_MESSAGER, SHARE_MEDIA.FLICKR,
                        SHARE_MEDIA.FOURSQUARE, SHARE_MEDIA.YNOTE, SHARE_MEDIA.TUMBLR, SHARE_MEDIA.MORE)
                .setCallback(this).open();
    }

    private void reset() {
        //重置手势密码
        Toast.makeText(getActivity(), "重置手势密码", Toast.LENGTH_SHORT).show();
        toggleMore.setChecked(false);
        checkout();
    }

    private void togglemore() {
        //创建构造者
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        //添加对话框内容
        builder1.setIcon(R.drawable.ic_head);
        builder1.setTitle("设置手势密码");
        builder1.setMessage("是否现在设置手势密码");
        builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //设置手势密码
                toggleMore.setChecked(isChecked = true);

                //跳转进入手势密码页面
                launchActivity(TogglemoreActivity.class, new Bundle());
            }
        });
        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "取消设置手势密码", Toast.LENGTH_SHORT).show();
                toggleMore.setChecked(false);
            }
        });
        //创建对话框
        AlertDialog alertDialog = builder1.create();
        //显示对话框
        alertDialog.show();
    }

    private void checkout() {
        SharedPreferences.Editor editor = CacheManager.getCacheManager().getEditor();
        editor.putBoolean(InvestConstant.SP_ISCHECKED,false);
        editor.commit();
    }

    private void regist() {
        launchActivity(RegisterActivity.class, new Bundle());
    }

    private void contact() {
        //创建构造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //添加对话框内容
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("联系客服");
        builder.setMessage("是否现在联系客服：010-56253825");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //调用URL打电话
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "010-56253825"));//数据电话
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //创建对话框
        AlertDialog alertDialog = builder.create();
        //显示对话框
        alertDialog.show();
    }

    private void fankui() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.customer_dialog, null);
        RadioButton cbFankuiTech = view1.findViewById(R.id.cb_fankui_tech);
        RadioButton cbFankuiInvest = view1.findViewById(R.id.cb_fankui_invest);
        RadioButton cbFankuiZixun = view1.findViewById(R.id.cb_fankui_zixun);
        EditText etFankuiContent = view1.findViewById(R.id.et_fankui_content);
        Button btnOk = view1.findViewById(R.id.btn_ok);
        Button btnNo = view1.findViewById(R.id.btn_no);

        //将视图插入到对话框中
        builder.setView(view1);

        final AlertDialog alertDialog = builder.create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                Toast.makeText(getActivity(), "发送反馈信息成功！", Toast.LENGTH_SHORT).show();
                printLog("隐藏");
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                printLog("隐藏");
            }
        });
        alertDialog.show();
        printLog("展示");
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        showMessage("开始分享");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        showMessage("分享成功！");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        showMessage("分享失败");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        showMessage("取消分享");
    }

    @Override
    public void onStart() {
        super.onStart();
        toggleMore.setChecked(isChecked);
    }
}
