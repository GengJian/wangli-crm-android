package com.control.tools;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.JiuyiLog;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;

import java.lang.reflect.Method;


/**
 * ****************************************************************
 * 文件名称 : JiuyiNavigationBarVisiableChange.java
 * 文件描述 :虚拟导航栏的显示和隐藏的处理
 * 注意事项 :1、在界面总体布局的时候，设置LayoutParams高度为LayoutParams.MATCH_PARENT,这样就可以自动的缩放了
 * 			2、在具体的界面里实现dealNavigationBarVisiableChange方法，主要是调整CRect的bottom和设置固定高度的布局的LayoutParams

 *****************************************************************
 */
public class JiuyiNavigationBarVisiableChange {
	private Activity pActivity;
	public int usableHeightPrevious;//当前正在使用的高度
	public View m_vBodyLayout;
    private boolean mNavigationBarStatus = false;
    private boolean mS8NavigationBarStatus = false;
    private boolean mOppoNavigationBarStatus = false;

	public JiuyiNavigationBarVisiableChange(Activity activity){
		pActivity = activity;
	}

    public void addListener(final View vBodyLayout) {
        m_vBodyLayout = vBodyLayout;
        if (m_vBodyLayout != null) {
            m_vBodyLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    Configuration mConfiguration = pActivity.getResources().getConfiguration(); //获取设置的配置信息
                    int usableHeightNow = (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) ? computeUsableWidth() : computeUsableHeight();
                    if (usableHeightPrevious == 0) {
                        usableHeightPrevious = usableHeightNow;
                        mNavigationBarStatus = isShowDeviceHasNavigationBar();
                        mS8NavigationBarStatus = isShowS8HasNavigationBar();
                        mOppoNavigationBarStatus = isShowOppoHasNavigationBar();
                    }
                    if (usableHeightNow != usableHeightPrevious &&
                            (mNavigationBarStatus != isShowDeviceHasNavigationBar() ||
                                    mS8NavigationBarStatus != isShowS8HasNavigationBar() ||
                                    mOppoNavigationBarStatus != isShowOppoHasNavigationBar())) {
                        //如果两次高度不一致 n
                        if (m_vBodyLayout != null && usableHeightNow != 0) {
                            if (m_vBodyLayout instanceof JiuyiRelativeLayout) {
                                int heightDiff = usableHeightNow - usableHeightPrevious;
                                if (Math.abs(heightDiff) != Math.abs(Rc.getIns().getBottomNaviNarHeight(pActivity))
                                        && mConfiguration.orientation != Configuration.ORIENTATION_LANDSCAPE) {//解决部分华为手机翻转时，无法获取topstatus高度的问题
                                    int bottomStatusHeight = Rc.getIns().getBottomNaviNarHeight(pActivity);
                                    heightDiff = heightDiff > 0 ? bottomStatusHeight : 0 - bottomStatusHeight;
                                }

                                ((JiuyiRelativeLayout) m_vBodyLayout).dealNavigationBarVisiableChange(usableHeightNow, heightDiff);
                                //将计算的可视高度设置成视图的高度
                                usableHeightPrevious = usableHeightNow;
                                if (mNavigationBarStatus != isShowDeviceHasNavigationBar())
                                    mNavigationBarStatus = isShowDeviceHasNavigationBar();
                                if (mS8NavigationBarStatus != isShowS8HasNavigationBar())
                                    mS8NavigationBarStatus = isShowS8HasNavigationBar();
                                if (mOppoNavigationBarStatus != isShowOppoHasNavigationBar())
                                    mOppoNavigationBarStatus = isShowOppoHasNavigationBar();
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * 是否有但是隐藏底部工具栏
     * 如果底部导航栏隐藏则高度加导航栏的高度
     *
     * @return
     */
    public boolean isHasButHideNavigationBar() {
        return (isShowDeviceHasNavigationBar() && !isPortraitNavigationBarShow())
                || (hasS8HasNavigationBar(null) && !isShowS8HasNavigationBar())
                || (hasOppoHasNavigationBar(null) && !isShowOppoHasNavigationBar());
    }

    private int computeUsableHeight() {
        //计算视图可视高度
        Rect r = new Rect();
        m_vBodyLayout.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }


    private int computeUsableWidth() {
        //计算视图可视高度
        Rect r = new Rect();
        m_vBodyLayout.getWindowVisibleDisplayFrame(r);
        return (r.right - r.left);
    }

    /**
     * 导航栏是否显示
     * 判断是否显示要与checkDeviceHasNavigationBar一起使用
     *
     * @return
     */
    private boolean isPortraitNavigationBarShow() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = pActivity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            boolean menu = ViewConfiguration.get(pActivity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isLandscapeNavigationBarShow() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = pActivity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.x != size.x;
        } else {
            boolean menu = ViewConfiguration.get(pActivity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 显示隐藏状态栏
     *
     * @param show
     */
    private void setStatusBarVisible(boolean show) {
        if (show) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            uiFlags |= 0x00001000;
            pActivity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        } else {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            uiFlags |= 0x00001000;
            pActivity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }
    }

    /**
     * 显示隐藏状态栏和导航栏
     *
     * @param show
     */
    private void setSystemUIVisible(boolean show) {
        if (show) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            uiFlags |= 0x00001000;
            pActivity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        } else {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            uiFlags |= 0x00001000;
            pActivity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }
    }

    //获取是否存在NavigationBar
    private boolean isShowDeviceHasNavigationBar() {
        WindowManager windowManager = pActivity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        int displayHeight = m_vBodyLayout!=null ? m_vBodyLayout.getMeasuredHeightAndState() : 0;
        int displayWidth = m_vBodyLayout!=null ? m_vBodyLayout.getMeasuredWidthAndState() : 0;
        if(displayHeight == 0){
            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            displayHeight = displayMetrics.heightPixels;
            displayWidth = displayMetrics.widthPixels;
        }

        return !((realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0);
    }

    //获取是否存在NavigationBar 0显示,1隐藏，-1不支持 只返回显示
    private boolean isShowS8HasNavigationBar() {
        int enable = -1;
        try {
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                enable = Settings.Global.getInt(pActivity.getContentResolver(), "navigationbar_hide_bar_enabled");
            }
        } catch (Settings.SettingNotFoundException e) {
            JiuyiLog.getStackTraceString(e);
            enable = -1;
        }
        return enable == 0;
    }

    //获取是否存在NavigationBar  0显示,1隐藏，-1不支持
    private boolean hasS8HasNavigationBar(Activity activity) {
        int enable = -1;
        try {

            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (null == activity)
                    activity = pActivity;
                enable = Settings.Global.getInt(activity.getContentResolver(), "navigationbar_hide_bar_enabled");
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            enable = -1;
        }
        return enable != -1;
    }

    //获取是否存在NavigationBar 0显示,1隐藏，-1不支持
    private boolean isShowOppoHasNavigationBar() {
        int enable = -1;
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String hideNavigationBar = (String) m.invoke(systemPropertiesClass, "oppo.hide.navigationbar");
            enable = Func.parseInt(hideNavigationBar);
        } catch (Exception e) {
            e.printStackTrace();
            enable = -1;
        }
        return enable == 0;
    }

    //获取是否存在NavigationBar  0显示,1隐藏，-1不支持
    private boolean hasOppoHasNavigationBar(Activity activity) {
        int enable = -1;
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String hideNavigationBar = (String) m.invoke(systemPropertiesClass, "oppo.hide.navigationbar");
            enable = Integer.valueOf(hideNavigationBar);
        } catch (Exception e) {
            e.printStackTrace();
            enable = -1;
        }
        return enable != -1;
    }

}

