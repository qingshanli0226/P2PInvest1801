package com.bw.lib_core.view;

import android.support.annotation.IdRes;
import android.view.View;

public interface IFragment extends IActivity {

    <T extends View> T findViewById(@IdRes int id);
}
