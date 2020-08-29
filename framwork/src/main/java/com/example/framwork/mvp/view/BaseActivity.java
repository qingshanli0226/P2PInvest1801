package com.example.framwork.mvp.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ToolBar;
import com.example.framwork.R;
import com.example.framwork.mvp.presenter.IPresenter;
import com.example.framwork.mvp.user.UserManagers;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity,IView {
    protected P mPresenter;
    protected ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        UserManagers.getInstance().addActivity(this);
        toolBar = findViewById(R.id.toolbar);
        initViews();
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.destroy();
            mPresenter = null;
        }
        UserManagers.getInstance().removeActivity(this);
    }
    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, launcActivityClass);
        startActivity(intent);
    }
}
