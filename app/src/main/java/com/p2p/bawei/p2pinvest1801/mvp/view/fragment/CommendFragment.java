package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyView;

import java.util.ArrayList;
import java.util.List;

public class CommendFragment extends BaseFragment {
    private MyView commendView;
    @Override
    public void initViews() {
        commendView = (MyView) findViewById(R.id.commend_view);
    }
    List<String> list_string = new ArrayList<>();
    List<Integer> list_color = new ArrayList<>();
    float down_x;
    float down_y;
    float move_x;
    float move_y;
    int count = 0;
    @Override
    public void initDatas() {

        list_color.add(Color.GREEN);
        list_color.add(Color.RED);
        list_color.add(Color.GRAY);
        list_color.add(Color.BLACK);
        list_color.add(Color.BLUE);
        list_string.add("180天理财计划(加息5%)");
        list_string.add("中情局投资商业经营");
        list_string.add("财神道90天计划");
        list_string.add("硅谷钱包计划");
        list_string.add("月月升理财计划");

        for (int i = 0; i < 4; i++) {
            count += 200;
            int textx = (int) (Math.random()*(200-1)+1);
            int texty = ((int) (Math.random()*(500-1)+1));
            commendView.addBean(list_string.get(i),list_color.get(i),(int) (Math.random()*(100-80)+80),textx,texty,count);
        }
        commendView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        move_x = event.getX();
                        move_y = event.getY();
                        if(move_x - down_x >= 0 && move_y - down_y != 0){
                            Log.i("----", "111");
                            upanim();
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        down_x = event.getX();
                        down_y = event.getY();
                        break;
                }
                return true;
            }
        });

    }

    private void upanim() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(commendView, "ScaleX", 1, 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(commendView, "ScaleY", 1, 2);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(commendView, "alpha", 1, 0);
        alpha.setDuration(10000);
        alpha.setInterpolator(new LinearInterpolator());
        animatorSet.play(scaleX).with(scaleY).before(alpha);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(commendView, "ScaleX", 2, 1);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(commendView, "ScaleY", 2, 1);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(commendView, "alpha", 0, 1);
                alpha.setDuration(10000);
                alpha.setInterpolator(new LinearInterpolator());
                animatorSet.play(alpha).before(scaleX).before(scaleY);
            }
        });
    }

    @Override
    public int bandLayout() {
        return R.layout.commendfragment;
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

    }
}
