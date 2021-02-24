package com.control.widget.keyboard;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import org.greenrobot.eventbus.EventBus;

/**
 * ****************************************************************
 * 文件名称:JiuyiKeyBoardManager.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:
 * ****************************************************************
 */

public class JiuyiKeyBoardManager {
    public static JiuyiKeyBoardManager ins;

    public static JiuyiKeyBoardManager getIns(){
        if(ins == null){
            ins = new JiuyiKeyBoardManager();
        }
        return ins;
    }

    /**
     * 关闭键盘
     */
    public void closeKeyBoard() {
        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_CloseSysKeyBoard, "", ""));
    }

    public static void hideSoftInputFromWindow(IBinder windowToken, int flags){
        if(windowToken == null && Rc.getApplication()!=null)
            return;
        try {
            InputMethodManager imm = (InputMethodManager) Rc.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                hideSoftInputFromWindow(imm, windowToken, flags);
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
    }

    private static void hideSoftInputFromWindow(InputMethodManager imm, IBinder windowToken, int flags){
        if(windowToken == null)
            return;
        imm.hideSoftInputFromWindow(windowToken, flags);
    }

    /**
     * 判断是否有自定义键盘
     * @param keyBoardView
     * @return
     */
    public boolean IsKeyBoardEmpty(JiuyiKeyBoardView keyBoardView) {
        return keyBoardView == null;
    }
}
