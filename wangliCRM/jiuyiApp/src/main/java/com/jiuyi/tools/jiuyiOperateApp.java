package com.jiuyi.tools;

import com.control.utils.CacheManager;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.jiuyi.app.JiuyiActivityManager;

/**
 * ****************************************************************
 * 文件名称:jiuyiOperateApp.java
 * 作    者:Created by zhengss
 * 创建时间:2018/6/1 9:00
 * 文件描述:对软件的操作类
 * 注意事项:实现的功能有：软件退出

 * ****************************************************************
 */

public class jiuyiOperateApp {
    public jiuyiOperateApp(){

    }


    /**
     * 退出软件通用处理
     */
    public void doExit() {
        try {
            //保存配置
            Rc.getIns().saveConfig();
        } catch (Exception e1) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e1));//e.printStackTrace();
        }

        try {

        } catch (Exception e1) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e1));//e.printStackTrace();
        }
        try {


        } catch (Exception e1) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e1));//e.printStackTrace();
        }

        try {
            CacheManager.cleanAppWebCacheFiles(Rc.getApplication());
            CacheManager.cleanInternalCache(Rc.getApplication());
            JiuyiActivityManager.popAllActivity();

        } catch (Exception e1) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e1));//e.printStackTrace();
        }
    }
}
