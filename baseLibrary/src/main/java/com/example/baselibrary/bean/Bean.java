package com.example.baselibrary.bean;


public class Bean {
    private String title;
    private int number;

    public Bean(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


}
