package com.example.baselibrary.mvp.model;

import io.reactivex.disposables.Disposable;

public class BaseModel implements IModel {
    protected Disposable gcDisposable;

    @Override
    public void onDestroy() {
        if (gcDisposable != null) {
            gcDisposable = null;
        }
    }
}
