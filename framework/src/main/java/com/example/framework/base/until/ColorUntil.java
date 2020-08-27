package com.example.framework.base.until;

import android.graphics.Color;

import java.util.Random;

public class ColorUntil {
    //颜色随机生成器

    public static int randomBeautifulColor(){
        Random random = new Random();
        //值小一点--这样随机生成的颜色尽量都比较暗(因为在较亮的背景上--亮色看不明显)
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return Color.rgb(red,green,blue);
    }
}
