package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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

public class MoreFragment  extends BaseFragment<MyHomePresenter> implements MyHomeContract.View {

    private Switch btSwitch;
    private TextView callPhone;
    private TextView textBottom;

    @Override
    public int bandLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView() {

        callPhone = (TextView) findViewById(R.id.call_phone);
        btSwitch = (Switch) findViewById(R.id.bt_switch);
        textBottom = (TextView) findViewById(R.id.text_bottom);

        init_switch();//手势开关
        init_phone();//联系客服
        init_bottom();

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
