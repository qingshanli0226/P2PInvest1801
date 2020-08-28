package com.bw.glide;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bw.glide.mode.DxBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DxAdapter extends BaseQuickAdapter<DxBean.DataBean, BaseViewHolder> {
    public DxAdapter(int layoutResId, @Nullable List<DxBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DxBean.DataBean item) {
        final GlideTask glideTask = new GlideTask();
        final ImageView imageView = helper.getView(R.id.img);

        imageView.setTag(item.getPic());
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        glideTask.setImageView(imageView);
        glideTask.setUrl(item.getPic());


        GlideManager.getInstance().getBitmapFromDiskLrucache(item.getPic(), new GlideManager.IDiskLrucacheBitmap() {
            @Override
            public void onBitmap(Bitmap sampleBitmap) {
                if (sampleBitmap!=null){
                    imageView.setImageBitmap(sampleBitmap);
                }else {
                    GlideManager.getInstance().NetGlideTask(glideTask);
                }
            }
        });

    }
}
