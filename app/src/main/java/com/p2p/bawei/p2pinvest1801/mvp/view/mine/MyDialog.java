package com.p2p.bawei.p2pinvest1801.mvp.view.mine;

import android.app.Dialog;
import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.p2p.bawei.p2pinvest1801.R;

public class MyDialog extends Dialog {
    private TextView dasilogTitle;
    private TextView dasilogMessage;
    private Button dasilogEnsure;
    private Button dasilogCancel;
    //确定按钮被点击了的监听器
    private DasilogEnsureListeren dasilogEnsureListeren;
    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daislog);
        initViews();
        initDatas();
    }

    private void initDatas() {
        dasilogEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dasilogEnsureListeren != null){
                    dasilogEnsureListeren.onEnsureOnclick();
                }
            }
        });
    }

    private void initViews() {
        dasilogTitle = (TextView) findViewById(R.id.dasilog_title);
        dasilogMessage = (TextView) findViewById(R.id.dasilog_message);
        dasilogEnsure = (Button) findViewById(R.id.dasilog_ensure);
    }
    public void setTitle(String title){
        dasilogTitle.setText(title);
    }
    public void setmessage(String message){
        dasilogMessage.setText(message);
    }
    public void showCancel(int visbility){
        dasilogCancel.setVisibility(visbility);
    }
    public void setDasilogEnsureListeren(DasilogEnsureListeren clickListener){
        this.dasilogEnsureListeren = clickListener;
    }
    //自定义点击事件
    public interface DasilogEnsureListeren {
        void onEnsureOnclick();
    }


}
