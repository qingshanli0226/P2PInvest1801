package com.p2p.bawei.p2pinvest1801.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.framework.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.main.view.MainActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    //定义常量
    public static final int REQUEST_PHOTO = 108;
    public static final int REQUEST_CAMERA = 102;

    private PopupWindow popupWindow;
    private SharedPreferences iconSp;

    private ImageView ivUserIcon;
    private TextView tvUserChange;
    private Button btnUserLogout;

    private String path;

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
                removeCurrentActivity();
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
        popupWindow = new PopupWindow(this);

        View view = LayoutInflater.from(this).inflate(R.layout.updata_head_popwindow, null, false);

        TextView pop_btn_photo = view.findViewById(R.id.pop_btn_photo);
        TextView pop_btn_camera = view.findViewById(R.id.pop_btn_camera);
        TextView pop_btn_back = view.findViewById(R.id.pop_btn_back);

        //调用照相机
        pop_btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
            }
        });

        //调用图库
        pop_btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo();
            }
        });


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

    private void photo() {
        printLog("==photo==");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTO);
        popupWindow.dismiss();
    }

    private void camera() {
        path = getName();
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//相机
        //设置图片保存的路径
        Uri uri = FileProvider.getUriForFile(this, "com.p2p.bawei.p2pinvest1801", new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
        popupWindow.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode 请求码  resultCode 结果码
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK) {
            Uri data1 = data.getData();//图库选择
            String s = String.valueOf(getRealPathFromUri(this, data1));
//            ivUserIcon.setImageURI(data1);
            //TODO:做二次采样
            Glide.with(this)
                    .load(s)
                    .transform(new CircleCrop())
                    .transform(new CenterInside())
                    .into(ivUserIcon);

            printLog("图库设置头像---" + s);
            saveIcon(s);
        } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            Glide.with(this)
                    .load(BitmapFactory.decodeFile(path))
                    .transform(new CircleCrop())
                    .transform(new CenterInside())
                    .into(ivUserIcon);
            printLog("照相机设置头像---" + path);
            saveIcon(path);
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //根据当前时间生成图片的名称
    public String getName() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String format1 = format.format(date);
        return "/sdcard/DCIM/IMG_" + format1 + ".jpg";
    }

    private void saveIcon(String path) {
        //保存图片
        iconSp = getSharedPreferences("headIcon", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = iconSp.edit();
        edit.clear();
        edit.putString("icon", path);
        edit.commit();
        printLog("保存头像---" + path);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //更新头像
        SharedPreferences headIcon = getSharedPreferences("headIcon", Context.MODE_PRIVATE);
        String icon = headIcon.getString("icon", "");
        Glide.with(this)
                .load(icon)
                .transform(new CircleCrop())
                .into(ivUserIcon);
        printLog("更新头像---" + icon);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popupWindow = null;
    }
}
