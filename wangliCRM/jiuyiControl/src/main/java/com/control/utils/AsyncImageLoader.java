package com.control.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

/**
 * ****************************************************************
 * 文件名称:AsyncImageLoader.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:动态加载图片
 * ****************************************************************
 */
public class AsyncImageLoader
{
	//SoftReference是软引用，是为了更好的为了系统回收变量
    private HashMap<String, SoftReference<Drawable>> imageCache;
    
    public AsyncImageLoader() 
    {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }
    
    public Drawable loadDrawable(final String imageUrl,final ImageView imageView, final ImageCallback imageCallback)
    {
        if (imageCache.containsKey(imageUrl))
        {
            //从缓存中获取
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null)
            {
                return drawable;
            }
        }
        final Handler handler = new Handler()
        {
            public void handleMessage(Message message)
            {
                imageCallback.imageLoaded((Drawable) message.obj, imageView,imageUrl);
            }
        };
        //建立新一个新的线程下载图片
        new Thread()
        {
            @Override
            public void run()
            {
            	String url = imageUrl;
                Drawable drawable = null;
                drawable = loadImageFromUrl(url);
                imageCache.put(url, new SoftReference<Drawable>(drawable));
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    public static Drawable loadImageFromUrl(String url)
    {
        URL m;
        InputStream i = null;
        try 
        {
            m = new URL(url);
            i = (InputStream) m.getContent();
        }
        catch (Exception e1)
        {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e1));
        } 
        Drawable d = Drawable.createFromStream(i, "src");
        return d;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap loadbitmapFromUrl(String url)
    {
        Bitmap bitmap=null;
        Drawable drawable=loadImageFromUrl(url);
        if(drawable!=null){
            bitmap=drawableToBitmap(drawable);
            return bitmap;
        }
        return bitmap;
    }
    
    //回调接口
    public interface ImageCallback
    {
        void imageLoaded(Drawable imageDrawable, ImageView imageView, String imageUrl);
    }
}
