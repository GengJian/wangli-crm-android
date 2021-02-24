package com.jiuyi.tools;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiCpuWakeLock;
import com.control.widget.JiuyiFastToast;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity;
import com.jiuyi.app.JiuyiActivityManager;

/**
 * ****************************************************************
 * 文件名称:jiuyiResetLinkBackToForeground.java
 * 作    者:Created by zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述:App进入后台运行后，超过10分钟再进入前台运行(软件没有被杀掉)，重启link即可恢复正常
 * ****************************************************************
 */

public class jiuyiResetLinkBackToForeground {
    private Application application;
    /** 获取和释放WakeLock的方法*/
    private JiuyiCpuWakeLock mCpuWakeLock;
    /** 按home的时间 */
    private long mPressHomeTime;
    /** 存活的Activity数 */
    private int mActivityCount = 0;
    /** 进入后台后在进入前台直接时间间隔后，重启link */
    private int mTimeSpan = 10 * 60 * 60;

    public jiuyiResetLinkBackToForeground(Application application){
        this.application = application;
        mCpuWakeLock = new JiuyiCpuWakeLock();

        //判断App是否在后台运行
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityStopped(Activity activity) {
                if(!(activity instanceof JiuyiHeadPageActivity)){
                    mActivityCount--;
                }
                if(mActivityCount == 0 && !JiuyiActivityManager.isStackEmpty()){
                    if(JiuyiActivityManager.getCurrentActivity() instanceof JiuyiICanvasInterface){
                        mPressHomeTime = System.currentTimeMillis();
                        //要保证手机桌面最下面一排按钮的上方，否则用户可能看不清
                        JiuyiFastToast.show(Rc.getApplication(), getApplicationName()+" 已进入后台运行！", Toast.LENGTH_SHORT, Res.Dip2Pix(100));
                        //指纹解锁
                        JiuyiPasswordLockShared.getIns().startCheckLock();
                    }
                }
            }
            @Override
            public void onActivityStarted(Activity activity) {
                if(mActivityCount == 0 && !JiuyiActivityManager.isStackEmpty()){
                    boolean isReset = System.currentTimeMillis() - mPressHomeTime > mTimeSpan;
                    if(isReset){
                        mPressHomeTime = System.currentTimeMillis();
                        doResetLink();
                    }
                }
                if(!(activity instanceof JiuyiHeadPageActivity)){
                    mActivityCount++;
                }
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
            @Override
            public void onActivityResumed(Activity activity) {
            }
            @Override
            public void onActivityPaused(Activity activity) {
            }
            @Override
            public void onActivityDestroyed(Activity activity) {
            }
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
        });
    }

//    public void releaseCpuLock(){
//        if(mCpuWakeLock != null){
//            mCpuWakeLock.releaseCpuLock();
//        }
//    }
//
//    public void acquireCpuWakeLock(){
//        if(mCpuWakeLock != null){
//            mCpuWakeLock.acquireCpuWakeLock(jiuyiResetLinkBackToForeground.this.application);
//        }
//    }

    /**
     * 清空参数
     */
    private void doResetLink(){

    }

    /**
     * 获取apk的应用名
     * @return
     */
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = Rc.getApplication().getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(Rc.getApplication().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        if(applicationInfo != null) {
            String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
            return applicationName;
        }else{
            return "";
        }
    }
}