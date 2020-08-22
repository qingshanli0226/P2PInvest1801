package com.p2p.bawei.p2pinvest1801.activity;

import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseMVPActivity;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.p2p.bawei.p2pinvest1801.CacheManager;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.presenter.RegisterLoginPresenterImpl;

public class UserMessageActivity extends BaseMVPActivity<RegisterLoginPresenterImpl, RegisterLoginContract.RegisterLoginView> implements RegisterLoginContract.RegisterLoginView {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private TextView userMessageTv;
    private Button unLoginButton;
    private LinearLayout userMessageView;

    @Override
    protected void initGetData() {

        //退出登录
        unLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断登录状态
                boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
                if(aBoolean){
                    //登录过 可以退出登录
                    //发起退出登录的网络请求
//                    iHttpPresenter.onLoginOut();
                    showMessage("退出登录成功");
                    //改变登录状态
                    editor.putBoolean(FinanceConstant.ISLOGIN,false);
                    editor.commit();
                    lunachActivity(MainActivity.class,null);
                } else{
                    //没有登录
                    showMessage("请你先登录");
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new RegisterLoginPresenterImpl();
    }

    @Override
    protected void initData() {
        //点击弹出popWindow选择框
        userMessageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow();
                View view = LayoutInflater.from(UserMessageActivity.this).inflate(R.layout.myview_pop, null);
                popupWindow.setContentView(view);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(1000);
                //设置点击外部可取消
                popupWindow.setOutsideTouchable(true);
                //显示位置
                popupWindow.showAtLocation(userMessageView, Gravity.CENTER,0,0);
            }
        });
    }

    @Override
    protected void initView() {
        //获取sp文件实例
        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        userMessageTv = (TextView) findViewById(R.id.userMessageTv);
        unLoginButton = (Button) findViewById(R.id.unLoginButton);
        userMessageView = (LinearLayout) findViewById(R.id.userMessageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {

    }

    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public void onLoginOutData(UnLoginBean unLoginBean) {

    }

    @Override
    public void showError(String code, String message) {
        showMessage(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
