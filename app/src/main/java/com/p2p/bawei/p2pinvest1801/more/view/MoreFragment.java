package com.p2p.bawei.p2pinvest1801.more.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.framework.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.more.activity.GuiGuInvestActivity;
import com.p2p.bawei.p2pinvest1801.more.activity.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvTitle;
    private ImageView ivTitleBack;
    private ImageView ivTitleSetting;
    private TextView tvMoreRegist;
    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private RelativeLayout rlMoreContact;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;

    private RadioButton cbFankuiTech;
    private RadioButton cbFankuiInvest;
    private RadioButton cbFankuiZixun;
    private EditText etFankuiContent;
    private Button btnOk;
    private Button btnNo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("更多");
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        tvMoreRegist = findViewById(R.id.tv_more_regist);
        toggleMore = findViewById(R.id.toggle_more);
        tvMoreReset = findViewById(R.id.tv_more_reset);
        rlMoreContact = findViewById(R.id.rl_more_contact);
        tvMorePhone = findViewById(R.id.tv_more_phone);
        tvMoreFankui = findViewById(R.id.tv_more_fankui);
        tvMoreShare = findViewById(R.id.tv_more_share);
        tvMoreAbout = findViewById(R.id.tv_more_about);

        tvMoreRegist.setOnClickListener(this);
        toggleMore.setOnClickListener(this);
        tvMoreReset.setOnClickListener(this);
        rlMoreContact.setOnClickListener(this);
        tvMorePhone.setOnClickListener(this);
        tvMoreFankui.setOnClickListener(this);
        tvMoreShare.setOnClickListener(this);
        tvMoreAbout.setOnClickListener(this);

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
                break;
            case R.id.tv_more_about:
                launchActivity(GuiGuInvestActivity.class, new Bundle());
                break;
        }
    }

    private void reset() {
        //重置手势密码
        Toast.makeText(getActivity(), "重置手势密码", Toast.LENGTH_SHORT).show();
        toggleMore.setChecked(false);
    }

    private void togglemore() {
        //创建构造者
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //添加对话框内容
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("设置手势密码");
        builder.setMessage("是否现在设置手势密码");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //设置手势密码
                toggleMore.setChecked(true);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "取消设置手势密码", Toast.LENGTH_SHORT).show();
                toggleMore.setChecked(false);
            }
        });
        //创建对话框
        AlertDialog alertDialog = builder.create();
        //显示对话框
        alertDialog.show();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.customer_dialog, null);
        cbFankuiTech = view.findViewById(R.id.cb_fankui_tech);
        cbFankuiInvest = view.findViewById(R.id.cb_fankui_invest);
        cbFankuiZixun = view.findViewById(R.id.cb_fankui_zixun);
        etFankuiContent = view.findViewById(R.id.et_fankui_content);
        btnOk = view.findViewById(R.id.btn_ok);
        btnNo = view.findViewById(R.id.btn_no);

        //将视图插入到对话框中
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                Toast.makeText(getActivity(), "发送反馈信息成功！", Toast.LENGTH_SHORT).show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }
}
