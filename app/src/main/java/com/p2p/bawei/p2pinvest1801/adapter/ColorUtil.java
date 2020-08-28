package com.p2p.bawei.p2pinvest1801.adapter;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {

    /**
     * 生成漂亮的颜色
     * @return
     */
    public static int randomBeautifulColor() {
        //值小一点--这样随机生成的颜色尽量都比较暗(因为在较亮的背景上--亮色看不明显)
        Random random = new Random();
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return Color.rgb(red, green, blue);
    }
}
