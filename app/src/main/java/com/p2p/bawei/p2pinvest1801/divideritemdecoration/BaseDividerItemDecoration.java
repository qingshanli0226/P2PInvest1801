package com.p2p.bawei.p2pinvest1801.divideritemdecoration;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class BaseDividerItemDecoration extends Y_DividerItemDecoration {

    public BaseDividerItemDecoration(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        switch (itemPosition % 2) {
            case 0:
                //每一行第一个显示right和bottom

                divider = new Y_DividerBuilder()
                        .setTopSideLine(false, Color.BLUE,0,0,0)
                        .setLeftSideLine(false, Color.BLUE,0,0,0)
                        .setRightSideLine(false,Color.BLUE,0,0,0)
                        .setBottomSideLine(true, Color.BLUE, 10, 0, 0)
                        .create();
                break;
            case 1:
                //第二个显示Left和bottom
                divider = new Y_DividerBuilder()
                        .setTopSideLine(true, Color.BLUE,0,0,0)
                        .setLeftSideLine(true, Color.BLUE,0,0,0)
                        .setRightSideLine(true,Color.BLUE,0,0,0)
                        .setBottomSideLine(true, Color.BLUE, 10, 0, 0)
                        .create();
                break;
            default:
                break;
        }
        return divider;
    }

}
