package com.p2p.bawei.p2pinvest1801.welcome.presenter;

import android.util.Log;

import com.example.common.AppCode;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.welcome.center.WelContract;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WelComePresenter extends BasePresenter<WelContract.View, WelContract.Model> {


    public WelComePresenter(WelContract.View mView, WelContract.Model mModel) {
        super(mView, mModel);
    }

    public void getData() {
        mModel.getAppCode(new BaseObserver<WelComeUpAppBean>() {
            @Override
            public void success(WelComeUpAppBean welComeUpAppBean) {
                String code = welComeUpAppBean.getResult().getVersion();
                String version = AppCode.getInstance().getVersion();
                Log.e("hq", "success: "+code+version );
                if (!code.equals(version)){
                    mView.upAppCode(welComeUpAppBean.getResult());
                }else {
                    mView.upAppCode(null);
                }
            }

            @Override
            public void error(String errorMessage) {
                Log.e("hq", "error: "+errorMessage );
            }
        });
    }
    public void downLoadApp(){
        mModel.DownLoadApp(new BaseObserver<RequestBody>() {
            @Override
            public void success(RequestBody requestBody) {
                try {
                    long contentLength = requestBody.contentLength();
                    Log.e("hq", "success: "+contentLength );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
