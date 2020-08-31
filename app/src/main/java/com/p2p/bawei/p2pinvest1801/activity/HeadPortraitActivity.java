package com.p2p.bawei.p2pinvest1801.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.common.glide.GlideManager;
import com.bw.common.view.ToolBar;
import com.bw.framwork.view.BaseActivity;
import com.bw.net.SpManager;
import com.bw.net.bean.OutLoginBean;
import com.bw.net.bean.UploadBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.UploadModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.UploadPresenter;

import java.io.FileNotFoundException;

public class HeadPortraitActivity extends BaseActivity<UploadPresenter> implements ToolBar.OnToolBarClick , UploadContract.View {
    private ToolBar toolBar;
    private TextView head_change;
    private ImageView imageView;
    private PopupWindow popupWindow;
    private Button outLogin;

    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        toolBar = findViewById(R.id.head_toolBar);
        toolBar.setOnToolBarClick(this);

        head_change = findViewById(R.id.head_change);
        head_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuWindow();
            }
        });

        imageView = findViewById(R.id.head_img);

        mPresenter=new UploadPresenter(new UploadModel(),this);
        outLogin=findViewById(R.id.outLogin);

        outLogin.setOnClickListener(new View.OnClickListener() {  //退出登录
            @Override
            public void onClick(View v) {
                mPresenter.outlogin();
            }
        });

    }

    private void initPopuWindow() {  //popupWindow逻辑
        View inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout, null);
        TextView open_images = inflate.findViewById(R.id.open_images);

        open_images.setOnClickListener(new View.OnClickListener() {  //打开图库
            @Override
            public void onClick(View v) {
                showMsg("打开图库");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 101);

                popupWindow.dismiss();
            }
        });

        TextView open_photo = inflate.findViewById(R.id.open_photo);

        open_photo.setOnClickListener(new View.OnClickListener() {  //打开拍照
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        inflate.findViewById(R.id.up_img).setOnClickListener(new View.OnClickListener() { //上传头像
            @Override
            public void onClick(View v) {
                mPresenter.upload();

                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(head_change, Gravity.CENTER, 0, 0);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_headimg;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            Uri data1 = data.getData();
            String imagePath = getImagePath(data1, null);

            //真实的图片路径存入sp
            SpManager.getInstance().addContents("imagePath",imagePath);
            try {
                Bitmap bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data1));
                Bitmap bitmap = GlideManager.getInstance().sampleBitmap(imageView.getMeasuredWidth(), imageView.getMeasuredHeight(), bitmap1);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void uploadOk(UploadBean uploadBean) {
        showMsg(uploadBean.getMessage());
    }

    @Override
    public void outLogin(OutLoginBean outLoginBean) {
        showMsg("退出登录");
        if (SpManager.getInstance().isHaveToken()){  //退出登录逻辑
            SpManager.getInstance().remove("token");
            UserManager.getInstance().saveUserBean(null);
            SpManager.getInstance().remove("imagePath");

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("num",0);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeActivity(this);
    }
}