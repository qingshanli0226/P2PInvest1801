package com.p2p.bawei.p2pinvest1801.user.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.LoginBean;

public class LoginContract {

    public interface ILoginView extends IView {
        void onLogin(LoginBean loginResult);
    }

    public static abstract class LoginPresenter extends BasePresenter<ILoginView> {
        public abstract void getLogin(String name,String password);
    }

}
