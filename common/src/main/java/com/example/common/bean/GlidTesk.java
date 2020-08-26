package com.example.common.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class GlidTesk {
    private String url;
    private ImageView imageView;
    private Bitmap bitmap;

    public GlidTesk() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
