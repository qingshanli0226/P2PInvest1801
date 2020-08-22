package com.p2p.bawei.p2pinvest1801.mvp.view.fra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.act.MainActivity;
import com.p2p.bawei.p2pinvest1801.zdyview.GestureView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import io.reactivex.functions.Consumer;

public class Fragment4 extends Fragment {
    View view;
    private TextView f4Reg;
    private TextView f4f4;
    private String phoneStr;
    private String usernameStr;
    private String pwdStr;
    private String pwddStr;
    private TextView f3Kf;
    private TextView f3Fk;
    private Switch switchView;
    private TextView f4Share;
    private TextView f4About;
    private TextView f4Resrt;
    private String url="https://p2.ssl.qhimgs1.com/sdr/400__/t015215ac314aa5d77c.jpg";
//    private GestureView gestureView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.f4,container,false);
        f4f4 = view.findViewById(R.id.f4f4);
        f4Reg = view.findViewById(R.id.f4_reg);
        f3Kf = view.findViewById(R.id.f3_kf);
        f3Fk = view.findViewById(R.id.f3_fk);
        f4Share = view.findViewById(R.id.f4_share);
        f4About = view.findViewById(R.id.f4_about);
        switchView = view.findViewById(R.id.switch_view);
        f4Resrt =  view.findViewById(R.id.f4_resrt);
        f4Resrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!switchView.isChecked()){
                    Toast.makeText(getContext(), "请打开手势开关", Toast.LENGTH_SHORT).show();
                }else {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop5_1, null, false);
                    PopupWindow popupWindow1 = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,true);
                    popupWindow1.setTouchable(true);
                    popupWindow1.showAtLocation(switchView, Gravity.BOTTOM,0,0);
                    PatternLockView mPatternLockView = inflate.findViewById(R.id.patter_lock_view);
                    PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
                        @Override
                        public void onStarted() {

                        }

                        @Override
                        public void onProgress(List<PatternLockView.Dot> progressPattern) {

                        }

                        @Override
                        public void onComplete(List<PatternLockView.Dot> pattern) {
                            String patternToString = PatternLockUtils.patternToString(mPatternLockView, pattern);
                            if(!TextUtils.isEmpty(patternToString)){
                                if(patternToString.length()>=4){
                                    popupWindow1.dismiss();
                                    Toast.makeText(getContext(), "手势密码设置成功", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getContext(), "请最少绘制4个", Toast.LENGTH_SHORT).show();
                                    pattern.clear();
                                }

                            }
                        }

                        @Override
                        public void onCleared() {

                        }
                    };
                    mPatternLockView.addPatternLockListener(mPatternLockViewListener);
                }
            }
        });

        f4About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop6, null, false);
                PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,true);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(f4f4, Gravity.BOTTOM,0,0);
                ImageView viewById = inflate.findViewById(R.id.f3_pop6_back);
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        //分享
        f4Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "分享", Toast.LENGTH_SHORT).show();
                UMImage umImage = new UMImage(getContext(), url);
//                new ShareAction(getActivity()).withText("hello").withMedia(umImage).setCallback(shareListener).open();
                new ShareAction(getActivity()).withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
            }
        });
        //手势锁
        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getContext(), "打开手势密码", Toast.LENGTH_SHORT).show();
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop5, null, false);
                    PopupWindow popupWindow = new PopupWindow(inflate,700,400,true);
                    popupWindow.setTouchable(true);
                    popupWindow.showAtLocation(switchView, Gravity.BOTTOM,0,700);
                    Button p5na = inflate.findViewById(R.id.f3_pop5_na);
                    Button p5post = inflate.findViewById(R.id.f3_pop5_post);
                    p5na.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            switchView.setChecked(false);
                        }
                    });
                    p5post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop5_1, null, false);
                            PopupWindow popupWindow1 = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,true);
                            popupWindow1.setTouchable(true);
                            popupWindow1.showAtLocation(switchView, Gravity.BOTTOM,0,0);
                            PatternLockView mPatternLockView = inflate.findViewById(R.id.patter_lock_view);
                            PatternLockView mPatternLocklittleView = inflate.findViewById(R.id.patter_lock_little_view);
                            PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
                                @Override
                                public void onStarted() {
                                    Log.d(getClass().getName(), "Pattern drawing started");
                                }

                                @Override
                                public void onProgress(List<PatternLockView.Dot> progressPattern) {
                                    Log.d(getClass().getName(), "Pattern progress: " +
                                            PatternLockUtils.patternToString(mPatternLockView, progressPattern));
                                }

                                @Override
                                public void onComplete(List<PatternLockView.Dot> pattern) {
                                    Log.d(getClass().getName(), "Pattern complete: " +PatternLockUtils.patternToString(mPatternLockView, pattern));
//                                    判断是否成功：24678
//                                    String mima = "24678";
                                    String patternToString = PatternLockUtils.patternToString(mPatternLockView, pattern);
//                                    String patternToString1 = PatternLockUtils.patternToString(mPatternLocklittleView, pattern);
                                    if(!TextUtils.isEmpty(patternToString)){
                                        if(patternToString.length()>=4){
//                                            patternToString1=patternToString;
                                            popupWindow1.dismiss();
                                            popupWindow.dismiss();
                                            Toast.makeText(getContext(), "手势密码设置成功", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getContext(), "请最少绘制4个", Toast.LENGTH_SHORT).show();
                                            pattern.clear();
                                        }

                                    }

//                                    if(!TextUtils.isEmpty(patternToString)){
//                                        if(patternToString.equals(mima)){
//                                            Toast.makeText(getContext(),"您绘制的密码是："+patternToString+"\n"+"密码正确，正在进入首页...",Toast.LENGTH_SHORT).show();
//
//                                        }else {
//                                            Toast.makeText(getContext(),"您绘制的密码是："+patternToString+"\n"+"密码错误，请重新绘制",Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
                                }

                                @Override
                                public void onCleared() {
                                    Log.d(getClass().getName(), "Pattern has been cleared");
                                    mPatternLockView.clearPattern();
                                }
                            };

