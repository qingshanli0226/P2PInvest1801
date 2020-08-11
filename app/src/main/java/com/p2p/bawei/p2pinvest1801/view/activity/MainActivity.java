package com.p2p.bawei.p2pinvest1801.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyFragmentAdapter;
import com.p2p.bawei.p2pinvest1801.view.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup tabLayout;


    private List<Fragment> list_fragment = new ArrayList<>();

    private MyFragmentAdapter myFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
        initView();


        Log.d("LQS", "1801学习git");
        Log.d("LQS", "1801wby.....");
    }

    private void initTab() {
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());


    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
       
        tabLayout = (RadioGroup) findViewById(R.id.tabLayout);

        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),list_fragment);
        viewPager.setAdapter(myFragmentAdapter);

        tabLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button_1:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.button_2:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.button_3:
                        viewPager.setCurrentItem(2);
                        break;

                    case R.id.button_4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });


    }
}
