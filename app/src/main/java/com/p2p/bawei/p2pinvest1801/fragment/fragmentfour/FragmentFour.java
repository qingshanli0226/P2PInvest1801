package com.p2p.bawei.p2pinvest1801.fragment.fragmentfour;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.aboutactivity.AboutActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.gesturelocksctivity.GestureLockActivity;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.dialog.MyDialog;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.popupwindow.MyPopupWindow;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentfour.activity.registeractivity.RegisterActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;

public class FragmentFour extends BaseFragment{
    private TextView tvToRegister;
    private TextView tvtoLxKf;
    private TextView tvToYhfk;
    private TextView tvToShare;
    private TextView tvToAbout;
    private TextView tvSetHand;
    private boolean isClicked = false;
    private ImageView moreToggle;

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_four;
    }

    @Override
    public void initView() {
        tvToRegister = (TextView) findViewById(R.id.tv_to_register);
        tvtoLxKf = (TextView) findViewById(R.id.tv_to_lxkf);
        tvToYhfk = (TextView) findViewById(R.id.tv_to_yhfk);
        tvSetHand = (TextView) findViewById(R.id.tv_set_hand);
        tvToShare = (TextView) findViewById(R.id.tv_to_share);
        tvToAbout = (TextView) findViewById(R.id.tv_to_about);
        moreToggle = (ImageView) findViewById(R.id.more_toggle);
    }

    @Override
    public void initData() {
        moreToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked){
                    Glide.with(getContext()).load(R.drawable.toggle_off).into(moreToggle);
                    isClicked = false;
                }else {
                    Glide.with(getContext()).load(R.drawable.toggle_on).into(moreToggle);
                    isClicked =true;
                }
            }
        });

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvtoLxKf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("是否联系客服");
                builder.setMessage("是否现在联系客服：010-56253825");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        tvToYhfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog = new MyDialog(getContext(), R.style.MyTheDialog);
                myDialog.show();
            }
        });

        tvSetHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked){
                    Intent intent = new Intent(getActivity(), GestureLockActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "请开启手势密码", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvToShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPopupWindow myPopupWindow = new MyPopupWindow(getActivity());
                myPopupWindow.showAtLocation(getActivity().findViewById(R.id.tab_id_layout), Gravity.BOTTOM,0,0);

            }
        });

        tvToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });

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
