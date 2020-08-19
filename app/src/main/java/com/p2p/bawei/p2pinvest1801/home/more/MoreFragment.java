package com.p2p.bawei.p2pinvest1801.home.more;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.p2p.bawei.p2pinvest1801.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private boolean isOpen=false;
    private ImageView more_toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        more_toggle=view.findViewById(R.id.more_toggle);
        more_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    isOpen=false;
                    more_toggle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.toggle_off));
                }else {
                    isOpen=true;
                    more_toggle.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.toggle_on));
                }
            }
        });
        return view;
    }

}
