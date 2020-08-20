package com.p2p.bawei.p2pinvest1801.user.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.view.MainActivity;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivUserIcon;
    private TextView tvUserChange;
    private Button btnUserLogout;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ImageView ivTitleBack = findViewById(R.id.iv_title_back);
        TextView tvTitle = findViewById(R.id.tv_title);

        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("用户信息");

        ivUserIcon = findViewById(R.id.iv_user_icon);
        tvUserChange = findViewById(R.id.tv_user_change);
        btnUserLogout = findViewById(R.id.btn_user_logout);

        ivTitleBack.setOnClickListener(this);
        btnUserLogout.setOnClickListener(this);
        ivUserIcon.setOnClickListener(this);
        tvUserChange.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                //退出当前页面
                this.removeCurrentActivity();
                break;
            case R.id.btn_user_logout:
                //退出登录
                SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                showMessage("退出登录");
                //返回Main
                launchActivity(MainActivity.class, null);
                break;
            case R.id.iv_user_icon:
                changeHeadIcon();
                break;
            case R.id.tv_user_change:
                changeHeadIcon();
                break;
        }
    }

    //更新头像
    private void changeHeadIcon() {
        //打开popwidow选择更新方式
        initPopwindow();
    }

    private void initPopwindow() {
        final PopupWindow popupWindow = new PopupWindow(this);

        View view = LayoutInflater.from(this).inflate(R.layout.updata_head_popwindow, null, false);

        TextView pop_btn_photo = view.findViewById(R.id.pop_btn_photo);
        TextView pop_btn_camera = view.findViewById(R.id.pop_btn_camera);
        TextView pop_btn_back = view.findViewById(R.id.pop_btn_back);


        pop_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);
        //popwindow背景透明
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(android.R.color.transparent));
        //底部弹出
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }



}
