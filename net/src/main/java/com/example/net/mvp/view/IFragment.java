package com.example.net.mvp.view;

import android.support.annotation.IdRes;
import android.view.View;


public interface IFragment extends IActivity{
    //findViewById
    public <T extends View> T findViewById(@IdRes int id);

}
