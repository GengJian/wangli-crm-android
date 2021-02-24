package com.iflytek.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ****************************************************************
 * 文件名称 : IflytekRes
 * ****************************************************************
 */
public class IflytekRes {
    /** 屏幕密度，单位为dpi;Dpi:指每英寸中的像素数 */
    private static float mDensity;//
    /** 屏幕宽度 */
    private static int mWidthPixels;//
    /** 屏幕高度(可显示区域+topstatusbar) */
    private static int mHeightPixels;//
    /**
     * 获取屏幕信息
     */
    public static void GetDisplayParam(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        //屏幕宽度
        mWidthPixels = dm.widthPixels;
        //屏幕高度
        mHeightPixels = dm.heightPixels;
        // 屏幕密度，单位为dpi;Dpi:指每英寸中的像素数
        mDensity = dm.density;// Density:指每平方英寸中的像素数
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
     * 上方状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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

    public static int getDrawabelID(Context context, String name, int nForceSkinType) {
        int id = 0;
        id = context.getResources().getIdentifier(name + (nForceSkinType == 1 ? "_1" : ""), "drawable", context.getPackageName());
        return id;
    }

    /**
     * 根据name获取布局Layout的ID
     * @param context 上下文
     * @param name 资源名字
     * @return Layout的ID
     */
    public static int getLayoutID(Context context, String name) {
        int id = context.getResources().getIdentifier(name, "layout", context.getPackageName());
        return id;
    }

    public static int getViewID(Context context, String name) {
        int id = context.getResources().getIdentifier(name, "id", context.getPackageName());
        return id;
    }
    /**
     * 根据name和nForceSkinType(默认皮肤)获取颜色值
     * @param context 上下文
     * @param name 资源名字
     * @param nForceSkinType 版本风格编号
     * @return 颜色值
     */
    public static int getColor (Context context, String name, int nForceSkinType){
        int id = context.getResources().getIdentifier(name, "color", context.getPackageName());

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
     * dip转为px
     * @param n dip值
     * @return px值
     */
    public static int Dip2Pix(int n) {
        return (int) (n * mDensity + 0.5f);
    }
}
