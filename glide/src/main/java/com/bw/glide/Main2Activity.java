package com.bw.glide;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bw.framwork.view.BaseActivity;
import com.bw.glide.mode.DxBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity {

    private ArrayList<DxBean.DataBean> data=new ArrayList<>();
    private DxAdapter adapter;
    private RecyclerView rv;

    @Override
    public void initView() {
        rv = findViewById(R.id.rv);

        adapter=new DxAdapter(R.layout.images,data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        OkGo.<String>get("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        DxBean dxBean = JSON.parseObject(body, DxBean.class);
                        data.clear();
                        data.addAll(dxBean.getData());
                        adapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }
}
