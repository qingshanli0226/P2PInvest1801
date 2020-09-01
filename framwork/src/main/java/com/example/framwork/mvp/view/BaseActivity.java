package com.example.framwork.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framwork.R;
import com.example.framwork.manager.CacheManager;
import com.example.framwork.mvp.presenter.IPresenter;

import java.security.acl.Group;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, IView, View.OnClickListener {

    protected P mPresenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initView();
        initData();
        CacheManager.getInstance().addActivity(this);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

        final LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 600));
        imageView.setImageResource(R.drawable.dog_anim);
        linearLayout.addView(imageView);

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }


        CacheManager.getInstance().removeActivity(this);

    }
}
