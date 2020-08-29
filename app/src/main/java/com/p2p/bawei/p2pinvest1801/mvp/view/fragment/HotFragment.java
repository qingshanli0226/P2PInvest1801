package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framwork.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyFlow;
public class HotFragment extends BaseFragment {
    private String mNames[] = {
            "welcome","android","TextView",
            "apple","jamy","kobe bryant",
            "jordan","layout","viewgroup",
            "margin","padding","text",
            "name","type","search","logcat"
    };
    int[] colors = new int[]{Color.BLUE,Color.BLACK,Color.GRAY,Color.GREEN};
    private MyFlow flowlayout;
    @Override
    public void initViews() {
        flowlayout = (MyFlow) findViewById(R.id.flowlayout);
    }
    @Override
    public void initDatas() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(getContext());
            view.setText(mNames[i]);
            view.setTextSize(50);
            view.setTextColor(colors[(int) (colors.length * Math.random())]);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowlayout.addView(view,lp);
        }
    }

    @Override
    public int bandLayout() {
        return R.layout.hotfragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String code, String message) {
        Toast.makeText(getContext(), "code："+code+"错误信息："+message, Toast.LENGTH_SHORT).show();
    }
}
