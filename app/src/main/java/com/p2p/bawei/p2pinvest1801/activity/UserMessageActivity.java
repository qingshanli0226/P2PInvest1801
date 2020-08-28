package com.p2p.bawei.p2pinvest1801.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseMVPActivity;
import com.example.framework.base.manager.UserManager;
import com.example.framework.base.service.FinanceService;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.example.common.CacheManager;
import com.example.net.mode.UploadBean;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.presenter.RegisterLoginPresenterImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMessageActivity extends BaseMVPActivity<RegisterLoginPresenterImpl, RegisterLoginContract.RegisterLoginView> implements RegisterLoginContract.RegisterLoginView {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private TextView userMessageTv;
    private Button unLoginButton;
    private LinearLayout userMessageView;
    private ImageView userMessageImg;

    private String path;
    private String newPath = "/sdcard/DCIM/aa.jpg";
    private String newFilePath = "/sdcard/皮蛋瘦肉粥.jpg";

    private boolean aBoolean = false;

    private FinanceService.FinanceBinder binder;


    @Override
    protected void initGetData() {

        //退出登录
        unLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (this){
                    //判断登录状态
                   aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
                }
                if(aBoolean){
                    //登录过 可以退出登录
                    //发起退出登录的网络请求
                    iHttpPresenter.onLoginOut();

                } else{
                    //没有登录
                    showMessage("请你先登录");
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterLoginPresenterImpl();
    }

    @Override
    protected void initData() {

        userMessageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (this){
                    aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
                }
                if(aBoolean){
                    //登录了
                    //点击弹出popWindow选择框
                    userMessageTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final PopupWindow popupWindow = new PopupWindow();
                            View view = LayoutInflater.from(UserMessageActivity.this).inflate(R.layout.myview_pop, null);
                            popupWindow.setContentView(view);
                            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                            //设置点击外部可取消
                            popupWindow.setOutsideTouchable(true);
                            //显示位置
                            popupWindow.showAtLocation(userMessageView, Gravity.BOTTOM,0,0);

                            //获取实例对象
                            TextView photoMap = view.findViewById(R.id.photoMap);
                            TextView camera = view.findViewById(R.id.camera);
                            TextView uploadImg = view.findViewById(R.id.uploadImg);

                            //点击上传头像
                            uploadImg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    File file = new File(newFilePath);
                                    iHttpPresenter.upload(file);
                                    popupWindow.dismiss();
                                }
                            });

                            //点击打开图库
                            photoMap.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    photoMap();
                                    popupWindow.dismiss();
                                }
                            });

                            //打开相机
                            camera.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    camera();
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    });
                } else{
                    //没有登录
                    showMessage("请你先登录在更换头像");
                }
            }
        });
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
        synchronized (this){
            SharedPreferences.Editor editor = CacheManager.getInstance().getEditor();
            editor.putString(FinanceConstant.IMG_ADDRESS, path);
            editor.commit();
        }
    }


    private void camera() {
        path = getName();
        //保存头像地址
        saveIcon(path);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = FileProvider.getUriForFile(this, "com.p2p.bawei.p2pinvest1801", new File(path));
        //手动设置输出路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,112);
    }

    private void photoMap() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            Uri data1 = data.getData();//获取原图
            //保存图片
            saveIcon(path);
            try {
                InputStream inputStream = getContentResolver().openInputStream(data1);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //通过服务进行二次采样
                Bitmap newBitmap = binder.getFinanceService().samplePic(userMessageImg,bitmap);
                userMessageImg.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if(requestCode == 112 && resultCode == Activity.RESULT_OK){
            //通过服务进行二次采样
            Bitmap bitmap = binder.getFinanceService().samplePicPath(userMessageImg.getWidth(), userMessageImg.getHeight(), path);
            userMessageImg.setImageBitmap(bitmap);

        }
    }

    @Override
    protected void initView() {
        //获取sp文件实例
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        //获取服务
        binder = UserManager.getInstance().getBinder();

        userMessageTv = (TextView) findViewById(R.id.userMessageTv);
        unLoginButton = (Button) findViewById(R.id.unLoginButton);
        userMessageView = (LinearLayout) findViewById(R.id.userMessageView);
        userMessageImg = (ImageView) findViewById(R.id.userMessageImg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {

    }

    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public void onLoginOutData(UnLoginBean unLoginBean) {
        showMessage("退出登录成功");
        synchronized (this){
            //改变登录状态
            editor.putBoolean(FinanceConstant.ISLOGIN,false);
            editor.commit();
        }
        lunachActivity(MainActivity.class,null);
    }

    @Override
    public void uploadData(UploadBean uploadBean) {
        showMessage("上传文件成功"+uploadBean.getCode());
    }

    @Override
    public void showError(String code, String message) {
        showMessage(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
