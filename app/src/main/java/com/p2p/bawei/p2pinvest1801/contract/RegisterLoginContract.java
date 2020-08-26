package com.p2p.bawei.p2pinvest1801.contract;

import com.example.framework.base.BasePresenter;
import com.example.framework.base.IView;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.example.net.mode.UploadBean;

import java.io.File;

public class RegisterLoginContract {

    public interface RegisterLoginView extends IView{
        void onRegisterData(RegisterBean registerBean);
        void onLoginData(LoginBean loginBean);
        void onLoginOutData(UnLoginBean unLoginBean);
        void uploadData(UploadBean uploadBean);
    }

    public static abstract class RegisterLoginPresenter extends BasePresenter<RegisterLoginView>{
        public abstract void onGetRegister(String name, String password);
        public abstract void onGetLogin(String name, String password);
        public abstract void onLoginOut();
        public abstract void upload(File file);
    }

}
