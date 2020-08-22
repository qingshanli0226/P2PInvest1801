package com.p2p.bawei.p2pinvest1801.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseFragment;
import com.p2p.bawei.p2pinvest1801.CacheManager;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.HandPasswordActivity;
import com.p2p.bawei.p2pinvest1801.activity.MoreAboutActivity;
import com.p2p.bawei.p2pinvest1801.activity.MoreRegisterActivity;

public class MoreFragment extends BaseFragment implements OnClickListener {

    private ImageView handPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        findViewById(R.id.moreAbout).setOnClickListener(this);
        findViewById(R.id.moreRegister).setOnClickListener(this);
        findViewById(R.id.reHandPassword).setOnClickListener(this);
        findViewById(R.id.moreCallPhone).setOnClickListener(this);
        handPassword = (ImageView) findViewById(R.id.handPassword);
        handPassword.setOnClickListener(this);

        //进入页面就判断状态
        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISOPEN, false);
        if(aBoolean){
            //打开的
            handPassword.setImageResource(R.drawable.toggle_on);
        } else{
            //关闭的
            handPassword.setImageResource(R.drawable.toggle_off);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //关于硅谷理财
            case R.id.moreAbout:
                lunachActivity(MoreAboutActivity.class,null);
                break;
                //用户注册
            case R.id.moreRegister:
                lunachActivity(MoreRegisterActivity.class,null);
                break;
                //是否设置手势密码（图标切换）
            case R.id.handPassword:
                handPassword();
                break;
                //重置手势密码
            case R.id.reHandPassword:
                reHandPassword();
                break;
                //联系客服
            case R.id.moreCallPhone:
                moreCallPhone();
            break;
        }
    }

    private void moreCallPhone() {
        //弹出自定义对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.myview_call_phone, null);
        builder.setView(inflate);
        final AlertDialog alertDialog = builder.create();
        //设置点击外部不可取消
        alertDialog.setCanceledOnTouchOutside(true);
        //外部点击和取消键的监听
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showMessage("取消了拨打电话");
            }
        });
        alertDialog.show();

        //获取yesPhone noPhone按钮
        Button yesPhone = inflate.findViewById(R.id.yesPhone);
        Button noPhone = inflate.findViewById(R.id.noPhone);

        //确认 跳转到拨打电话
        yesPhone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+"010-56253825"));
                startActivity(intent);

                alertDialog.dismiss();
            }
        });

        //取消
        noPhone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("取消了拨打电话");
                alertDialog.dismiss();
            }
        });
    }

    private void reHandPassword() {
        //获取状态
        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISOPEN, false);
        if(aBoolean){
            //已经打开的手势密码  可以重置 跳转到绘制的页面
            showMessage("打开了手势密码，跳转到重置页面");
        } else {
            //没有打开
            showMessage("手势密码操作已关闭，请开启后在设置");
        }
    }

    //手势密码
    private void handPassword() {
        //先从数据库读取
        boolean isOpen = sharedPreferences.getBoolean(FinanceConstant.ISOPEN, false);
        if(isOpen){
            //已经打开了 点击关闭 改变图片和状态
            handPassword.setImageResource(R.drawable.toggle_off);
            editor.putBoolean(FinanceConstant.ISOPEN,false);
            editor.commit();
            showMessage("关闭了手势密码");
        } else{
            //第一次选择打开  弹窗并改变图片和状态
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.myview_hand_password, null);
            builder.setView(inflate);
            final AlertDialog alertDialog = builder.create();
            //设置外部点击不可取消
            alertDialog.setCanceledOnTouchOutside(true);
            //外部点击取消和返回键的监听
            alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    showMessage("取消了设置手势密码");
                    handPassword.setImageResource(R.drawable.toggle_off);
                    editor.putBoolean(FinanceConstant.ISOPEN,false);
                    editor.commit();
                }
            });
            alertDialog.show();
            //改变图片
            handPassword.setImageResource(R.drawable.toggle_on);
            //改变存储的状态
           editor.putBoolean(FinanceConstant.ISOPEN,true);
           editor.commit();

           //获取yes no Button按钮
            Button yes = inflate.findViewById(R.id.yes);
            Button no = inflate.findViewById(R.id.no);

            //点击yes
            yes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到设置手势密码的页面
                    showMessage("跳转设置手势密码页面");
                    alertDialog.dismiss();
                    lunachActivity(HandPasswordActivity.class,null);
                }
            });

            //点击no
            no.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMessage("取消了现在设置手势密码");
                    showMessage("关闭了手势密码");
                    alertDialog.dismiss();
                    handPassword.setImageResource(R.drawable.toggle_off);
                    editor.putBoolean(FinanceConstant.ISOPEN,false);
                    editor.commit();
                }
            });

        }
    }
}
