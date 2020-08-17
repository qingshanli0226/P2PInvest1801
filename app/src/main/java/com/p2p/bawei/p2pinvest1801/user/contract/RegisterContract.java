package com.p2p.bawei.p2pinvest1801.user.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;

public class RegisterContract {

    public interface IRgisterView extends IView {
        void onRegister(String registerResult);
    }

    public static abstract class RegisterPresenter extends BasePresenter<IRgisterView> {
        public abstract void getRegister(String name,String password,String phone);
    }

}
