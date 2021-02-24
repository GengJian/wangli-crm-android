package com.control.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;


/**
 * ****************************************************************
 * 文件名称:Res.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:获取资源的ID的方法
 * ****************************************************************
 */

public class Res {
    /** 屏幕密度，单位为dpi;Dpi:指每英寸中的像素数 */
    private static float mDensity;//
    /** 屏幕宽度 */
    private static int mWidthPixels;//
    /** 屏幕高度(可显示区域+topstatusbar) */
    private static int mHeightPixels;//
    /**
     * 获取屏幕信息
     */
    public static void GetDisplayParam() {
        DisplayMetrics dm = Rc.getApplication().getResources().getDisplayMetrics();

        if(dm.heightPixels >= dm.widthPixels) {
            //屏幕宽度
            mWidthPixels = dm.widthPixels;
            //屏幕高度
            mHeightPixels = dm.heightPixels;
            // 屏幕密度，单位为dpi;Dpi:指每英寸中的像素数
            mDensity = dm.density;// Density:指每平方英寸中的像素数
        }
    }
    /**
     * dip转为px
     * @param n dip值
     * @return px值
     */
    public static int Dip2Pix(int n) {
        return (int) (n * mDensity + 0.5f);
    }
    public static int Dip2Pix(float n) {
        return (int) (n * mDensity + 0.5f);
    }
    public static int Pix2Dip(int n) {
        return (int) ((n - 0.5f) / mDensity);
    }
    public static float Pix2Dip(float n) {
        return (float) ((n - 0.5f) / mDensity);
    }
    /**
     * 屏幕宽度
     * @return 屏幕宽度
     */
    public static int getWidthPixels(){
        return mWidthPixels;
    }

    /**
     * 屏幕高度
     * @return 屏幕高度
     */
    public static int getHeightPixels(){
        return mHeightPixels;
    }

    /**
     * 屏幕分别率变化了
     */
    public static boolean getDensityChanged(){
        DisplayMetrics dm = Rc.getApplication().getResources().getDisplayMetrics();
        return mDensity != dm.density;
    }


    public static int getDrawabelID(Context context, String name, int nForceSkinType) {
        context = Rc.getApplication();

        int id = 0;
         id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return id;
    }
    /**
     * 根据name获取Drawabel的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Drawabel的ID
     */
    public static int getDrawabelID(Context context, String name) {
        return getDrawabelID(context, name, -1);
    }
    /**
     * 根据name和nForceSkinType(默认皮肤)获取颜色值
     * @param context 上下文
     * @param name 资源名字
     * @param nForceSkinType 版本风格编号
     * @return 颜色值
     */
    public static int getColor (Context context, String name, int nForceSkinType){
        context = Rc.getApplication();

        int id = 0;
        id = context.getResources().getIdentifier(name, "color", context.getPackageName());

        return context.getResources().getColor(id);
    }
    /**
     * 根据name获取颜色值
     * @param context 上下文
     * @param name 资源名字
     * @return 颜色值
     */
    public static int getColor(Context context, String name) {
        return getColor(context, name, -1);
    }

    /**
     * 对于 context.getResources().getIdentifier 无法获取的数据 , 或者数组
     * 资源反射值
     * @param context 上下文
     * @param name 资源名字
     * @param type childClass的类型
     * @return 对象
     */
    private static Object getResourceId(Context context, String name, String type) {
        String className = context.getPackageName() + ".R";
        try {
            Class<?> cls = Class.forName(className);
            for (Class<?> childClass : cls.getClasses()) {
                String simple = childClass.getSimpleName();
                if (simple.equals(type)) {
                    for (Field field : childClass.getFields()) {
                        String fieldName = field.getName();
                        if (fieldName.equals(name)) {
                            JiuyiLog.i("", fieldName);
                            return field.get(null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return null;
    }

    /**
     * 对于 context.getResources().getIdentifier 无法获取到 styleable 的数据
     * @param context 上下文
     * @param name 资源名字
     * @return styleable 的数据
     */
    public static int getStyleableID(Context context, String name) {
        return ((Integer)getResourceId(context, name,"styleable")).intValue();
    }

    /**
     * 获取 styleable 的 ID 号数组
     * @param context 上下文
     * @param name 资源名字
     * @return ID 号数组
     */
    public static int[] getStyleableArray(Context context,String name) {
        return (int[])getResourceId(context, name,"styleable");
    }


    /**
     * 根据name获取Dimen的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Dimen的ID
     */
    public static int getDimenID(Context context, String name) {
        context = Rc.getApplication();

        int id = context.getResources().getIdentifier(name, "dimen", context.getPackageName());
        return id;
    }

    /**
     * 根据name获取Dimen的值
     * 注意getDimension与getDimensionPixelOffset、getDimensionPixelSize区别
     * @param context 上下文
     * @param name 资源名字
     * @return Dimen的值
     */
    public static int getDimenValue(Context context, String name) {
        context = Rc.getApplication();

        int id = getDimenID(context, name);
        if(id > 0) {
            return context.getResources().getDimensionPixelSize(id);
        }else{
            return 0;
        }
    }

    /**
     * 根据name获取布局Layout的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Layout的ID
     */
    public static int getLayoutID(Context context, String name) {
        context = Rc.getApplication();
        int id = context.getResources().getIdentifier(name, "layout", context.getPackageName());
        return id;

    }
    /**
     * 根据name获取布局内View的ID
     * @param context 上下文
     * @param name 资源名字
     * @return View的ID
     */
    public static int getViewID(Context context, String name) {
        context = Rc.getApplication();

       int id = context.getResources().getIdentifier(name, "id", context.getPackageName());
        return id;
    }

    /**
     * 根据name获取布String的ID
     * @param context 上下文
     * @param name 资源名字
     * @return String的ID
     */
    public static int getStringID(Context context, String name) {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }
    /**
     * 根据name获取布String
     * @param context 上下文
     * @param name 资源名字
     * @return String的字符串
     */
    public static String getString(Context context, String name) {
        context = Rc.getApplication();

        int id = context.getResources().getIdentifier(name, "string", context.getPackageName());
        if(id > 0) {
            return context.getResources().getString(id);
        }else{
            return null;
        }
    }

    /**
     * 根据name获取布Raw的ID
     * @param context 上下文
     * @param name 资源名字
     * @return 布Raw的ID
     */
    public static int getRawID(Context context, String name)
    {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }
    /**
     * 根据name获取布Attr的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Attr的ID
     */
    public static int getAttrID(Context context, String name)
    {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "attr", context.getPackageName());
    }
    /**
     * 根据name获取布Attr的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Attr的ID
     */
    public static int getIdID(Context context, String name)
    {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }
    /**
     * 根据name获取布Style的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Style的ID
     */
    public static int getStyleID(Context context, String name)
    {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "style", context.getPackageName());
    }

    /**
     * 根据name获取Animation的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Animation的ID
     */
    public static int getAnimationID(Context context, String name) {
        context = Rc.getApplication();

        return context.getResources().getIdentifier(name, "anim", context.getPackageName());
    }
    public static int resolveColor(Context context, @AttrRes int attr, int fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        int color = 0;
        if (fallback != 0) {
            color = ContextCompat.getColor(context, fallback);
        }
        try {
            return a.getColor(0, color);
        } finally {
            a.recycle();
        }
    }

}
