package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;
import com.example.common.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyGestureActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyHelperActivity;
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyUserregisActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
public class MyMorFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout userregis;
    private LinearLayout gespassword;
    private LinearLayout agegespassword;
    private LinearLayout ccservice;
    private LinearLayout userfeedback;
    private LinearLayout friendsshare;
    private LinearLayout inregard;
    private Switch sw;
    @Override
    public int BondLayout() {
        return R.layout.mymorefragment;
    }

    @Override
    public void initview() {
        userregis = (LinearLayout) findViewById(R.id.userregis);
        gespassword = (LinearLayout) findViewById(R.id.gespassword);
        agegespassword = (LinearLayout) findViewById(R.id.agegespassword);
        ccservice = (LinearLayout) findViewById(R.id.ccservice);
        userfeedback = (LinearLayout) findViewById(R.id.userfeedback);
        friendsshare = (LinearLayout) findViewById(R.id.friendsshare);
        inregard = (LinearLayout) findViewById(R.id.inregard);
        userregis.setOnClickListener(this);
        gespassword.setOnClickListener(this);
        agegespassword.setOnClickListener(this);
        ccservice.setOnClickListener(this);
        userfeedback.setOnClickListener(this);
        friendsshare.setOnClickListener(this);
        inregard.setOnClickListener(this);
        sw = (Switch) findViewById(R.id.sw);
    }

    @Override
    public void initdata() {
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    final PopupWindow popupWindow = new PopupWindow();
                    popupWindow.setWidth(450);
                    popupWindow.setHeight(800);
                    popupWindow.setOutsideTouchable(true);
                    View inflate = getLayoutInflater().inflate(R.layout.ssmm, null);
                    popupWindow.setContentView(inflate);
                    popupWindow.showAtLocation(ccservice, Gravity.CENTER_HORIZONTAL,0,100);
                    Button bt_sure = inflate.findViewById(R.id.bt_sure);
                    Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                    bt_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "设置", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), MyGestureActivity.class));
                            popupWindow.dismiss();
                        }
                    });
                    bt_cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sw.setChecked(false);
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void initInJect() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.userregis:
                startActivity(new Intent(getContext(), MyUserregisActivity.class));
                break;
            case R.id.agegespassword:
                Toast.makeText(getContext(), "重置手势密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ccservice:
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(450);
                popupWindow.setHeight(800);
                popupWindow.setOutsideTouchable(true);
                View inflate = getLayoutInflater().inflate(R.layout.ssmm, null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(ccservice, Gravity.CENTER_HORIZONTAL,0,100);
                Button bt_sure = inflate.findViewById(R.id.bt_sure);
                Button bt_cancle = inflate.findViewById(R.id.bt_cancle);
                bt_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });
                bt_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Toast.makeText(getContext(), "联系客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userfeedback:

                Toast.makeText(getContext(), "用户反馈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.friendsshare:
                UMImage image = new UMImage(getActivity(), R.mipmap.adr);//本地文件
                new ShareAction(getActivity()).withText("硅谷金融").withMedia(image).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                Toast.makeText(getContext(), "分享给好友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inregard:
                startActivity(new Intent(getActivity(), MyHelperActivity.class));
                Toast.makeText(getContext(), "关于硅谷理财", Toast.LENGTH_SHORT).show();
                break;
        }
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
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
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
}
