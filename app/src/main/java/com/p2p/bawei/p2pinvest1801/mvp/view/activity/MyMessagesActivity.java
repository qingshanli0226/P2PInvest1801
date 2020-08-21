package com.p2p.bawei.p2pinvest1801.mvp.view.activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.common.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import static com.wildma.pictureselector.PictureSelector.PICTURE_RESULT;
public class MyMessagesActivity extends BaseActivity {
    private TextView chanagepic;
    private ImageView pic;
    @Override
    public int BondLayout() {
        return R.layout.myfragment_messages_activity;
    }
    @Override
    public void initview() {
        chanagepic = (TextView) findViewById(R.id.chanagepic);
        pic = (ImageView) findViewById(R.id.pic);
    }
    @Override
    public void initdata() {
        chanagepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(MyMessagesActivity.this,100).selectPicture(true);

            }
        });
    }
    @Override
    public void initInJect() {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&data!=null){
            PictureBean o= (PictureBean) data.getExtras().get(PICTURE_RESULT);
            if (o.getPath()!=null&&o!=null){
                Glide.with(MyMessagesActivity.this).load(o.getPath())
                        .transform(new CircleCrop())
                        .into(pic);
            }
        }
    }
}
