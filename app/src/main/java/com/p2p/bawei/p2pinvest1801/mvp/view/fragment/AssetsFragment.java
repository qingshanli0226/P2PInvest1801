package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.lib_core.mvp.view.BaseFragment;
import com.example.net.AssetsBarChart;
import com.example.net.AssetsLineChart;
import com.example.net.AssetsPieChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.LoginModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.LoginPresenter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

public class AssetsFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {
    private Button mainAssetsRecharge;
    private Button mainAssetsWithdrawDeposit;
    private Button assetsPopTopUpBtn;
    private ImageView userImg;
    private TextView assetsWithdrawDepositPopBalance;
    private ImageView upLoadImg;
    private RelativeLayout assetsHistogram;
    private RelativeLayout assetsLineChar;
    private RelativeLayout assetsScreenGraph;

    private int sum = 0;
    @Override
    public void initView() {

        //柱状图饼状图线性图
        assetsHistogram = (RelativeLayout) findViewById(R.id.assets_histogram);
        assetsLineChar = (RelativeLayout) findViewById(R.id.assets_line_char);
        assetsScreenGraph = (RelativeLayout) findViewById(R.id.assets_Screen_graph);
        graphOnClick();
        //弹出登录窗口
        Login();
        //点击大图展示
        upLoadImg = (ImageView) findViewById(R.id.up_load_img);
        //点击换图片
        userImg = (ImageView) findViewById(R.id.user_img);
        //充值
        mainAssetsRecharge = (Button) findViewById(R.id.main_assets_recharge);
        //提现
        mainAssetsWithdrawDeposit = (Button) findViewById(R.id.main_assets_withdraw_deposit);
        mainAssetsRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    setTranslucentStatus(true);
                    FragmentActivity activity = getActivity();
                    if (activity!=null){
                        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                        tintManager.setStatusBarTintEnabled(true);
                        tintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
                    }

                }
                final PopupWindow popupWindow = new PopupWindow();
                View view= LayoutInflater.from(getContext()).inflate(R.layout.assets_pop_layout, null);
                ImageView assetsPopBack = view.findViewById(R.id.assets_pop_back);
                assetsPopBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            setTranslucentStatus(true);
                            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
                            tintManager.setStatusBarTintEnabled(true);
                            tintManager.setStatusBarTintResource(R.color.colorTransparent);//通知栏所需颜色
                        }
                    }
                });
                EditText assetsPopMoney = view.findViewById(R.id.assets_pop_money);
                TextView assetsPopBalance = view.findViewById(R.id.assets_pop_balance);
                String text = String.valueOf(assetsPopMoney.getText());
                assetsPopBalance.setText(text);
                assetsPopTopUpBtn = view.findViewById(R.id.assets_pop_top_up_btn);
                assetsPopMoney.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length()>0){
                            assetsPopTopUpBtn.setEnabled(true);
                            assetsPopTopUpBtn.setBackgroundResource(R.drawable.btn_01);
                        }else {
                            assetsPopTopUpBtn.setEnabled(false);
                            assetsPopTopUpBtn.setBackgroundResource(R.drawable.btn_02);
                        }
                    }
                });
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow .setBackgroundDrawable(null);
                popupWindow .setFocusable(true);//解决点击view重新打开popupWindow的问题
                popupWindow .setOutsideTouchable(true);//点击外部popupWindow消失
                popupWindow.showAtLocation(mainAssetsRecharge, Gravity.BOTTOM, 0, 0);
            }
        });
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(AssetsFragment.this,0).selectPicture(true);
            }
        });
        mainAssetsWithdrawDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow();
                View view= LayoutInflater.from(getContext()).inflate(R.layout.assets_pop_withdraw_deposit_layout, null);
                ImageView assetsWithdrawDepositPopBack = view.findViewById(R.id.assets_withdraw_deposit__pop_back);
                EditText assetsWithdrawDepositPopMoney = view.findViewById(R.id.assets_withdraw_deposit__pop_money);
                assetsWithdrawDepositPopBalance = view.findViewById(R.id.assets_withdraw_deposit__pop_balance);
                Button assetsWithdrawDepositPopTopUpBtn = view.findViewById(R.id.assets_withdraw_deposit__pop_top_up_btn);
                WithdrawDepositOnclick(popupWindow,assetsWithdrawDepositPopBack,assetsWithdrawDepositPopMoney,assetsWithdrawDepositPopBalance,assetsWithdrawDepositPopTopUpBtn);
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow .setBackgroundDrawable(null);
                popupWindow .setFocusable(true);//解决点击view重新打开popupWindow的问题
                popupWindow .setOutsideTouchable(true);//点击外部popupWindow消失
                popupWindow.showAtLocation(mainAssetsRecharge, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    //点击进入图形
    private void graphOnClick(){
        //折线图
        assetsLineChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineCharOnClick();
            }
        });
        //柱状图
        assetsHistogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistogramOnclick();
            }
        });
        //饼状图
        assetsScreenGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenGraphOnClick();
            }
        });

    }

    //饼状图
    private void ScreenGraphOnClick() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.assets_screen_graph, null);
        PopupWindow popupWindow = new PopupWindow();

        PieChart assetsPieChart = inflate.findViewById(R.id.assets_PieChart);
        AssetsPieChart assetsPieChart1 = new AssetsPieChart(getContext());
        PieData pieData = assetsPieChart1.getPieData(3, 100);
        assetsPieChart1.showChart(assetsPieChart,pieData);

        popupWindow.setContentView(inflate);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(inflate,Gravity.BOTTOM,0,0);
    }

    //柱状图
    private void HistogramOnclick(){
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.assets_histogram, null);
        PopupWindow popupWindow = new PopupWindow();
        BarChart assetsBarChart = inflate.findViewById(R.id.assets_BarChart);
        AssetsBarChart assetsBarChart1 = new AssetsBarChart(assetsBarChart);
        BarData barData = new BarData(assetsBarChart1.getXAxisValues(), assetsBarChart1.getDataSet());
        assetsBarChart.setData(barData);
        popupWindow.setContentView(inflate);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(inflate,Gravity.BOTTOM,0,0);

    }

    //折线图
    private void LineCharOnClick(){
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.assets_linechar, null);
        PopupWindow popupWindow = new PopupWindow();
        LineChart assetsLineChart = inflate.findViewById(R.id.assets_LineChart);
        LineData lineData = AssetsLineChart.getLineData(10, 30);
        AssetsLineChart.showChart(assetsLineChart,lineData, Color.rgb(114, 188, 223));

        popupWindow.setContentView(inflate);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(inflate,Gravity.BOTTOM,0,0);

    }

    //登录popupWindow
    private void Login() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("你还没有登录,请点击登录");
        builder.setTitle("登录");
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginBus();
            }
        });
        builder.show();
    }

    /**
     * 登录业务
     */
    private void LoginBus() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.login, null);
        ImageView loginBack = inflate.findViewById(R.id.login_back);
        EditText loginName = inflate.findViewById(R.id.login_name);
        EditText loginPwd = inflate.findViewById(R.id.login_pwd);
        Button loginBtn = inflate.findViewById(R.id.login_btn);
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setFocusable(true);
        LoginBusOnclick(popupWindow,loginBack,loginName,loginPwd,loginBtn);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(inflate,Gravity.BOTTOM,0,0);
    }
    /*
    登录业务点击事件
     */
    private void LoginBusOnclick(final PopupWindow popupWindow, ImageView loginBack, final EditText loginName, final EditText loginPwd, Button loginBtn) {

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(loginName.getText());
                String pwd = String.valueOf(loginPwd.getText());
                //判断是否输入密码和手机号
                if (name.length()<=0&&pwd.length()<=0){
                    Toast.makeText(getContext(), "请输入手机号或密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter = new LoginPresenter(new LoginModel(),AssetsFragment.this);
                mPresenter.login(name,pwd);
            }
        });
    }
    /**
     * 提现点击事件
     */
    private void WithdrawDepositOnclick(final PopupWindow popupWindow, ImageView assetsWithdrawDepositPopBack, final EditText assetsWithdrawDepositPopMoney, TextView assetsWithdrawDepositPopBalance, final Button assetsWithdrawDepositPopTopUpBtn){
        assetsWithdrawDepositPopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        assetsWithdrawDepositPopTopUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.valueOf(assetsWithdrawDepositPopMoney.getText());
                Message message = new Message();
                message.what = 1;
                message.obj = text;
                handler.sendMessage(message);
            }
        });
        assetsWithdrawDepositPopMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    assetsWithdrawDepositPopTopUpBtn.setEnabled(true);
                    assetsWithdrawDepositPopTopUpBtn.setBackgroundResource(R.drawable.btn_01);
                }else {
                    assetsWithdrawDepositPopTopUpBtn.setEnabled(false);
                    assetsWithdrawDepositPopTopUpBtn.setBackgroundResource(R.drawable.btn_02);
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                String a = (String) msg.obj;
                int i = Integer.parseInt(a);
                sum+=i;
                assetsWithdrawDepositPopBalance.setText(sum+"元");
            }
        }
    };
    /*结果回调*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 图片回调结果处理
         */
        if (requestCode == 0) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean.isCut()) {
                    Context context = getContext();
                    if (context!=null){
                        Glide.with(context).load(pictureBean.getPath()).transform(new CircleCrop()).into(userImg);
                        Log.e("获取到的图片地址", "onActivityResult: "+pictureBean.getPath()+":");
                        //上传图片
                        OkGo.<String>post("http://49.233.93.155:8080/upload").params("MultipartFile",pictureBean.getPath()).execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String body = response.body();
                                Log.e("么么么么么么么么吗", "onSuccess: " +body);
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                Log.e("数据上传失败", "onError: ");
                            }
                        });

                    }

                }
            }
        }
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
    }


    @Override
    public void initInJect() {
    }


    @Override
    public int BandLayout() {
        return R.layout.main_assets_layout_fragment;
    }



    /**
     * 换沉浸式布局
     * @return
     */
    @TargetApi(23)
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 登录成功处理方法
     */
    @Override
    public void success() {
        Toast.makeText(getContext(), "登录成功请手动返回上一级页面", Toast.LENGTH_SHORT).show();
        String a = "http://49.233.93.155:8080/downloadFile";
        OkGo.<Bitmap>get(a).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Response<Bitmap> response) {
                Bitmap body = response.body();
                Log.e("么么么么么", "onSuccess: "+ body);
                Glide.with(getContext()).load(body).transform(new CircleCrop()).into(userImg);
            }

            @Override
            public void onError(Response<Bitmap> response) {
                super.onError(response);
                Log.e("失败", "onError: "+response.body() );
            }
        });


    }
}
