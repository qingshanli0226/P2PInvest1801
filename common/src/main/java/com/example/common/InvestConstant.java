package com.example.common;

public class InvestConstant {
    //服务端基类接口
    public static final String BASE_URL = "http://49.233.93.155:9999/";
    //json接口
    public static final String BASE_RESOURCE_JSON_URL = BASE_URL + "atguigu/json/";

    //SharedPreferences管理的key值
    //用户名
    public static final String SP_USERNAME = "name";
    //用户密码
    public static final String SP_PASSWORD = "password";
    //token
    public static final String SP_TOKEN = "token";
    //头像图片
    public static final String SP_ICON = "icon";
    //手势密码是否打开
    public static final String SP_ISCHECKED = "isChecked";
    //手势密码图案
    public static final String SP_LOCK_STR = "lock_str";
    //是否登录
    public static final String SP_ISLOGIN = "isLogin" ;

    public static final String JSCON_ERROR_CODE = "1001";
    public static final String JSON_ERROR_MESSAGE = "服务端范湖数据解析错误";

    public static final String HTTP_ERROR_CODE = "1002";
    public static final String HTTP_ERROR_MESSAGE = "网络错误";

    public static final String SECURITY_ERROR_CODE = "1003";
    public static final String SECURITY_ERROR_MESSAGE = "权限错误";

    public static final String USER_NOT_REGISTER_ERROR = "10000";

    public static final String SOCKET_TIMEOUT_ERROR_CODE = "1004";
    public static final String SOCKET_TIMEOUT_ERROR_MESSAGE = "连接超时错误";
}
