package com.p2p.bawei.p2pinvest1801.home.more;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.framework2.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.more.reg.RegActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment {
    private View moreView;
    private boolean isOpen = false;
    private ImageView more_toggle;
    private TextView more_user_reg;
    private RelativeLayout more_geste;
    private TextView more_reset_geste;
    private RelativeLayout more_call;
    private TextView more_feedback;
    private TextView more_share;
    private TextView more_about;
    private Button callYes;
    private Button callNo;
    private Button feedbackYes;
    private Button feedbackNo;
    private PopupWindow callPop;
    private PopupWindow feedbackPop;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more_geste:
                geste();
                break;
            case R.id.more_call:
                call(v);
                break;
            case R.id.more_feedback:
                feedBack(v);
                break;
            case R.id.more_user_reg:
                userReg();
                break;
            case R.id.more_reset_geste:
                resetGeste();
                break;
            case R.id.more_share:
                share();
                break;
            case R.id.more_about:
                about();
                break;
            case R.id.call_no:
                Log.e("ff", "onClick: no" );
                callPop.dismiss();
                break;
            case R.id.call_yes:
                Log.e("ff", "onClick: yes" );
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-56253825"));
                startActivity(intent);
                callPop.dismiss();
                break;
            case R.id.feedback_no:
                Log.e("ff", "onClick: no" );
                feedbackPop.dismiss();
                break;
            case R.id.feedback_yes:
                Log.e("ff", "onClick: yes" );
                feedbackPop.dismiss();
                break;
        }
    }

    private void share() {

    }

    private void resetGeste() {
        isOpen=false;
        more_toggle.setImageResource(R.drawable.toggle_off);
    }

    private void userReg() {
        startActivity(new Intent(getActivity(), RegActivity.class));
    }

    private void feedBack(View view) {
        View feedView = LayoutInflater.from(getContext()).inflate(R.layout.feedback_popwindow, null);
        feedbackNo=feedView.findViewById(R.id.feedback_no);
        feedbackYes=feedView.findViewById(R.id.feedback_yes);
        feedbackYes.setOnClickListener(this);
        feedbackNo.setOnClickListener(this);
       feedbackPop= new PopupWindow(feedView,600,500);
        feedbackPop.setOutsideTouchable(true);
        feedbackPop.setFocusable(true);
//         moreView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_more, null);
        feedbackPop.showAtLocation(view, Gravity.CENTER,0,0);
    }

    private void call(View view) {
        View callView = LayoutInflater.from(getContext()).inflate(R.layout.call_popwindow, null);
        callNo=callView.findViewById(R.id.call_no);
        callYes=callView.findViewById(R.id.call_yes);
        callYes.setOnClickListener(this);
        callNo.setOnClickListener(this);
        callPop = new PopupWindow(callView,500,400);
        callPop.setOutsideTouchable(true);
        callPop.setFocusable(true);
//         moreView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_more, null);
        callPop.showAtLocation(view, Gravity.CENTER,0,0);

    }

    private void about() {
        startActivity(new Intent(getActivity(),AboutActivity.class));
    }

    private void geste() {
        if (isOpen) {
            isOpen = false;
            more_toggle.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.toggle_off));
        } else {
            isOpen = true;
            more_toggle.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.toggle_on));
        }
    }

    @Override
    public void initView() {
        more_about= (TextView) findViewById(R.id.more_about);
        more_call= (RelativeLayout) findViewById(R.id.more_call);
        more_feedback= (TextView) findViewById(R.id.more_feedback);
        more_geste= (RelativeLayout) findViewById(R.id.more_geste);
        more_user_reg= (TextView) findViewById(R.id.more_user_reg);
        more_reset_geste= (TextView) findViewById(R.id.more_reset_geste);
        more_share= (TextView) findViewById(R.id.more_share);
        more_toggle = (ImageView) findViewById(R.id.more_toggle);
        more_geste.setOnClickListener(this);
        more_about.setOnClickListener(this);
        more_call.setOnClickListener(this);
        more_feedback.setOnClickListener(this);
        more_user_reg.setOnClickListener(this);
        more_share.setOnClickListener(this);
        more_reset_geste.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_more;
    }
}
