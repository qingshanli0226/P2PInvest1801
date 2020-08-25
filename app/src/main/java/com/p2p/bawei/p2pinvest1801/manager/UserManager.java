package com.p2p.bawei.p2pinvest1801.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.net.RetrofitManager;
import com.example.net.bean.AutologinBean;
import com.p2p.bawei.p2pinvest1801.utils.EncryptUtil;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserManager {

    private static UserManager userManager;

    public UserManager() {
    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static UserManager getUserManager() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public void init(final Context context) {
        sharedPreferences = CacheManager.getCacheManager().getSharedPreferences();
        editor = CacheManager.getCacheManager().getEditor();

        boolean isLogin = sharedPreferences.getBoolean(InvestConstant.SP_ISLOGIN, false);

        //如果登录过
        if (isLogin) {
            TreeMap<String, String> params = new TreeMap<>();
            params.put("token", CacheManager.getCacheManager().getSharedPreferences().getString(InvestConstant.SP_TOKEN, ""));

            //拼接
            StringBuilder sb = new StringBuilder();
            for (String key : params.keySet()) {
                String value = params.get(key);
                sb.append(key + "=" + value + "&");
            }

            sb.append("encrypt=md5");
            //进行信息摘要(MD5)
            String sign = EncryptUtil.enrypByMd5(sb.toString());
            //加入TreeMap中
            params.put("sign", sign);
            //生成Base64暗文传输
            TreeMap<String, String> stringStringTreeMap = EncryptUtil.encryptParamsValueByBase64(params);

            RetrofitManager.getInvestApiService()
                    .onAutologin(stringStringTreeMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AutologinBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AutologinBean autologinBean) {
                            Log.d("---", "onNext: ---->" + autologinBean.getCode() + autologinBean.getMessage());
                            if (autologinBean.getCode().equals("200")) {
                                Toast.makeText(context, "自动登录成功", Toast.LENGTH_SHORT).show();
                                //把token存入SP文件中
                                Log.i("hj", "onNext: " + autologinBean.getResult().getToken());
                                editor.putString(InvestConstant.SP_TOKEN, autologinBean.getResult().getToken());
                                editor.commit();
                            } else {
                                Toast.makeText(context, "" + autologinBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }


}
