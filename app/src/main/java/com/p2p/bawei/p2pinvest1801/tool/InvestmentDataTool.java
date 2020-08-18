package com.p2p.bawei.p2pinvest1801.tool;

import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;

import java.util.ArrayList;
import java.util.List;

public class InvestmentDataTool {

    public static List<InvestmentBean.ResultBean> list = new ArrayList<>();


    public static List<InvestmentBean.ResultBean> getList() {
        return list;
    }

    public static void setList(List<InvestmentBean.ResultBean> list) {
        InvestmentDataTool.list = list;
    }
}
