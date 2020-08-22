package com.p2p.bawei.p2pinvest1801.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;

import androidx.annotation.NonNull;

import com.example.net.RetrofitManager;
import com.jakewharton.disklrucache.DiskLruCache;
import com.p2p.bawei.p2pinvest1801.utils.EncryptUtil;

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
    private File fileDir;
    private Context context;

    private DiskLruCache diskLruCache;
    private LruCache<String, Bitmap> lruCache;//内存缓存数据

    public GlideManager() {
    }


    public static GlideManager getInstance() {
        if (instance == null) {
            instance = new GlideManager();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;

        fileDir = new File(context.getExternalCacheDir().getAbsolutePath() + "/zxh/");
        if (fileDir.exists()) {
            fileDir.mkdir();
        }

        try {
            //超过1G，删除最老数据
            diskLruCache = DiskLruCache.open(fileDir, 1, 1, 1000 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lruCache = new LruCache<>(20 * 1024 * 1024);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    GlideTask glideTask = new GlideTask();
                    glideTask.getImageView().setImageBitmap(glideTask.getBitmap());
                    break;
                default:
                    break;
            }
        }
    };

    //从diskLruCache里读取数据
    public void getBitmapFromDiskLrucache(final String url, final IDiskLrucacheBitmap iDiskLrucacheBitmap) {
        //获取key

        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                String key = EncryptUtil.enrypByMd5(url);
                try {
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    InputStream inputStream = snapshot.getInputStream(0);
                    Bitmap sampleBitmap = BitmapFactory.decodeStream(inputStream);
                    e.onNext(sampleBitmap);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    e.onError(ex);
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

    public interface IDiskLrucacheBitmap {
        void onBitmap(Bitmap sampleBitmap);
    }

    public void processGlideTask(final GlideTask glideTask) {
        RetrofitManager.getInvestApiService().downloadFile(glideTask.getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();//输入流
                        //获取图片原图的bitmap
                        Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                        if (originalBitmap == null) {
                            return;
                        }
                        //进行二次采样，生成缩略图的bitmap
                        Bitmap sampleBitmap = sampleBitmap(glideTask.getImageView().getMeasuredWidth(), glideTask.getImageView().getMeasuredHeight(), originalBitmap);
                        //拿到采样后的bitmap直接显示
                        if (((String) glideTask.getImageView().getTag()).equals(glideTask.getUrl())) {
                            //渲染imageView，放到主线程渲染
                            glideTask.setBitmap(sampleBitmap);
                            Message.obtain(handler, 1, glideTask).sendToTarget();
                        }

                        originalBitmap.recycle();//把原图的bitmap释放掉
                        originalBitmap = null;
                        //把采样后sampbitmap以文件的形式存储disklrucache里
                        //disklrucache也是类似hashmap也是以key value存储的
                        //生成一个key
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

    private Bitmap samplePic(int width, int height, String filePath) {

        //第一次采样，主要采集图片边框，算出图片的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//通过该标志位，确定第一次采样只采集边框
        BitmapFactory.decodeFile(filePath, options);
        //计算出图片的宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight / sampleSize > height || picWidth / sampleSize > width) {
            sampleSize = sampleSize * 2;
        }
        //第二次采样，就是按照这个比例采集像素
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


        return BitmapFactory.decodeFile(filePath, options);
    }


    private Bitmap sampleBitmap(int width, int height, Bitmap originalBitmap) {

        int picWidth = originalBitmap.getWidth();
        int picHeight = originalBitmap.getHeight();
        //计算出缩放比例
        int sampleSize = 1;
        while (picHeight / sampleSize > height || picWidth / sampleSize > width) {
            sampleSize = sampleSize * 2;
        }

        //第二次采样，就是按照这个比例采集像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;//不是采集边框，而是按比例采集像素
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        //将originalbitmap转换成byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap samplaeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return samplaeBitmap;
    }
}


