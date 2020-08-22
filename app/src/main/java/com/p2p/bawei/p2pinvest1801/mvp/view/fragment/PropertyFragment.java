package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.MyDialog;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.user.RegisterActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyFragment extends BaseFragment {
    private ImageView properTouxiang;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;
    private LinearLayout properLin;

    @Override
    public void initViews() {
        properTouxiang = (ImageView) findViewById(R.id.proper_touxiang);
        recharge = (ImageView) findViewById(R.id.recharge);
        withdraw = (ImageView) findViewById(R.id.withdraw);
        llTouzi = (TextView) findViewById(R.id.ll_touzi);
        llTouziZhiguan = (TextView) findViewById(R.id.ll_touzi_zhiguan);
        llZichan = (TextView) findViewById(R.id.ll_zichan);
        properLin = (LinearLayout) findViewById(R.id.proper_lin);

    }


    @Override
    public void initDatas() {
        properTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow();
            }
        });
    }
    Button pop_chose;
    Button pop_photo;
    private void initPopWindow() {
        final PopupWindow popupWindow = new PopupWindow(properLin, LinearLayout.LayoutParams.MATCH_PARENT, 500);
        popupWindow.setOutsideTouchable(true);
        LayoutInflater from = LayoutInflater.from(getContext());
        View inflate = from.inflate(R.layout.popwindow, null);
        pop_chose = inflate.findViewById(R.id.pop_chose);
        pop_photo = inflate.findViewById(R.id.pop_photo);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,0);
        pop_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chose();
                popupWindow.dismiss();
            }
        });
        pop_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo();
                popupWindow.dismiss();
            }
        });
    }

    private void photo() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        String path = "/sdcard/tmp"+getName();
        Uri uriForFile = FileProvider.getUriForFile(getContext(), "com.example.hemingxuan.aa", new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        startActivityForResult(intent, 102);
    }

    private void chose() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }
    private String getName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String format = simpleDateFormat.format(date);
        return "IMG_"+format+".jpg";
    }
    @Override
    public int bandLayout() {
        return R.layout.propertyfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            properTouxiang.setImageBitmap((Bitmap) msg.obj);
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            String data1 = data.getData().getPath();
            properTouxiang.setImageURI(data.getData());
//            cai(data1+".jpg");
        }else if(requestCode == 102 && resultCode == Activity.RESULT_OK){

        }
    }

    private void cai(final String data1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("----", data1);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(data1,options);
                Log.i("----", options.outWidth+"");
                Log.i("----", options.outHeight+"");
                int picWidth = 10;
                int picHeight = 10;
                int sampleSize = 1;
                while (picHeight/sampleSize > properTouxiang.getHeight() || picWidth / sampleSize > properTouxiang.getWidth()){
                    sampleSize = sampleSize * 2;
                }

                options.inJustDecodeBounds = false;
                options.inSampleSize = sampleSize;
                options.inPreferredConfig  = Bitmap.Config.ARGB_8888;

                Bitmap bitmap = BitmapFactory.decodeFile(data1, options);
                Message.obtain(handler,1,bitmap).sendToTarget();
            }
        }).start();
    }
}
