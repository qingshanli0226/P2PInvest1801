package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.view.activity.MainActivity;

public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView updateName;
    private Button exitLogin;
    private ImageView imageTitle;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 1;//本地
    private static final int CODE_CAMERA_REQUEST = 2;//拍照

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

        initView();
    }

    private void initView() {
        updateName = (TextView) findViewById(R.id.update_image);
        exitLogin = (Button) findViewById(R.id.exit_login);
        imageTitle = (ImageView) findViewById(R.id.image_title);

        updateName.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update_image:
                initUpdate();
                break;

            case R.id.exit_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initUpdate() {

    }
}
