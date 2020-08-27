package com.example.framework.base.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.common.CacheManager;
import com.example.common.FinanceConstant;
import com.example.framework.base.until.EncryptUntil;
import com.example.net.FinanceManager;
import com.example.net.mode.AutoLoginBean;

import java.util.ArrayList;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserManager {

    //储存activity的集合
    private ArrayList<Activity> activityArrayList = new ArrayList<>();
    private String userName;
    private String version;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private UserManager(){}

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static UserManager userManager;

    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    public void init(final Context context){

        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        boolean aBoolean = sharedPreferences.getBoolean(FinanceConstant.ISLOGIN, false);
        if(aBoolean){
            //登陆过一次
            TreeMap<String,String> params = new TreeMap<>();
            params.put("token",CacheManager.getInstance().getSharedPreferences().getString(FinanceConstant.TOKEN,""));
            //拼接
            StringBuilder sb = new StringBuilder();
            for(String key : params.keySet()){
                String value = params.get(key);
                sb.append(key+"="+value+"&");
            }
            sb.append("encrypt=md5");
            //进行信息摘要(MD5)
            String sign = EncryptUntil.enrypByMd5(sb.toString());
            //加入TreeMap中
            params.put("sign",sign);
            //生成Base64暗文传输
            TreeMap<String, String> stringStringTreeMap = EncryptUntil.encryptParamsValueByBase64(params);

            //发起自动登录的网络请求
            FinanceManager.getInstance().getFinanceApi()
                    .autoLogin(stringStringTreeMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AutoLoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AutoLoginBean autoLoginBean) {
                            if(autoLoginBean.getCode().equals("200")){
                                Toast.makeText(context, "自动登录成功", Toast.LENGTH_SHORT).show();
                                //把token和登录状态存入SP文件中
                                Log.i("hj", "onNext: "+autoLoginBean.getResult().getToken());
                                editor.putString(FinanceConstant.TOKEN,autoLoginBean.getResult().getToken());
                                editor.putBoolean(FinanceConstant.ISLOGIN,true);
                                editor.commit();
                            } else{
                                Toast.makeText(context, ""+autoLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            //没有登录  进入主页面去登录
        }
    }

    //添加activity的方法
    public void addActivity(Activity activity){
        if(!activityArrayList.contains(activity)){
            activityArrayList.add(activity);
        }
    }
    //移除activity的方法
    public void removeActivity(Activity activity){
        if(activityArrayList.contains(activity)){
            activityArrayList.remove(activity);
        }
    }

    public ArrayList<Activity> getActivityArrayList() {
        return activityArrayList;
    }
}
