package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;
import com.p2p.bawei.p2pinvest1801.view.activity.GuiGuActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.GesturePasswordActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.RegisterActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * 更多
 */
public class MoreFragment  extends BaseFragment<MyHomePresenter> implements MyHomeContract.View {

    private Switch btSwitch;
    private TextView callPhone;
    private TextView textBottom;
    private ImageView imagePwd;
    private TextView textRegister;
    private TextView textUser;
    private TextView textShare;


    @Override
    public int bandLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView() {

        callPhone = (TextView) findViewById(R.id.call_phone);
        btSwitch = (Switch) findViewById(R.id.bt_switch);
        textBottom = (TextView) findViewById(R.id.text_bottom);
        imagePwd = (ImageView) findViewById(R.id.image_pwd);
        textRegister = (TextView) findViewById(R.id.text_register);
        textUser = (TextView) findViewById(R.id.text_user);
        textShare = (TextView) findViewById(R.id.text_share);

        init_switch();//手势开关
        init_phone();//联系客服
        init_bottom();
        init_pwd();//重新绘制密码
        init_register();
        init_userResult();
        textShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_share();//分享
            }
        });


    }

    private void init_share() {

        UMImage image = new UMImage(getActivity(), R.drawable.umeng_socialize_share_web);//资源文件
        new ShareAction(getActivity()).withText("hello").withMedia(image).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getContext(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };

    private void init_userResult() {
        textUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setHeight(900);
                popupWindow.setWidth(1200);
                popupWindow.setOutsideTouchable(true);
                View inflate = getLayoutInflater().inflate(R.layout.layout_user_result, null);
                popupWindow.setContentView(inflate);
                Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                Button bt_sure = inflate.findViewById(R.id.bt_sure);
                popupWindow.showAsDropDown(callPhone);

                bt_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });

                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    private void init_register() {
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init_pwd() {
         imagePwd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(), GesturePasswordActivity.class);
                 startActivity(intent);
             }
         });
    }

    private void init_bottom() {
        textBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GuiGuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init_phone() {
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setHeight(700);
                popupWindow.setWidth(1100);
                popupWindow.setOutsideTouchable(true);
                View inflate = getLayoutInflater().inflate(R.layout.layout_call_phone, null);
                popupWindow.setContentView(inflate);
                Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                Button bt_sure = inflate.findViewById(R.id.bt_sure);
                popupWindow.showAsDropDown(callPhone);
                
                bt_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "您取消了打电话", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "打电话给客服,请稍等...", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    private void init_switch() {
        btSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    btSwitch.getTextOn();
                    final PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setHeight(700);
                    popupWindow.setWidth(1100);
                    popupWindow.setOutsideTouchable(true);
                    View inflate = getLayoutInflater().inflate(R.layout.layout_password_item, null);
                    popupWindow.setContentView(inflate);
                    Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                    Button bt_sure = inflate.findViewById(R.id.bt_sure);
                    popupWindow.showAsDropDown(callPhone);

                    bt_cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "您取消了设置手势密码", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();

                        }
                    });

                    bt_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "设置手势密码,请稍后...", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                            Intent intent = new Intent(getContext(), GesturePasswordActivity.class);
                            startActivity(intent);
                        }
                    });
                }else {
                    btSwitch.getTextOff();
                    Toast.makeText(getContext(), "您取消了设置手势密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter(MyHomeBean myHomeBean) {

    }
}
