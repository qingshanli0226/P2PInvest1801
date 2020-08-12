package com.p2p.bawei.p2pinvest1801.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bw.framwork.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.fragment.HomePageFragment;
import com.p2p.bawei.p2pinvest1801.fragment.InvestmentFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MindFragment;
import com.p2p.bawei.p2pinvest1801.fragment.MoreFragment;

public class MainActivity extends BaseActivity {    //王俊豪练习项目

    private FragmentTransaction transaction;
    @Override
    public void initView() {
        setFragment(0);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    private HomePageFragment homePageFragment;
    private InvestmentFragment investmentFragment;
    private MindFragment mindFragment;
    private MoreFragment moreFragment;

    private void setFragment(int i){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        transaction = supportFragmentManager.beginTransaction();

        hideFragment();
        switch (i){
            case 0:
                if (homePageFragment==null){
                    homePageFragment=new HomePageFragment();
                    transaction.add(R.id.main_vp,homePageFragment);
                }

                transaction.show(homePageFragment);
                break;
            case 1:
                if (investmentFragment==null){
                    investmentFragment=new InvestmentFragment();
                    transaction.add(R.id.main_vp,investmentFragment);
                }

                transaction.show(investmentFragment);

                break;
            case 2:
                if (mindFragment==null){
                    mindFragment=new MindFragment();
                    transaction.add(R.id.main_vp,mindFragment);
                }

                transaction.show(mindFragment);
                break;
            case 3:
                if (moreFragment==null){
                    moreFragment=new MoreFragment();
                    transaction.add(R.id.main_vp,moreFragment);
                }

                transaction.show(moreFragment);
                break;
        }

        transaction.commit();
    }

    private void hideFragment(){
        if (homePageFragment!=null){
            transaction.hide(homePageFragment);
        }

        if (investmentFragment!=null){
            transaction.hide(investmentFragment);
        }

        if (mindFragment!=null){
            transaction.hide(mindFragment);
        }

        if (moreFragment!=null){
            transaction.hide(moreFragment);
        }
    }

}
