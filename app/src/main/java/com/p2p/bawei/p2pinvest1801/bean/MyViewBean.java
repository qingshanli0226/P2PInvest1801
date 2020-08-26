package com.p2p.bawei.p2pinvest1801.bean;

public class MyViewBean {
    private int color;
    private String title;
    private float x;
    private float y;
    private int textsize;

    public MyViewBean(int color, String title, float x, float y, int textsize) {
        this.color = color;
        this.title = title;
        this.x = x;
        this.y = y;
        this.textsize = textsize;
    }

    public int getTextsize() {
        return textsize;
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
