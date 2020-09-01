package com.p2p.bawei.p2pinvest1801.invest.adapter;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {

    public static int randomBeautifulColor() {


        Random random = new Random();
        int red = random.nextInt(150);
        int blue  = random.nextInt(150);
        int green  = random.nextInt(150);

        return Color.rgb(red, blue, green);

    }



}
