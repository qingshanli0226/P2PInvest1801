package com.p2p.bawei.p2pinvest1801.mvp.view.act;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.next.easynavigation.view.EasyNavigationBar;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.Share;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.Fragment1;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.Fragment2;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.Fragment3;
import com.p2p.bawei.p2pinvest1801.mvp.view.fra.Fragment4;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SyActivity extends AppCompatActivity {
    private EasyNavigationBar bar;
    private String title[]={"首页","投资","我的资产","更多"};
    private int wei[]={R.drawable.bottom01,R.drawable.bottom03,R.drawable.bottom05,R.drawable.bottom07};
    private int zhong[]={R.drawable.bottom02,R.drawable.bottom04,R.drawable.bottom06,R.drawable.bottom08};
    private List<Fragment> flist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy);
        bar = findViewById(R.id.bar);
        flist.add(new Fragment1());
        flist.add(new Fragment2());
        flist.add(new Fragment3());
        flist.add(new Fragment4());
        bar.titleItems(title)
                .normalIconItems(wei)
                .selectIconItems(zhong)
                .fragmentList(flist)
                .fragmentManager(getSupportFragmentManager())
                .mode(EasyNavigationBar.NavigationMode.MODE_NORMAL)
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        bar.setMsgPointCount(1, 66);

                        bar.setHintPoint(3, true);
                    }
                })
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        bar.clearHintPoint(position);
                        bar.clearMsgPoint(position);
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        return false;
                    }
                })
                .build();


}


}
