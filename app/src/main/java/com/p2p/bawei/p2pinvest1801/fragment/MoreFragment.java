package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.bw.framwork.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.activity.MoreShuoActivity;
import com.p2p.bawei.p2pinvest1801.activity.RegisterActivity;

public class MoreFragment extends BaseFragment {
    private CheckBox checkBox;
    private RelativeLayout go_register;

    @Override
    public void initView() {
        checkBox= (CheckBox) findViewById(R.id.more_checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    initDialog();
                }else {

                }
            }
        });

        go_register= (RelativeLayout) findViewById(R.id.go_register);

        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.morefragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    private void initDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("是否设置手势密码?");
        builder.setTitle("设置手势密码");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getContext(), MoreShuoActivity.class));
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkBox.setChecked(false);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);

    }
}
