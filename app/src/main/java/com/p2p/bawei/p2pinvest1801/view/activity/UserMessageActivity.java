package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import static com.wildma.pictureselector.PictureSelector.PICTURE_RESULT;

public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView updateName;
    private Button exitLogin;
    private ImageView imageTitle;
    private  String path;
    private ImageView imageBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

        initView();

        imageTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
            }
        });

    }

    //显示大图
    private void initPop() {

        final PopupWindow popupWindow = new PopupWindow();
        View inflate = getLayoutInflater().inflate(R.layout.layout_picture, null);
        ImageView image_big = inflate.findViewById(R.id.image_title);
        Glide.with(this).load(path).into(image_big);//获取本地图片路径
        popupWindow.setHeight(1500);
        popupWindow.setWidth(800);
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(imageTitle, Gravity.CENTER,0,0);

        image_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update_image:
                //initUpdate(width,height);
                initIMageTitle();
                break;

            case R.id.exit_login:
               finish();
                break;
        }
    }

    //更换头像，拍照
    private void initIMageTitle() {
        PictureSelector.create(this,0).selectPicture(true);
    }

    //初始化
    private void initView() {
        updateName = (TextView) findViewById(R.id.update_image);
        exitLogin = (Button) findViewById(R.id.exit_login);
        imageTitle = (ImageView) findViewById(R.id.image_title);
        imageBack = (ImageView) findViewById(R.id.image_back);

        updateName.setOnClickListener(this);
        exitLogin.setOnClickListener(this);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 更换头像.拍照
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && data!=null){
            PictureBean pictureBean = (PictureBean) data.getExtras().get(PICTURE_RESULT);
            path = pictureBean.getPath();
            if(pictureBean!=null&&pictureBean.getPath()!=null){
                Glide.with(this)
                        .load(path)
                        .apply(new RequestOptions().circleCrop())
                        .into(imageTitle);
            }

        }
    }
}
