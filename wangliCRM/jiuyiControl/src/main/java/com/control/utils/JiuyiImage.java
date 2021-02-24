package com.control.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * ****************************************************************
 * 文件名称:JiuyiImage.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:Bitmap、drawable等的相互转化、大小设置等操作
 * ****************************************************************
 */
public final class JiuyiImage {
    /** Bitmap对象 */
    public Bitmap bitmap;

    /**
     * 构造函数，直接传入Bitmap对象
     * @param bitmap Bitmap对象
     */
    public JiuyiImage(Bitmap bitmap){
    	this.bitmap = bitmap;
    }

    /**
     * 构造函数，通过资源获取Bitmap对象
     * @param context 上下文
     * @param resid 资源ID
     */
    public JiuyiImage(Context context, int resid){
    	try{
    		if(resid > 0)//判断资源是否存在
    		 
    			createImage((BitmapDrawable)(context.getResources().getDrawable(resid)));
    		
    		else
    			bitmap = null;
    	}catch(Exception e){
    		
    	}
    }


    /**
     * 通过资源获取Bitmap对象,生成图片并拉伸或压缩图片的大小
     * @param context 上下文
     * @param resid 资源ID
     * @param w 宽
     * @param h 高
     */
    public JiuyiImage(Context context, int resid, int w, int h){
        this(context, resid);
        
        if(bitmap == null) {
        	return;
        }

        if (w <= 0) {
            w = bitmap.getWidth();
        }
        if (h <= 0) {
            h = bitmap.getHeight();
        }
        transImage(w, h);
    }

    /**
     * 通过资源获取Bitmap对象,生成图片并按比例拉伸或压缩图片的大小
     * @param context 上下文
     * @param resid 资源ID
     * @param defh 默认高度
     */
    public JiuyiImage(Context context, int resid, int defh){
        this(context, resid);
        
        if(bitmap == null) {
        	return;
        }

        int w = bitmap.getWidth();
	    int h = bitmap.getHeight();
	    
	    int defw = w * defh / h;
	    
	    
        transImage(defw, defh);
    }


    /**
     * 根据图片名称生成图片
     * @param context 上下文
     * @param name 资源名称
     */
    public JiuyiImage(Context context, String name){
        this(context, Res.getDrawabelID(context,name));
    }

    /**
     * 根据图片名称生成图片，并拉伸或压缩图片的大小
     * @param context 上下文
     * @param name 资源名称
     * @param w 宽
     * @param h 高
     */
    public JiuyiImage(Context context, String name, int w, int h){
        this(context, name);
        
        if(bitmap == null) {
        	return;
        }
        
        if (w <= 0) {
            w = bitmap.getWidth();
        }
        if (h <= 0) {
            h = bitmap.getHeight();
        }
        transImage(w, h);
    }

    /**
     * 获取图片的宽
     * @return 宽
     */
    public int getWidth() {
        return bitmap==null?0:bitmap.getWidth();
    }
    /**
     * 获取图片的高
     * @return 高
     */
    public int getHeight() {
        return bitmap==null?0:bitmap.getHeight();
    }
    /**
     * 判断bitmap是否为null
     * @return
     */
    public boolean isBitmapEmpty() {
        return bitmap == null;
    }

    /**
     * 根据BitmapDrawable创建图片
     * @param res 资源
     * @return Bitmap
     */
    public Bitmap createImage(BitmapDrawable res) {
        bitmap = res.getBitmap();
        return bitmap;
    }

    /**
     * 缩放图片
     * @param newWidth 宽
     * @param newHeight 高
     * @return Bitmap
     */
    public Bitmap transImage(int newWidth,int newHeight) {
    	if(bitmap == null){
    		return null;
    	}
        // 获得图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    /**
     * 缩放图片
     * @param newWidth 宽
     * @param newHeight 高
     * @return Bitmap
     */
    public Bitmap transScaledImage(int newWidth,int newHeight) {
        bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return bitmap;
    }

    /**
     * 绘制图片
     * @param g Canvas
     * @param x left
     * @param y top
     */
    public void drawImage(Canvas g, int x, int y){
        g.drawBitmap(bitmap, x, y,null);
    }

    /**
     * 绘制图片
     * @param g Canvas
     * @param x left
     * @param y top
     * @param w 目标宽度
     * @param h 目标高度
     * @param imgw 图片宽度
     * @param imgh 图片高度
     */
    public void drawImage(Canvas g, int x, int y, int w, int h, int imgw, int imgh){
        Rect src = new Rect(imgw, imgh, imgw + w, imgh + h);
        Rect dsc = new Rect(x, y, x + w, y + h);

        g.drawBitmap(bitmap, src, dsc,null);

        src = null;
        dsc = null;
    }

    /**
    * 旋转图片
    * @param rotate 旋转度数
    */
    public void getRotateBitmap(int rotate) {
		// 获取图片的原始的大小
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		// 旋转30*
		matrix.postRotate(rotate);
		// 创建一个新的图片
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	}

    /**
     * 根据Drawable得到一个确定大小的Bitmap
     * @param drawable Drawable对象
     * @param width 宽
     * @param height 高
     * @return Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(
                width,
                height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, width,height);
        drawable.draw(canvas);
        return bitmap;

    }


    /**
     * 保存Bitmap到本地
     * @param nameType 名称
     * @param mBitmap 要保存的图片Bitmap
     */
    public static void saveBitmap(int nameType, Bitmap mBitmap) {
    }
}
