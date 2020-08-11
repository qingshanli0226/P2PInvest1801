package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.support.annotation.IdRes;
import android.view.View;


public interface IFragment extends IActivity {

    <V extends View> V findViewById(@IdRes int id);

}
