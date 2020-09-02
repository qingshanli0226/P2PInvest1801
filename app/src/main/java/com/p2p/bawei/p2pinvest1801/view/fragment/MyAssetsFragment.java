package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;
import com.p2p.bawei.p2pinvest1801.view.activity.RechargeActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.HistogramActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.LineChartActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.PieChartActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.CashActivity;
import com.p2p.bawei.p2pinvest1801.view.activity.UserMessageActivity;

/**
 * 我的资产
 */
public class MyAssetsFragment extends BaseFragment<MyAllPresenter> implements MyAllContract.View, View.OnClickListener {

    private ImageView settings;
    private LinearLayout linear;
    private TextView textTzgl;
    private TextView textTzglZg;
    private TextView textZcgl;
    private Button btCz;
    private Button btTx;


    @Override
    public int bandLayout() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void initView() {

        linear = (LinearLayout) findViewById(R.id.linear);
        settings = (ImageView) findViewById(R.id.settings);
        textTzgl = (TextView) findViewById(R.id.text_tzgl);
        textTzglZg = (TextView) findViewById(R.id.text_tzgl_zg);
        textZcgl = (TextView) findViewById(R.id.text_zcgl);
        btCz = (Button) findViewById(R.id.bt_cz);
        btTx = (Button) findViewById(R.id.bt_tx);
        settings.setOnClickListener(this);//用户更换头像
        textTzgl.setOnClickListener(this);//折线图
        textTzglZg.setOnClickListener(this);//柱状图
        textZcgl.setOnClickListener(this);//饼图
        btCz.setOnClickListener(this);//充值
        btTx.setOnClickListener(this);//提现


    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter(MyAllBean myAllBean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settings:
                Intent intent = new Intent(getActivity(), UserMessageActivity.class);
                startActivity(intent);
                break;

            case R.id.text_tzgl://折线图
                Intent intent1 = new Intent(getActivity(), LineChartActivity.class);
                startActivity(intent1);
                break;

            case R.id.text_tzgl_zg://柱状图
                Intent intent2 = new Intent(getActivity(), HistogramActivity.class);
                startActivity(intent2);
                break;

            case R.id.text_zcgl://饼图

                Intent intent3 = new Intent(getActivity(), PieChartActivity.class);
                startActivity(intent3);
                break;

            case R.id.bt_cz:
                Intent intent4 = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent4);
                break;

            case R.id.bt_tx:
                Intent intent5 = new Intent(getActivity(), CashActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
