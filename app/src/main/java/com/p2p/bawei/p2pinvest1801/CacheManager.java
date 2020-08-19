package com.p2p.bawei.p2pinvest1801;

import com.example.net.activity_bean.IndexBean;
import com.example.net.activity_bean.InvestBean;
import com.example.net.activity_bean.UpdateBean;

public class CacheManager {
    private CacheManager() {
    }
    private static CacheManager instance;
    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }
    private IndexBean indexBean;
    private UpdateBean updateBean;
    private InvestBean investBean;

    public InvestBean getInvestBean() {
        return investBean;
    }

    public void setInvestBean(InvestBean investBean) {
        this.investBean = investBean;
    }

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexBean) {
        this.indexBean = indexBean;
    }

    public UpdateBean getUpdateBean() {
        return updateBean;
    }

    public void setUpdateBean(UpdateBean updateBean) {
        this.updateBean = updateBean;
    }
}
