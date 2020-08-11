package com.example.framwork.mvp.view;

import android.view.View;

import androidx.annotation.IdRes;

public interface IFragment extends IActivity {
    <T extends View> T findViewById(@IdRes int id);
}
