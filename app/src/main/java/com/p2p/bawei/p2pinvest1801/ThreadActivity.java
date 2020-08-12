package com.p2p.bawei.p2pinvest1801;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private int num = 0;

    private int countMax = 50000;
    private int countMax2 = 50000;
    private int countValue = 0;

    List<Date> dateList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        findViewById(R.id.btnWhy).setOnClickListener(this);
        findViewById(R.id.btnIssue).setOnClickListener(this);
        findViewById(R.id.btnTime).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWhy:
                useManyThread();
                break;
            case R.id.btnIssue:
                useManyThreadIssue();
                break;
            case R.id.btnTime:
                useManyThreadTime();

                break;
        }
    }
    int value1 = 0;
    int value2 = 0;

    private void useManyThreadTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    value1++;
                    if (value1 == 10000) {
                        synchronized (ThreadActivity.this) {
                            taskEndNum++;
                            if (taskEndNum == 2) {
                                handler.sendEmptyMessage(1);
                            }
                        }
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    value2++;
                    if (value2 == 10000) {
                        synchronized (ThreadActivity.this) {
                            taskEndNum++;
                            if (taskEndNum == 2) {
                                handler.sendEmptyMessage(1);
                            }
                        }
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    int taskEndNum = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("LQS", "value1 =" + value1 + " value2 = " + value2);

        }
    };

    private void useManyThreadIssue() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countMax-- > 0) {
                    synchronized (ThreadActivity.this) {
                        countValue++;
                        dateList.add(new Date());
                    }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                Log.d("LQS", "countValue = " + countValue);
                Log.d("LQS", "dataList size = " + dateList.size());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countMax2-- > 0) {
                    synchronized (ThreadActivity.this) {
                        countValue++;
                        dateList.add(new Date());
                    }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                }
                Log.d("LQS", "countValue = " + countValue);
                Log.d("LQS", "dataList size = " + dateList.size());
            }
        }).start();
    }

    private void useManyThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final long time = System.currentTimeMillis();
                while (true) {
                    num++;
                    if (num > 10000) {
                        Log.d("LQS", "使用时间" + (System.currentTimeMillis() - time));
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final long time = System.currentTimeMillis();
                while (true) {
                    num++;
                    if (num > 10000) {
                        Log.d("LQS", "使用时间" + (System.currentTimeMillis() - time));
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final long time = System.currentTimeMillis();
                while (true) {
                    num++;
                    if (num > 10000) {
                        Log.d("LQS", "使用时间" + (System.currentTimeMillis() - time));
                        break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
