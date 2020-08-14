package com.p2p.bawei.p2pinvest1801.adapter.divider;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class MyDivider extends Y_DividerItemDecoration {
    public MyDivider(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        switch((itemPosition)%2){
            case 0:
                return new Y_DividerBuilder()
                        .setTopSideLine(false, Color.BLUE,0,0,0)
                    .setBottomSideLine(true, Color.BLUE,10,0,0)
                    .setLeftSideLine(false, Color.BLUE,0,0,0)
                    .setRightSideLine(false, Color.BLUE,0,0,0).create();
            case 1:
                return new Y_DividerBuilder()
                        .setTopSideLine(true, Color.BLUE,0,0,0)
                        .setBottomSideLine(true, Color.BLUE,10,0,0)
                        .setLeftSideLine(true, Color.BLUE,0,0,0)
                        .setRightSideLine(true, Color.BLUE,0,0,0).create();
            default:
                return new Y_DividerBuilder().create();

        }

    }
}
