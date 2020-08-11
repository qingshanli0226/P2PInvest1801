package com.p2p.bawei.p2pinvest1801.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class DaoBean {
    @Id(autoincrement = true)
    Long ida;
    private String id;
    private String title;
    private String pic;
    private String collect_num;
    private String food_str;
    private int num;

    @Generated(hash = 586775997)
    public DaoBean(Long ida, String id, String title, String pic,
            String collect_num, String food_str, int num) {
        this.ida = ida;
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.collect_num = collect_num;
        this.food_str = food_str;
        this.num = num;
    }

    @Generated(hash = 405743142)
    public DaoBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getFood_str() {
        return food_str;
    }

    public void setFood_str(String food_str) {
        this.food_str = food_str;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Long getIda() {
        return this.ida;
    }

    public void setIda(Long ida) {
        this.ida = ida;
    }
}
