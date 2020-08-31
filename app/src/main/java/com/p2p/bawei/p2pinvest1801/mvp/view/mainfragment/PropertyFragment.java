package com.p2p.bawei.p2pinvest1801.mvp.view.mainfragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.common.ToolBar;
import com.example.common.bean.LoginBean;
import com.example.common.bean.UploadBean;
import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.UploadModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.UploadPresenter;
import com.p2p.bawei.p2pinvest1801.mvp.view.MyPortraitActivty;
import com.p2p.bawei.p2pinvest1801.mvp.view.TouZiActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyFragment extends BaseFragment<UploadPresenter> implements UploadContract.UploadView,ToolBar.ClicksListener,UserManagers.ILoginStatusChangeListener {
    private ImageView properTouxiang;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;
    private LinearLayout properLin;
    private TextView properName;
    private ToolBar toolbar;
    String path;
    @Override
    public void initViews() {

        properName = (TextView) findViewById(R.id.proper_name);
        properTouxiang = (ImageView) findViewById(R.id.proper_touxiang);
        recharge = (ImageView) findViewById(R.id.recharge);
        withdraw = (ImageView) findViewById(R.id.withdraw);
        llTouzi = (TextView) findViewById(R.id.ll_touzi);
        llTouziZhiguan = (TextView) findViewById(R.id.ll_touzi_zhiguan);
        llZichan = (TextView) findViewById(R.id.ll_zichan);
        properLin = (LinearLayout) findViewById(R.id.proper_lin);
        mPresenter = new UploadPresenter(new UploadModel(), this);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        Loginstate();
        toolbar.setClicksListener(this);
        UserManagers.getInstance().setLoginStatusChangeListener(this);
    }
    //检查是否登录
    private void Loginstate() {
        if(UserManagers.getInstance().isUserLogin()){
            properTouxiang.setImageResource(R.drawable.my_user_bg_icon);
            properName.setText(UserManagers.getInstance().getetname());
        }else{
            properTouxiang.setImageResource(R.drawable.my_user_default);
            properName.setText("Hi,Welcome!");
        }
    }
    private void getPermission(){
        //动态申请权限
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.CAMERA"
            }, 103) ;
        }
    }
    private boolean Permissionflag = false;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){//
            case 103://如果申请权限回调的参数
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(),"申请成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"拒绝权限,请重新获取",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void initDatas() {
        properTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow();
            }
        });
        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(TouZiActivity.class, new Bundle());
            }
        });
    }
    Button pop_chose;
    Button pop_photo;
    private Button popUpload;
    //没有登录弹框登录
    private void initPopWindow() {
        final PopupWindow popupWindow = new PopupWindow(properLin, LinearLayout.LayoutParams.MATCH_PARENT, 700);
        popupWindow.setOutsideTouchable(true);
        LayoutInflater from = LayoutInflater.from(getContext());
        View inflate = from.inflate(R.layout.popwindow, null);
        //初始化弹框中控件
        pop_chose = inflate.findViewById(R.id.pop_chose);
        popUpload = inflate.findViewById(R.id.pop_upload);
        pop_photo = inflate.findViewById(R.id.pop_photo);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,0);
        if(Permissionflag){
            //选择相机图片
            pop_chose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chose();
                    popupWindow.dismiss();

                }
            });
            //拍照
            pop_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photo();
                    popupWindow.dismiss();
                }
            });
            //上传图片
            popUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.uploadP();
                }
            });
        }else {

            getPermission();
        }
    }
    //拍照
    private void photo() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        path = "/sdcard/电影/"+getName();
        Uri uriForFile = FileProvider.getUriForFile(getContext(), "com.example.hemingxuan.aa", new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        startActivityForResult(intent, 102);
    }
    //图册选择
    private void chose() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }
    //用当前时间作为文件前缀
    private String getName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String format = simpleDateFormat.format(date);
        return "IMG_"+format+".jpg";
    }
    @Override
    public int bandLayout() {
        return R.layout.propertyfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            Uri data1 = data.getData();
//            Bitmap bitmap = GlideCacheManager.getInstance().samplePic(properTouxiang.getMeasuredWidth(), properTouxiang.getMeasuredHeight(), data1);
//            properTouxiang.setImageBitmap(bitmap);
            Glide.with(this)
                    .load(data1)
                    .circleCrop()
                    .into(properTouxiang);
        }else if(requestCode == 102 && resultCode == Activity.RESULT_OK){
            Glide.with(this)
                    .load(path)
                    .circleCrop()
                    .into(properTouxiang);
        }
    }

    @Override
    public void uploadView(UploadBean uploadBean) {
        Toast.makeText(getContext(), ""+uploadBean.getCode()
                +"返回字符串"+uploadBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public String url() {
        return path;
    }

    @Override
    public void leftclick() {

    }
    @Override
    public void rightclick() {
        launchActivity(MyPortraitActivty.class, new Bundle());
    }
    //监听登录状态
    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Loginstate();
    }
    //监听是否退出登录
    @Override
    public void onLogoutSuccess() {
        Loginstate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManagers.getInstance().removeLoginStatusChangeListener(this);
    }
}
