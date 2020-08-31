package com.p2p.bawei.p2pinvest1801;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.common.HomeDataManager;
import com.bw.framwork.view.BaseActivity;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;
import com.p2p.bawei.p2pinvest1801.activity.MainActivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HdContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HdModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HdPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<HdPresenter> implements HdContract.View {

    private UpdataBean.ResultBean result;
    private int num = 3;
    private ImageView wImg;
    private TextView wTime;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                wTime.setText("倒计时" + num-- + "秒");
                if (num < 0) {
                    if (!HomeDataManager.getInstance().isHaveUpdate()) {
                        mPresenter.upDate();
                    }
                } else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }else if (msg.what == 2) {  //跳转到主页面的逻辑
                if (HomeDataManager.getInstance().isHaveUpdate() && HomeDataManager.getInstance().isHaveHomeBean()) {  //判断各数据是否都请求到了
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }else {
                    handler.sendEmptyMessageDelayed(2,2000);  //没有的话各两秒再重新检测
                }
            }

        }
    };

    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        wImg = findViewById(R.id.w_img);
        wTime = findViewById(R.id.w_time);

        mPresenter = new HdPresenter(new HdModel(), this);
        if (!HomeDataManager.getInstance().isHaveHomeBean()) {
            mPresenter.homeData();
        }

        handler.sendEmptyMessage(1);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(wImg, "alpha", 0, 1);  //欢迎界面动画
        alpha.setDuration(3000);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.start();


    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setHomeData(HomeBean homeData) {
        HomeDataManager.getInstance().saveHomeBean(homeData); //将请求到的首页数据放到单列类中。
    }

    @Override
    public void setUpdateBean(UpdataBean updateBean) {
        result = updateBean.getResult();
        HomeDataManager.getInstance().saveUpdataBean(updateBean);

        float code = Float.parseFloat(result.getVersion());
        if (code > APKVersionCodeUtils.getInstance().getVersionCode()) {   //判断版本号，是否要提醒用户更新
            initDialog();
        } else {
            handler.sendEmptyMessage(2);
        }
    }

    public void initDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setTitle("提示");
        if (result != null) {
            builder.setMessage(result.getDesc());
        }
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {   //版本更新
                final ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
                progressDialog.setMessage("正在更新。。");
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();


                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int progress = 0;

                    @Override
                    public void run() {
                        if (progress == 100) {
                            timer.cancel();
                            progressDialog.dismiss();

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    showMsg("更新完成。");
                                }
                            });
                            handler.sendEmptyMessage(2);

                        } else {
                            progressDialog.setProgress(progress += 2);
                        }

                    }
                }, 0, 100);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.sendEmptyMessage(2);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {  //解决handle内存泄漏问题
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        UserManager.getInstance().removeActivity(this);
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
}
