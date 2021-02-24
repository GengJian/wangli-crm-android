package com.control.tools;

import android.os.Handler;
import android.os.Looper;

/**
 * ****************************************************************
 * 文件名称:JiuyiHandler.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:
 * 注意事项:1、new Handler()必须要在主线程中定义才能操作主线程，如果想在其他地方定义声明时要这样写new Handler(Looper.getMainLooper())
 *          2、handler.post(Runnable r)方法把消息处理放在该 handler 依附的消息队列中
 *          3、postAtTime(Runnable r, long uptimeMillis); //在某一时刻发送消息
 *          4、postDelayed(Runnable r, long delayMillis); //延迟delayMillis毫秒再发送消息
 * ****************************************************************
 */

public abstract class JiuyiHandler {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    public abstract void callBack();
    public JiuyiHandler(){
    }
    public void post(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack();
            }
        });
    }
    public void postAtTime(long uptimeMillis){
        mHandler.postAtTime(new Runnable() {
            @Override
            public void run() {
                callBack();
            }
        }, uptimeMillis);
    }
    public void postDelayed(long delayMillis){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack();
            }
        }, delayMillis);
    }
}
