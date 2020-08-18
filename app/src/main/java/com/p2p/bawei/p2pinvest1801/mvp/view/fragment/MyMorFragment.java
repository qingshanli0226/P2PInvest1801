package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.p2p.bawei.p2pinvest1801.mvp.view.activity.MyUserregisActivity;

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
        }
    }
}
