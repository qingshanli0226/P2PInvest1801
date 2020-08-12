package com.p2p.bawei.p2pinvest1801.mvp.view.fra;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.Share;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    View view;
    private Banner ban;
    private List<String> Blist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.f1,container,false);
        ban = view.findViewById(R.id.ban);
        intiban();
        return view;
    }
    private void intiban() {
        for(int i = 0; i < Share.list.size() ; i++){
            Blist.add(Share.list.get(i).getIMAURL());
        }
        ban.setImages(Blist);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }
}
