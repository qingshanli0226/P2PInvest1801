package com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Llog;
import com.example.framwork.mvp.view.BaseActivity;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.mine.mvp.contract.UploadContract;
import com.p2p.bawei.p2pinvest1801.mine.mvp.model.UploadModel;
import com.p2p.bawei.p2pinvest1801.mine.mvp.presenter.UploadPresenter;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.wildma.pictureselector.PictureSelector.PICTURE_RESULT;

public class UserInfoActivity extends BaseActivity<UploadPresenter> implements View.OnClickListener , UploadContract.IUploadContractView {
    private ImageView activityUserInfoPic;
    private TextView activityUserInfoSetPic;
    private Button activityUserInfoOut;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_user_info_set_pic://更换头像
                PictureSelector.create(this, 101).selectPicture();
                break;
            case R.id.activity_user_info_out://退出登录

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && data != null) {

            PictureBean pictureBean = (PictureBean) data.getExtras().get(PICTURE_RESULT);

            assert pictureBean != null;

            String path = pictureBean.getPath();
            mPresenter.upLoadImg(path);
            Llog.d( "onActivityResult: " + path);
            Bitmap bm = BitmapFactory.decodeFile(path); // 获取裁剪到的图片Bitmap


            //二次采样
            Bitmap bitmap = doubleSampling(activityUserInfoPic.getMeasuredWidth(),
                    activityUserInfoPic.getMeasuredHeight(),
                    bm
            );

            //存储二次采样图片
            saveBitmap(bitmap, new File("/storage/emulated/0/Android/data/com.p2p.bawei.p2pinvest1801/files/Pictures/adadad.jpg"));
            activityUserInfoPic.setImageBitmap(bm);


//            File file = new File(path);
//            if (file.exists()) {
//                Bitmap bm = BitmapFactory.decodeFile(path); // 获取裁剪到的图片Bitmap
//                //将图片显示到ImageView中
//                activityUserInfoPic.setImageBitmap(bm);
//            }
//            Glide.with(UserInfoActivity.this).load(path).into(activityUserInfoPic);


//            new Bitmap().compress();
        }


    }

    public static boolean saveBitmap(Bitmap bitmap, File file) {
        if (bitmap == null)
            return false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //二次采样
    private Bitmap doubleSampling(int measuredWidth, int measuredHeight, Bitmap photobitmap) {
        int picWidth = photobitmap.getWidth();
        int picHeight = photobitmap.getHeight();
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight / sampleSize > measuredHeight || picWidth / sampleSize > measuredWidth) {
            sampleSize = sampleSize * 2;
        }
        //第一次采样结束
        //第二次采样，就是按照这个比例采集像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //将originalbitmap转换成byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photobitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap samplaeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samplaeBitmap;
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {

        activityUserInfoPic = findViewById(R.id.activity_user_info_pic);
        activityUserInfoSetPic = findViewById(R.id.activity_user_info_set_pic);
        activityUserInfoOut = findViewById(R.id.activity_user_info_out);
        activityUserInfoSetPic.setOnClickListener(this);
        activityUserInfoOut.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mPresenter = new UploadPresenter(new UploadModel(), this);
    }

    @Override
    public void onPic(StringBean stringBean) {

        Llog.d("onPic: "+stringBean.getCode());
    }
}
