package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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

import com.example.common.ToolBar;
import com.example.common.bean.LoginBean;
import com.example.common.bean.UploadBean;
import com.example.framwork.mvp.user.UserManagers;
import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.cache.GlideCacheManager;
import com.p2p.bawei.p2pinvest1801.mvp.view.Myportrait;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.UploadModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.UploadPresenter;
import com.p2p.bawei.p2pinvest1801.mvp.view.TouZiActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyFragment extends BaseFragment<UploadPresenter> implements UploadContract.UploadView,ToolBar.ClicksListener,UserManagers.ILoginStatusChangeListener {
    private ImageView properTouxiang;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;
    private LinearLayout properLin;
    private TextView properName;
    private ToolBar toolbar;
    String path;
    @Override
    public void initViews() {
        properName = (TextView) findViewById(R.id.proper_name);
        properTouxiang = (ImageView) findViewById(R.id.proper_touxiang);
        recharge = (ImageView) findViewById(R.id.recharge);
        withdraw = (ImageView) findViewById(R.id.withdraw);
        llTouzi = (TextView) findViewById(R.id.ll_touzi);
        llTouziZhiguan = (TextView) findViewById(R.id.ll_touzi_zhiguan);
        llZichan = (TextView) findViewById(R.id.ll_zichan);
        properLin = (LinearLayout) findViewById(R.id.proper_lin);
        mPresenter = new UploadPresenter(new UploadModel(), this);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        Loginstate();
        toolbar.setClicksListener(this);
        UserManagers.getInstance().setLoginStatusChangeListener(this);
    }

    private void Loginstate() {
        if(UserManagers.getInstance().isUserLogin()){
            properTouxiang.setImageResource(R.drawable.my_user_bg_icon);
            properName.setText(UserManagers.getInstance().getetname());
        }else{
            properTouxiang.setImageResource(R.drawable.my_user_default);
            properName.setText("Hi,Welcome!");
        }
    }


    @Override
    public void initDatas() {
        properTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow();
            }
        });
        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TouZiActivity.class);
                startActivity(intent);
            }
        });
    }
    Button pop_chose;
    Button pop_photo;
    private Button popUpload;

    private void initPopWindow() {
        final PopupWindow popupWindow = new PopupWindow(properLin, LinearLayout.LayoutParams.MATCH_PARENT, 700);
        popupWindow.setOutsideTouchable(true);
        LayoutInflater from = LayoutInflater.from(getContext());
        View inflate = from.inflate(R.layout.popwindow, null);
        pop_chose = inflate.findViewById(R.id.pop_chose);
        popUpload = inflate.findViewById(R.id.pop_upload);
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

        popUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.uploadP();

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
            String data1 = String.valueOf(getRealPathFromUri(getContext(),data.getData()));
            Bitmap bitmap = GlideCacheManager.getInstance().samplePic(properTouxiang.getMeasuredWidth(), properTouxiang.getMeasuredHeight(), data1);
            properTouxiang.setImageBitmap(bitmap);
        }else if(requestCode == 102 && resultCode == Activity.RESULT_OK){

        }
    }

    @Override
    public void uploadView(UploadBean uploadBean) {
        Toast.makeText(getContext(), ""+uploadBean.getCode()
                +"返回字符串"+uploadBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
    public String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    public String url() {
        return path;
    }

    @Override
    public void leftclick() {

    }

    @Override
    public void rightclick() {
        Intent intent = new Intent(getContext(), Myportrait.class);
        startActivity(intent);
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Loginstate();
    }

    @Override
    public void onLogoutSuccess() {
        Loginstate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManagers.getInstance().removeLoginStatusChangeListener(this);
    }
}
