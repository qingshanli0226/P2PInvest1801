package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.common.glide.GlideManager;
import com.bw.common.view.ToolBar;
import com.bw.framwork.view.BaseFragment;
import com.bw.net.SpManager;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.BrokenLineOne;
import com.p2p.bawei.p2pinvest1801.activity.HeadPortraitActivity;

public class MindFragment extends BaseFragment implements ToolBar.OnToolBarClick {
    private ToolBar toolBar;
    private RelativeLayout chat1;

    private ImageView mind_img;
    @Override
    public void initView() {
        toolBar = (ToolBar) findViewById(R.id.mind_ToolBar);
        toolBar.setOnToolBarClick(this);

        mind_img= (ImageView) findViewById(R.id.mind_img);

        chat1= (RelativeLayout) findViewById(R.id.chat1);

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BrokenLineOne.class));  //跳转到第一个折线图
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.mindfragment;
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

    }

    @Override
    public void onRightClick() {
        Intent intent = new Intent(getContext(), HeadPortraitActivity.class);  //跳转到设置头像的activity
        startActivity(intent);

    }

    @Override
    public void onResume() { //读取头像文件，并设置
        super.onResume();
        String imagePath = SpManager.getInstance().getContent("imagePath");

        Bitmap bitmap = GlideManager.getInstance().samplePic(mind_img.getMeasuredWidth(), mind_img.getMeasuredHeight(), imagePath);
        mind_img.setImageBitmap(bitmap);
    }
}
