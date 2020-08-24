package com.p2p.bawei.p2pinvest1801.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.baselibrary.mvp.view.BaseFragment;
import com.example.common.ARouterCode;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.user_act.manager.UserManager;
import com.p2p.bawei.p2pinvest1801.user_act.view.LoginAct;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import java.util.Objects;

@Route(path = ARouterCode.USER_FRAGMENT)
public class UserFragment extends BaseFragment {
    private ImageView mHeadPic;
    private boolean flag = false;
    private String image = "http://49.233.93.155:8080/atguigu";
    private String image_pic;
    private PopupWindow popupWindow;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.user_head_pic) {
            if (!flag) {
                Intent intent = new Intent(getContext(), LoginAct.class);
                startActivityForResult(intent, 152);
            } else {
                initPop();
            }

        }
        if (view.getId() == R.id.zhu) {
            Intent intent = new Intent(getContext(), ZhuAct.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.yuan) {
            Intent intent = new Intent(getContext(), YuanAct.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.zhe) {
            Intent intent = new Intent(getContext(), ZheAct.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.head_pic_beck) {
            popupWindow.dismiss();
        }

        if (view.getId() == R.id.show_head_pic) {
            Intent intent = new Intent(getContext(), Picture.class);
            intent.putExtra("image", image_pic);
            startActivity(intent);
            popupWindow.dismiss();
        }
    }

    private void initPop() {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getWidth());
        popupWindow.setHeight(Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getHeight());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.pop_layout, null);
        popupWindow.setContentView(inflate);
        popupWindow.showAsDropDown(findViewById(R.id.zhe));
        inflate.findViewById(R.id.show_head_pic).setOnClickListener(this);
        inflate.findViewById(R.id.head_pic_beck).setOnClickListener(this);
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(getContext());

        findViewById(R.id.zhu).setOnClickListener(this);
        findViewById(R.id.yuan).setOnClickListener(this);
        findViewById(R.id.zhe).setOnClickListener(this);

        mHeadPic = (ImageView) findViewById(R.id.user_head_pic);
        mHeadPic.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    private Bitmap samplePic() {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeResource(getResources(), R.mipmap.a, options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight / sampleSize > 100 || picWidth / sampleSize > 100) {
            sampleSize = sampleSize * 2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


        return BitmapFactory.decodeResource(getResources(), R.mipmap.a, options);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.user_layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 152 && resultCode == 10000) {
            flag = true;
            String head_image = data.getStringExtra("head_image");
            image_pic = image + head_image;
            Glide.with(this).load(image_pic).transform(new CircleCrop()).into(mHeadPic);
        }
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                assert pictureBean != null;
                if (pictureBean.isCut()) {
                    mHeadPic.setImageBitmap(BitmapFactory.decodeFile(pictureBean.getPath()));
                } else {
                    mHeadPic.setImageURI(pictureBean.getUri());
                }
                Log.e("hq", "onActivityResult: " + pictureBean.getPath());
                //使用 Glide 加载图片
                Glide.with(this)
                        .load(pictureBean.getPath())
                        .into(mHeadPic);
            }
        }
    }
}
