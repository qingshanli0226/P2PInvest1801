package com.p2p.bawei.p2pinvest1801;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class GlideTask {
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
