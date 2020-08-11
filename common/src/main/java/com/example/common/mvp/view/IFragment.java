package com.example.common.mvp.view;

public interface IFragment extends IActivity {
    <T extends android.view.View> T findViewById(@android.support.annotation.IdRes int id);
}
