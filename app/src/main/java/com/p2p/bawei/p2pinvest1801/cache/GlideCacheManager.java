package com.p2p.bawei.p2pinvest1801.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import com.example.common.bean.GlidTesk;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.jakewharton.disklrucache.DiskLruCache;
import com.p2p.bawei.p2pinvest1801.util.EncryptUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class GlideCacheManager {
    private static GlideCacheManager glideCacheManager;
    private File fileDir;
    private Context context;
    private DiskLruCache diskLruCache;
    private LruCache<String, Bitmap> lruCache;//内存缓存的数据
    public GlideCacheManager() {
    }

    public static GlideCacheManager getInstance() {
        if (glideCacheManager == null) {
            glideCacheManager = new GlideCacheManager();
        }
        return glideCacheManager;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    GlidTesk glideTask = (GlidTesk) msg.obj;
                    glideTask.getImageView().setImageBitmap(glideTask.getBitmap());
                    break;
                default:
                    break;
            }
        }
    };
    public void init(Context context) {
        this.context = context;
        fileDir = new File(context.getExternalCacheDir().getAbsolutePath()+"/1801/");
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        try {
            diskLruCache = DiskLruCache.open(fileDir, 1,1, 1000*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lruCache = new LruCache<>(20*1024*1024);
    }
    public interface IDiskLrucacheBitmap{
        void onBitmap(Bitmap sampleBitmap);
    }
    public void getBitmapFromDiskLrucache(final String url, final IDiskLrucacheBitmap iDiskLrucacheBitmap) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                String key = EncryptUtil.enrypByMd5(url);
                    try {
                        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                        InputStream inputStream = snapshot.getInputStream(0);
                        Bitmap sampleBitmap = BitmapFactory.decodeStream(inputStream);
                        emitter.onNext(sampleBitmap);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        emitter.onError(ex);
                    }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        iDiskLrucacheBitmap.onBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iDiskLrucacheBitmap.onBitmap(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    public void processGlideTask(final GlidTesk glidTesk){
//        RetrofitManager.getInstance()
//                .getRetrofit()
//                .create(P2PApi.class)
//                .downloadFile(glidTesk.getUrl())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io() )
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody body) {
//                        InputStream inputStream = body.byteStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        if(bitmap == null){
//                            return;
//                        }
//
//                        Bitmap samplemap = sampleBitMap(glidTesk.getImageView().getMeasuredWidth(), glidTesk.getImageView().getMeasuredHeight(), bitmap);
//
//                        if(((String)glidTesk.getImageView().getTag()).equals(glidTesk.getUrl())){
//                            glidTesk.setBitmap(samplemap);
//                            Message.obtain(handler,1,glidTesk).sendToTarget();
//                        }
//                        bitmap.recycle();
//                        bitmap = null;
//                        String key = EncryptUtil.enrypByMd5(glidTesk.getUrl());
//                        lruCache.put(key, samplemap);
//                        Log.i("----", lruCache.size()+"");
//                        try {
//                            DiskLruCache.Editor edit = diskLruCache.edit(key);
//                            OutputStream outputStream = edit.newOutputStream(0);
//                            samplemap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//                            edit.commit();
//                            diskLruCache.flush();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
    public Bitmap samplePic(int width,int height,String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeFile(filePath, options);
        options.inJustDecodeBounds = false;
        int picw = options.outWidth;
        int pich = options.outHeight;
        int samplesize = 1;
        while(pich / samplesize > height || picw / samplesize > width){
            samplesize = samplesize * 2;
        }

        options.inJustDecodeBounds = true;
        options.inSampleSize = samplesize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(filePath, options);
    }

    private Bitmap sampleBitMap(int width,int height,Bitmap bitmap){
        int picW = bitmap.getWidth();
        int picH = bitmap.getHeight();

        int samplesize = 1;
        while(picH / samplesize > height || picW / samplesize > width){
            samplesize = samplesize * 2;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = samplesize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        Bitmap samplaeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samplaeBitmap;
    }



}
