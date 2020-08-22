package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.view.activity.MainActivity;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import static com.wildma.pictureselector.PictureSelector.PICTURE_RESULT;
import static com.wildma.pictureselector.PictureSelector.SELECT_REQUEST_CODE;

public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView updateName;
    private Button exitLogin;
    private ImageView imageTitle;
    private int width,height;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 1;//本地
    private static final int CODE_CAMERA_REQUEST = 2;//拍照

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

        initView();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update_image:
                //initUpdate(width,height);
                initIMageTitle();
                break;

            case R.id.exit_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initIMageTitle() {
        PictureSelector.create(this,0).selectPicture(true);
    }

    private void initView() {
        updateName = (TextView) findViewById(R.id.update_image);
        exitLogin = (Button) findViewById(R.id.exit_login);
        imageTitle = (ImageView) findViewById(R.id.image_title);

        updateName.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // Log.i("wby", "onActivityResult: .............");
        Log.i("wby", "onActivityResult: data."+data);
        if(requestCode==0 && data!=null){
            Log.i("wby", "onActivityResult: 3333.");
            PictureBean pictureBean = (PictureBean) data.getExtras().get(PICTURE_RESULT);
            Log.i("wby", "onActivityResult: 2222222222.");
            if(pictureBean!=null&&pictureBean.getPath()!=null){
                Log.i("wby", "onActivityResult: 111111111.");
                Glide.with(this)
                        .load(pictureBean.getPath())
                        .apply(new RequestOptions().circleCrop())
                        .into(imageTitle);
            }

        }
    }
}
