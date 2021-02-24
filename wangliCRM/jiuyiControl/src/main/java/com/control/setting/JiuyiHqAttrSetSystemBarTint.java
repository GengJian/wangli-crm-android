package com.control.setting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.control.utils.Func;
import com.control.utils.Res;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * ****************************************************************
 * 文件名称 : JiuyiHqAttrSetSystemBarTint.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 沉浸式状态栏
 *****************************************************************
 */
public class JiuyiHqAttrSetSystemBarTint {

    /**
	 * 是否允许-沉浸式状态栏
	 * 默认允许
	 */
    private boolean mIsSystemBarTint = true;
	
	/**
	 * 导航栏是否沉浸(不能私自开放)
	 * 默认不沉浸
	 */
	private final boolean mNavigationBarTint = false;
	
	
	/**
	 * 沉浸式状态栏-状态栏的背景Drawabel
	 */
    private String mSystemBarTintColorName = "title_background_color";


    public void setSystemBarTint(boolean systemBarTint) {
        mIsSystemBarTint = systemBarTint;
    }

    public void setSystemBarTintDrawabelName(String mSystemBarTintColorName) {
        this.mSystemBarTintColorName = mSystemBarTintColorName;
    }

    /**
     * 是否支持沉浸式状态栏
     * @return  是否支持
     */
	public boolean isSystemBarTint(){
		return mIsSystemBarTint && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	}
	
	/**
	 * 显示沉浸式状态栏
	 * 不显示沉浸式的情况有：
	 * 1、用户设置不显示的不显示
	 * 2、不是竖屏的情况下不显示
     * 3、完全沉浸式不需要设置android:fitsSystemWindows="true"
	 * @param activity 当前活动
	 * @param mBodyLayout 根view
	 * @param statusBarColor 最上方状态栏背景色 可为空
	 * @param navigationBar 最下方导航栏背景色 可为空
     * @param isFullBarTint 是否完全沉浸式
	 */
	@SuppressLint("NewApi") 
	public void showSystemBarTint(Activity activity, ViewGroup mBodyLayout, View titleBar, String statusBarColor , String navigationBar, boolean isFullBarTint){
		if(activity== null || mBodyLayout == null || !mIsSystemBarTint ||
				activity.getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			return;
		}

		if(isFullBarTint && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //5.0以上状态栏透明 [黑名单: 华为荣耀4A]
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mBodyLayout.setFitsSystemWindows(true);
                mBodyLayout.setClipToPadding(true);

                //透明状态栏
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
                if (mNavigationBarTint)
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


                SystemBarTintManager tintManager = new SystemBarTintManager(activity);

                // 激活状态栏
                tintManager.setStatusBarTintEnabled(true);
                // 激活导航栏
                if (mNavigationBarTint)
                    tintManager.setNavigationBarTintEnabled(true);


                //给状态栏设置颜色
                if (Func.IsStringEmpty(statusBarColor)) {
                    tintManager.setStatusBarTintColor(Res.getColor(activity, mSystemBarTintColorName));
                } else {
                    tintManager.setStatusBarTintColor(Res.getColor(activity, statusBarColor));
                }

                //给导航栏设置资源
                if (mNavigationBarTint) {
                    if (Func.IsStringEmpty(navigationBar)) {
                        tintManager.setNavigationBarTintColor(Color.rgb(0, 0, 0));//Color.parseColor("#ff000000")
                    } else {
                        tintManager.setNavigationBarTintColor(Res.getColor(activity, navigationBar));
                    }
                }
            }
        }

        //5.0以上titlebar 设置marginTop为状态栏高度
        if(isFullBarTint){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //获取状态栏高度
                int mStatusBarHeight = Func.getNaviStatusBarSize(activity)[0];
                mStatusBarHeight = mStatusBarHeight > 0 ? mStatusBarHeight : Res.Dip2Pix(20);
                if (titleBar != null) {
                    ViewGroup.LayoutParams mTitleBarParams = titleBar.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginParams = null;
                    //获取view的margin设置参数
                    if (mTitleBarParams instanceof ViewGroup.MarginLayoutParams) {
                        marginParams = (ViewGroup.MarginLayoutParams) mTitleBarParams;
                    } else {
                        //不存在时创建一个新的参数
                        marginParams = new ViewGroup.MarginLayoutParams(mTitleBarParams);
                    }
                    marginParams.setMargins(0, mStatusBarHeight, 0, 0);
                    titleBar.setLayoutParams(marginParams);
                }
            }
        }
	}
}
