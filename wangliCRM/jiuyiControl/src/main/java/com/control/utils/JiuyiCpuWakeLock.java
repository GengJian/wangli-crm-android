package com.control.utils;

import android.content.Context;
import android.os.PowerManager;

/**
 * ****************************************************************
 * 文件名称:JiuyiCpuWakeLock.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:Android中获取和释放WakeLock的方法
 * 注意事项:
 * 技术说明:一些应用程序用户即使长时间不与其交互，也要阻止手机进入休眠状态。
 *          一个例子就是我们在看视频时，手机屏幕要保持开启状态。Android为此设计了WakeLock。
 * ****************************************************************
 */

public class JiuyiCpuWakeLock {
    private PowerManager.WakeLock sCpuWakeLock;

    public void acquireCpuWakeLock(Context context) {
        if (sCpuWakeLock != null) {
            return;
        }
        JiuyiLog.v("JiuyiCpuWakeLock", "acquireCpuWakeLock");
        sCpuWakeLock = createPartialWakeLock(context);
        sCpuWakeLock.setReferenceCounted(false);
        sCpuWakeLock.acquire(30*1000);//30s亮屏
    }

    /**
     * 申请锁
     * @param context
     * @return
     *
     * PARTIAL_WAKE_LOCK        屏幕关，键盘灯关，不休眠
     * SCREEN_DIM_WAKE_LOCK     屏幕灰，键盘灯关，不休眠
     * SCREEN_BRIGHT_WEEK_LOCK  屏幕亮，键盘灯关，不休眠
     * FULL_WAKE_LOCK           屏幕亮，键盘灯亮，不休眠
     *
     * ACQUIRE_CAUSES_WAKEUP：  强制使屏幕亮起，这种锁主要针对一些必须通知用户的操作.
     * ON_AFTER_RELEASE：       当锁被释放时，保持屏幕亮起一段时间
     */
    private PowerManager.WakeLock createPartialWakeLock(Context context) {
        JiuyiLog.i("JiuyiCpuWakeLock", "createPartialWakeLock");
        String flag = "log";
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, flag);
    }

    public void releaseCpuLock() {
        if (sCpuWakeLock != null) {
            JiuyiLog.i("JiuyiCpuWakeLock", "releaseCpuLock");
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }
    }
}
