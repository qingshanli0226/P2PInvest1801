package com.p2p.bawei.p2pinvest1801.home.asset;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.framework2.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssetFragment extends BaseFragment {


    private ImageView asset_head;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.asset_head:
                PictureSelector
                        .create(AssetFragment.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true);
                break;
        }
    }

    @Override
    public void initView() {
        asset_head= (ImageView) findViewById(R.id.asset_head);
        asset_head.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_asset;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
//                if (pictureBean.isCut()) {
//                    asset_head.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
//                } else {
//                    asset_head.setImageURI(pictureBean.getUri());
//                }

                //使用 Glide 加载图片
                Glide.with(this)
                        .load(pictureBean.getPath())
                        .apply(RequestOptions.centerCropTransform()).into(asset_head);
            }
        }
    }
}
