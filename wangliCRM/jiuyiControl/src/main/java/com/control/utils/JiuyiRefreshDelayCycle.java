package com.control.utils;


/**
 * ****************************************************************
 * 文件名称:JiuyiRefreshDelayCycle.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:控制触发频率的类；根据周期刷新，如果不到时间，则等到时间才触发操作，主要是否防止触发过快
 * ****************************************************************
 */
public abstract class JiuyiRefreshDelayCycle {
    /** 执行延迟操作的具体动作 */
	public abstract void doDelayAction();
    /** 延迟周期 */
	private int nCycle = 0;//
    /** 是否第一次start就触发一次doDelayAction() */
	private boolean bSendWhenFirst = false;//
    /** 线程是否在运行 */
	private boolean bRun = false;
    /** 最后的触发时间 */
	private long lastClickTime;//
    /** 延迟执行的线程 */
	private DelayThread pDelayThread;//

    /**
     * 控制触发频率的类
     * @param cycle 延迟周期
     * @param bSendWhenFirst  是否第一次start就触发一次doDelayAction()
     */
	public JiuyiRefreshDelayCycle(int cycle, boolean bSendWhenFirst){
		nCycle = cycle;
		this.bSendWhenFirst = bSendWhenFirst;
		lastClickTime = System.currentTimeMillis();
	}

    /**
     * 设置延迟周期
     * @param cycle 延迟周期
     */
	public void setDelayTime(int cycle){
		nCycle = cycle;
	}

    /**
     * 线程的开启
     */
	public void start(){
		if(pDelayThread == null){
			try {
				bRun = true;
				pDelayThread = new DelayThread();
				pDelayThread.start();
				
				if(bSendWhenFirst){
					bSendWhenFirst = false;
					doDelayAction();
				}
			} catch (Exception e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}else{
			checkDelayAction();
		}
	}
    /**
     * 线程的结束
     */
	public void stop(){
		if(pDelayThread != null){
			try {
				bRun = false;
				pDelayThread.interrupt();
				pDelayThread = null;
				lastClickTime = System.currentTimeMillis();
			} catch (Exception e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}
	}

    /**
     * 检查是否超过了周期
     * 超过了就执行doDelayAction
     */
	private void checkDelayAction(){
        long time = System.currentTimeMillis();  
        long timeD = time - lastClickTime;  

        if (timeD >= nCycle) {
        	stop();
        	
        	doDelayAction();
        }
	}

    /**
     * 延迟执行的线程类
     */
	private class DelayThread extends Thread {
		public void run() {
			while (bRun) {
				checkDelayAction();
				try {
					if(pDelayThread != null)
						sleep(100);
				} catch (Exception e) {
//					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			}
		}
	}
}


