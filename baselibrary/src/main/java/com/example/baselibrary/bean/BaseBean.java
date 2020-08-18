package com.example.baselibrary.bean;


public class BaseBean {


    /**
     * code : 200
     * msg : 请求成功
     * result : {"imageArr":[{"ID":"1","IMAPAURL":"http://fund.eastmoney.com/f10/jjjl_002939.html","IMAURL":"http://49.233.93.155:8080/atguigu/img/P2PInvest/index01.png"},{"ID":"2","IMAPAURL":"http://fund.eastmoney.com/519983.html","IMAURL":"http://49.233.93.155:8080/atguigu/img/P2PInvest/index02.png"},{"ID":"3","IMAPAURL":"http://www.gffunds.com.cn/funds/?fundcode=004997","IMAURL":"http://49.233.93.155:8080/atguigu/img/P2PInvest/index03.png"},{"ID":"5","IMAPAURL":"http://fund.eastmoney.com/002939.html","IMAURL":"http://49.233.93.155:8080/atguigu/img/P2PInvest/index04.png"}],"proInfo":{"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}}
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
