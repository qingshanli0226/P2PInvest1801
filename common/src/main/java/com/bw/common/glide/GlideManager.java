package com.bw.common.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.bw.net.RetrofitManager;
import com.bw.net.api.Api;
import com.jakewharton.disklrucache.DiskLruCache;

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

public class GlideManager {

    private static GlideManager instance;
    private DiskLruCache diskLruCache;
    private Context context;
    private File fileDir;

    private GlideManager() {

    }

    public static GlideManager getInstance() {
        if (instance == null) {
            instance = new GlideManager();
        }
        return instance;
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    GlideTask glideTask = (GlideTask) msg.obj;
                    glideTask.getImageView().setImageBitmap(glideTask.getBitmap());
                    break;
                default:
                    break;
            }
        }
    };

    public void NetGlideTask(final GlideTask glideTask) {
        RetrofitManager.getInstance().retrofit
                .create(Api.class)
                .downloadFile(glideTask.getUrl())
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                        if (decodeStream == null) {
                            return;
                        }

                        Bitmap sampleBitmap = sampleBitmap(glideTask.getImageView().getMeasuredWidth(), glideTask.getImageView().getMeasuredHeight(), decodeStream);

                        //拿到采样后的bitmap直接显示
                        if ((glideTask.getImageView().getTag()).equals(glideTask.getUrl())) {
                            //渲染imageView，放到主线程渲染
                            glideTask.setBitmap(sampleBitmap);
                            Message.obtain(handler, 1, glideTask).sendToTarget();
                        }

                        decodeStream.recycle();
                        decodeStream=null;

                        String key = EncryptUtil.enrypByMd5(glideTask.getUrl());
                        try {
                            DiskLruCache.Editor edit = diskLruCache.edit(key);
                            OutputStream outputStream = edit.newOutputStream(0);//disklrucache的文件输出流
                            sampleBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);//将采集后bitmap存放到disklrucache里
                            edit.commit();
                            diskLruCache.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Bitmap samplePic(int width, int height, String filePath) {
        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeFile(filePath,options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>height || picWidth/sampleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public Bitmap sampleBitmap(int width, int height, Bitmap originalBitmap) {

        int picWidth = originalBitmap.getWidth();
        int picHeight = originalBitmap.getHeight();
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight/sampleSize>height || picWidth/sampleSize > width) {
            sampleSize = sampleSize*2;
        }
        //第一次采样结束

        //第二次采样，就是按照这个比例采集像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        //将originalbitmap转换成byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap samplaeBitmap =  BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samplaeBitmap;
    }

    public void getBitmapFromDiskLrucache(final String imgUrl, final IDiskLrucacheBitmap iDiskLrucacheBitmap){
        Observable.create(new ObservableOnSubscribe<Bitmap>(){
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                String key = EncryptUtil.enrypByMd5(imgUrl);
                try {
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    InputStream inputStream = snapshot.getInputStream(0);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    emitter.onNext(bitmap);
                }catch (IOException ex){
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

    public void init(Context context) {
        this.context = context;
        //是放到sd卡上的
        fileDir = new File(context.getExternalCacheDir().getAbsolutePath()+"/1801/");
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        try {
            //使用DiskLrucache缩略图，磁盘最多使用的空间是1个G，超过1个G，默认会删除最老的图片
            diskLruCache = DiskLruCache.open(fileDir, 1,1, 1000*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface IDiskLrucacheBitmap{
        void onBitmap(Bitmap sampleBitmap);
    }




}

