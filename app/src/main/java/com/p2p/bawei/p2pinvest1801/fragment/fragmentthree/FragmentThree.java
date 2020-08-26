package com.p2p.bawei.p2pinvest1801.fragment.fragmentthree;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.popupwindow.MyPopupWindow;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheOneActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheThreeActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity.TheTwoActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.popupwindow.ThreePopupWindow;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class FragmentThree extends BaseFragment {
    private ImageView ivIdF3;
    private Button btChongZhi;
    private Button btTiXian;
    private TextView tvFThreeFirst;
    private TextView tvFThreeSec;
    private TextView tvFThreeThe3;
    private Button btXiangJi;
    private Button btBenDi;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/";// sd路径

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_three;
    }

    @Override
    public void initView() {

        ivIdF3 = (ImageView) findViewById(R.id.iv_id_f3);
        btChongZhi = (Button) findViewById(R.id.bt_ChongZhi);
        btTiXian = (Button) findViewById(R.id.bt_TiXian);
        tvFThreeFirst = (TextView) findViewById(R.id.tv_FThree_first);
        tvFThreeSec = (TextView) findViewById(R.id.tv_FThree_sec);
        tvFThreeThe3 = (TextView) findViewById(R.id.tv_FThree_the3);

        Bitmap bt = BitmapFactory.decodeFile(path + "3.jpg");
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            ivIdF3.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }

    @Override
    public void initData() {
        Glide.with(this).load(R.drawable.tx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivIdF3);

        tvFThreeFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheOneActivity.class);
                startActivity(intent);
            }
        });

        tvFThreeSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheTwoActivity.class);
                startActivity(intent);
            }
        });

        tvFThreeThe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheThreeActivity.class);
                startActivity(intent);
            }
        });
        ivIdF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypePopup();
            }
        });
    }

    private void showTypePopup() {
        final ThreePopupWindow threePopupWindow = new ThreePopupWindow(getActivity());
        threePopupWindow.showAtLocation(getActivity().findViewById(R.id.tab_id_layout), Gravity.BOTTOM,0,0);
        View contentView = threePopupWindow.getContentView();
        btXiangJi = contentView.findViewById(R.id.bt_XiangJi);
        btBenDi = contentView.findViewById(R.id.bt_BenDi);
        btBenDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,1);
                threePopupWindow.dismiss();
            }
        });

        btXiangJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//战利品
                File file = new File(Environment.getExternalStorageDirectory() + "/test_image.png");//存到这个位置
                Uri uri = FileProvider.getUriForFile(getContext(),"com.p2p.bawei.p2pinvest1801.fileprovider",file);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                //显示控件
                startActivityForResult(intent2, 2);// 采用ForResult打开
                threePopupWindow.dismiss();
            }
        });
        threePopupWindow.showAsDropDown(contentView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/test_image.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        ivIdF3.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;
        }
    }

    private void setPicToView(Bitmap head) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "test_image.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            head.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cropPhoto(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    @Override
    public void showMessage(String message) {

    }
    @Override
    public void showView() {

    }
    @Override
    public void hideView() {

    }
}