//                            mPatternLockView.setDotCount(3);
//                            mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(getContext(), R.dimen.pattern_lock_dot_size));
//                            mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(getContext(), R.dimen.pattern_lock_dot_selected_size));
//                            mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(getContext(), R.dimen.pattern_lock_path_width));
//                            mPatternLockView.setAspectRatioEnabled(true);
//                            mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
//                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
//                            mPatternLockView.setDotAnimationDuration(150);
//                            mPatternLockView.setPathEndAnimationDuration(100);
//                            mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(getContext(), R.color.white));
//                            mPatternLockView.setInStealthMode(false);
//                            mPatternLockView.setTactileFeedbackEnabled(true);
//                            mPatternLockView.setInputEnabled(true);
                            mPatternLockView.addPatternLockListener(mPatternLockViewListener);
                            mPatternLocklittleView.addPatternLockListener(mPatternLockViewListener);

//                            RxPatternLockView.patternComplete(mPatternLockView)
//                                    .subscribe(new Consumer<PatternLockCompleteEvent>() {
//                                        @Override
//                                        public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
//                                            Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
//                                        }
//                                    });
//
//                            RxPatternLockView.patternChanges(mPatternLockView)
//                                    .subscribe(new Consumer<PatternLockCompoundEvent>() {
//                                        @Override
//                                        public void accept(PatternLockCompoundEvent event) throws Exception {
//                                            if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
//                                                Log.d(getClass().getName(), "Pattern drawing started");
//                                            } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
//                                                Log.d(getClass().getName(), "Pattern progress: " +
//                                                        PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
//                                            } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
//                                                Log.d(getClass().getName(), "Pattern complete: " +
//                                                        PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
//                                            } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
//                                                Log.d(getClass().getName(), "Pattern has been cleared");
//                                            }
//                                        }
//                                    });


                        }
                    });
                }else {
                    Toast.makeText(getContext(), "关闭手势密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册
        f4Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop2, null, false);
                PopupWindow popupWindow = new PopupWindow(inflate,1200,2000,true);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(f4f4, Gravity.BOTTOM,0,0);
                EditText phone = inflate.findViewById(R.id.f3_pop_phone);
                EditText username = inflate.findViewById(R.id.f3_pop_username);
                EditText pwd = inflate.findViewById(R.id.f3_pop_pwd);
                EditText pwdd = inflate.findViewById(R.id.f3_pop_pwdd);
                ImageView back = inflate.findViewById(R.id.f3_pop2_back);
                Button btn2 = inflate.findViewById(R.id.f3_pop_btn2);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        phoneStr=phone.getText().toString().trim();
                        usernameStr=username.getText().toString().trim();
                        pwdStr=pwd.getText().toString().trim();
                        pwddStr=pwdd.getText().toString().trim();

                        //密码进行加密
                        try {
                            MessageDigest instance = MessageDigest.getInstance("MD5");
                            instance.update(pwdStr.getBytes());
                            byte[] digest = instance.digest();
                            StringBuffer stringBuffer = new StringBuffer();
                            for (byte b:digest){
                                int i=(b & 0xFF);
                                if(i<0x10){
                                    stringBuffer.append("0");
                                }
                                stringBuffer.append(Integer.toHexString(i));
                            }
                            Toast.makeText(getContext(), ""+stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        if(!pwdStr.equals(pwddStr)){
                            Toast.makeText(getContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
                            pwd.setText("");
                            pwdd.setText("");
                        }else {
                            popupWindow.dismiss();
                        }
                    }
                });
            }
        });
        //客服
        f3Kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop3, null, false);
                PopupWindow popupWindow = new PopupWindow(inflate,700,400,true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(f3Kf, Gravity.BOTTOM,0,600);
                Button na = inflate.findViewById(R.id.f3_pop3_na);
                Button post = inflate.findViewById(R.id.f3_pop3_post);
                na.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        call("010-56253825");
                    }
                });
            }
        });
        //用户反馈
        f3Fk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.f3_pop4, null, false);
                PopupWindow popupWindow = new PopupWindow(inflate,700, ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(f3Kf, Gravity.BOTTOM,0,600);
                Button na = inflate.findViewById(R.id.f3_pop4_na);
                Button post = inflate.findViewById(R.id.f3_pop4_post);
                na.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "已经提交，等待处理", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
            }
        });
        return view;
    }

    private void call(String s) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+s));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };
}
