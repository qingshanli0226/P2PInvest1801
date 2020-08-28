package com.bw.common.glide;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

public class GlideTask implements Serializable {
    private ImageView imageView;
    private String url;
    private Bitmap bitmap;


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
