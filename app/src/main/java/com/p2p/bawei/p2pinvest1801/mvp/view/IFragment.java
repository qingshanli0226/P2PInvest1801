package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.view.View;

import androidx.annotation.IdRes;

public interface IFragment extends IActivity {

    <V extends View> V findViewById(@IdRes int id);

}
