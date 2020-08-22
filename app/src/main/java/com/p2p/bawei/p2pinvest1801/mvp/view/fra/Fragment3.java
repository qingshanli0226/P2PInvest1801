package com.p2p.bawei.p2pinvest1801.mvp.view.fra;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.zdyview.PColumn;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import static com.wildma.pictureselector.PictureSelector.SELECT_REQUEST_CODE;

public class Fragment3 extends Fragment {
    View view;
    private ImageView f3Tou;
    private Button f3Recharge;
    private Button f3Withdraw;
    private ImageView f3She;
    private String s;
    private String ends;
    private TextView f3Zhu;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.f3,container,false);
        f3Tou = view.findViewById(R.id.f3_tou);
        f3Recharge = view.findViewById(R.id.f3_recharge);
        f3Withdraw = view.findViewById(R.id.f3_withdraw);
        f3She = view.findViewById(R.id.f3_she);
        f3Zhu = view.findViewById(R.id.f3_zhu);
        //柱状图
        f3Zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop_zhu, null, false);
                PopupWindow popupWindowback = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,1400,true);
                popupWindowback.setOutsideTouchable(true);
                popupWindowback.setTouchable(true);
                popupWindowback.showAtLocation(f3She, Gravity.BOTTOM,0,0);
                PColumn one = inflate.findViewById(R.id.column_one);
                PColumn two = inflate.findViewById(R.id.column_two);
                PColumn three = inflate.findViewById(R.id.column_three);
                PColumn four = inflate.findViewById(R.id.column_four);
                PColumn five = inflate.findViewById(R.id.column_five);
                PColumn six = inflate.findViewById(R.id.column_six);
                PColumn seven = inflate.findViewById(R.id.column_seven);
                PColumn egiht = inflate.findViewById(R.id.column_egiht);
                PColumn nine = inflate.findViewById(R.id.column_nine);
                PColumn ten = inflate.findViewById(R.id.column_ten);
                PColumn three11 = inflate.findViewById(R.id.column_11);
                PColumn three12 = inflate.findViewById(R.id.column_12);
                one.setData(68,100);
                two.setData(32,100);
                three.setData(61,100);
                four.setData(71,100);
                five.setData(43,100);
                six.setData(82,100);
                seven.setData(76,100);
                egiht.setData(83,100);
                nine.setData(31,100);
                ten.setData(97,100);
                three11.setData(44,100);
                three12.setData(77,100);
                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindowback.dismiss();
                    }
                });
            }
        });

        //换头像
        f3Tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inittouxiang();
            }
        });

        //充值
        f3Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecharge();
            }
        });
        //提现
        return view;
    }

    private void initRecharge() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop1, null, false);
        PopupWindow popupWindow = new PopupWindow(inflate,1200,2400,true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(f3She, Gravity.BOTTOM,0,0);
        ImageView back = inflate.findViewById(R.id.f3_pop_back);
        EditText editText = inflate.findViewById(R.id.f3_pop_ed);
        TextView yue = inflate.findViewById(R.id.f3_pop_yu);
        Button btn = inflate.findViewById(R.id.f3_pop_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                s = editText.getText().toString();
                if(s.equals("")&&s.length()==0){

                    btn.setBackgroundResource(R.drawable.btn_02);
                    btn.setEnabled(false);
                }else {
                    btn.setEnabled(true);
                    btn.setBackgroundResource(R.drawable.btn_01);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                ends=yue.getText().toString().trim();
                int i = Integer.parseInt(ends);
                int i1 = Integer.parseInt(s);

                yue.setText(i+i1+"");
                editText.setText("");
            }
        });
    }

    private void inittouxiang() {
        PictureSelector.create(getActivity(), SELECT_REQUEST_CODE).selectPicture();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_REQUEST_CODE&&data!=null){
            PictureBean o = (PictureBean) data.getExtras().get(PictureSelector.PICTURE_RESULT);
            Glide.with(getContext()).load(o.getPath()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(f3Tou);
        }
    }

}
