package com.p2p.bawei.p2pinvest1801.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutOne extends ViewGroup {

    //储存所以的View 按行记录
    private List<List<View>> mAllViews = new ArrayList<>();
    //记录每一行的高度
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayoutOne(Context context) {
        super(context);
    }



    public FlowLayoutOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayoutOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //当前行的最大高度
        int lineHeight = 0;
        //储存每一行的childView
        List<View> lineViews = new ArrayList<>();
        int left = 0;
        int top = 0;
        //得到总行数
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum ; i++) {
            //每一行所有的views
            lineViews = mAllViews.get(i);
            //当前行的最大高度
            lineHeight = mLineHeight.get(i);
            Log.i("onLayout" , "第" + i + "行 ：" + lineViews.size()+"-------lineHeight"+ lineHeight);
            
            //遍历当前行所有的view
            for (int j = 0; j < lineViews.size() ; j++) {
                View child = lineViews.get(j);
                if(child.getVisibility() == View.GONE){
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top = lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);

                left += child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;

            }

            left = 0;
            top += lineHeight;
            Log.d("onLayout", "onLayout   mAllViews.size() -- > " + mAllViews.size() + "   mLineHeight.size() -- > "+ mLineHeight.size());
        }
    }
//    generateLayoutParams


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //因为OnMeasure方法会走两次，第一次是实例化这个对象的时候高度和宽度都是0
        //之后走了OnSizeChange()方法后 又走了一次OnMeasure，所以要把第一次加进去的数据清空。
        mAllViews.clear();
        mLineHeight.clear();
        //得到上级容器为其推荐的宽高和计算模式
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        //计算所以child的宽和高
        measureChildren(specWidthSize,specHeightSize);
        //记录如果是wrap_content 时 设置的宽高
        int width = 0;
        int height = 0;
        //得到子View的个数
        int childCount = getChildCount();
        //记录每一行的宽度，width不断取最大宽度
        int lineWidth = 0;
        //每一行的高度，累加至height
        int lineHeight = 0;
        //储存每一行所有的childView（子View）
        List<View> lineViews = new ArrayList<>();
        //循环 子View的个数
        for (int i = 0; i < childCount ; i++) {
            //得到每个字View
            View childAt = getChildAt(i);
            //测量每个子View宽高
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            //当前子View的layoutParams
            MarginLayoutParams lp = (MarginLayoutParams) childAt.getLayoutParams();
            //子View的宽高
            int cWidth = 0;
            int cHeight = 0;
            //当前子View实际占据的宽高
            cWidth = childAt.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            cHeight = childAt.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            lineHeight = cHeight;
            //判断是否需要换行
            if(lineWidth + cWidth < specWidthSize){
                //取最大值
                width = Math.max(lineWidth,cWidth);
                //开始新行的时候重新累加height
                lineWidth = cWidth;
                //开始新行的时候重新累加height
                lineHeight = cHeight;//记录下一行的高度
                mAllViews.add(lineViews);
                mLineHeight.add(cHeight);
                lineViews = new ArrayList<>();
                //换行的时候把View放在集合里
                lineViews.add(childAt);//这个childAt是下一行的第一个View
                //每个View的高度一样  直接累加
                height += cHeight;
                Log.d("需要换行", "hight--" + height);
                Log.d("onMeasure", "AllViews.size()  --  > " + mAllViews.size());
            } else{
                //不需要换行
                lineWidth += cWidth;
                Log.d("不需要换行","hight--"+height);
                //不需要换行时  把view放进集合中
                lineViews.add(childAt);
            }

            //如果是最后一个View
            if(i == childCount - 1){
                width = Math.max(lineWidth,cWidth);
                height += cHeight;
                Log.d("最后一个view","hight--"+height);
            }
        }

        //循环结束后 把最后一行的内容放进集合中
        mLineHeight.add(lineHeight);//记录最后一行
        mAllViews.add(lineViews);

        // MeasureSpec.EXACTLY 表示设置了精确的值
        // 如果 mode 是 MeasureSpec.EXACTLY 时候，则不是 warp_content 用计算来的值，否则则用上级布局分给它的值
        setMeasuredDimension(
                specWidthMode == MeasureSpec.EXACTLY ? specWidthSize : width,
                specHeightMode == MeasureSpec.EXACTLY ? specHeightSize : height
        );
        Log.d("onMeasure", "mAllViews.size() -- > " + mAllViews.size() + "   mLineHeight.size() -- > " + mLineHeight.size() + "Height -- > "+height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
