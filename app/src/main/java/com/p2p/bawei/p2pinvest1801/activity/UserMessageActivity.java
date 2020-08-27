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
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.example.common.CacheManager;
import com.example.net.mode.UploadBean;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.presenter.RegisterLoginPresenterImpl;

import java.io.ByteArrayOutputStream;
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

    @Override
    protected void initGetData() {

        //退出登录
        unLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断登录状态
                boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
                if(aBoolean){
                    //登录过 可以退出登录
                    //发起退出登录的网络请求
                    iHttpPresenter.onLoginOut();
//                    showMessage("退出登录成功");

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
                boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
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
        SharedPreferences.Editor editor = CacheManager.getInstance().getEditor();
        editor.putString(FinanceConstant.IMG_ADDRESS, path);
        editor.commit();
    }


    private void camera() {
        path = getName();
        //保存头像地址
        saveIcon(path);
        printLog(path);
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
            String path = data1.getPath();
            printLog(data1.getEncodedPath());
            printLog(path);
            //保存图片
            saveIcon(path);
            try {
                InputStream inputStream = getContentResolver().openInputStream(data1);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //进行二次采样
                Bitmap newBitmap = samplePic(userMessageImg,bitmap);
                userMessageImg.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if(requestCode == 112 && resultCode == Activity.RESULT_OK){

            //进行二次采样
            Bitmap bitmap = samplePicPath(userMessageImg.getWidth(), userMessageImg.getHeight(), path);
            userMessageImg.setImageBitmap(bitmap);


        }
    }
    //通过地址的二次采样
    private Bitmap samplePicPath(int width, int height, String filePath) {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeFile(filePath,options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>height || picWidth/sampleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


        return BitmapFactory.decodeFile(filePath, options);

    }

    //通关图片的二次采样的方法
    private Bitmap samplePic(ImageView imageView,Bitmap bitmap){

        int picWidth = bitmap.getWidth();
        int picHeight = bitmap.getHeight();
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>imageView.getHeight() || picWidth/sampleSize > imageView.getWidth()) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        //将bitmap转换成byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Bitmap samPlaceBitmap =  BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samPlaceBitmap;
    }

    @Override
    protected void initView() {
        //获取sp文件实例
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

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
        //改变登录状态
        editor.putBoolean(FinanceConstant.ISLOGIN,false);
        editor.commit();
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
