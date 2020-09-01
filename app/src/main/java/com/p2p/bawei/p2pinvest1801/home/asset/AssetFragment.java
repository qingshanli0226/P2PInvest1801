package com.p2p.bawei.p2pinvest1801.home.asset;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.framework2.manager.CacheManager;
import com.example.framework2.mvp.view.BaseFragment;
import com.example.net.activity_bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.more.log.LogActivity;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssetFragment extends BaseFragment implements CacheManager.LoginINCallback{


    private ImageView asset_head;
    private RelativeLayout asset_manage;
    private RelativeLayout asset_anschauung;
    private RelativeLayout property_manage;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asset_head:
                assetCut();
                break;
            case R.id.asset_manage:
                startActivity(new Intent(getActivity(),AssetOneActivity.class));
                break;
            case R.id.asset_anschauung:
                startActivity(new Intent(getActivity(),AssetTwoActivity.class));
                break;
            case R.id.property_manage:
                startActivity(new Intent(getActivity(),AssetThreeActivity.class));
                break;
        }
    }

    private void assetCut() {
        if (CacheManager.getInstance().getLoginBean()!=null){
            PictureSelector
                    .create(AssetFragment.this, PictureSelector.SELECT_REQUEST_CODE)
                    .selectPicture(true);
        }else {
            startActivity(new Intent(getActivity(), LogActivity.class));
        }

    }

    @Override
    public void initView() {
        asset_head = (ImageView) findViewById(R.id.asset_head);
        asset_anschauung= (RelativeLayout) findViewById(R.id.asset_anschauung);
        asset_manage= (RelativeLayout) findViewById(R.id.asset_manage);
        property_manage= (RelativeLayout) findViewById(R.id.property_manage);
        asset_manage.setOnClickListener(this);
        asset_anschauung.setOnClickListener(this);
        property_manage.setOnClickListener(this);
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

    @Override
    public void OnLoginNotifyCallback(LoginBean loginBean) {
        if (!(CacheManager.getInstance().getLoginBean()==null)){
            Glide.with(getActivity()).load(CacheManager.getInstance().getLoginBean().getResult().getAvatar())
                    .transform(new CircleCrop())
                    .into(asset_head);
        }
    }
}
