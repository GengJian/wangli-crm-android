package com.control.Interface;

import android.content.Context;

/**
 * ****************************************************************
 * 文件名称 : JiuyiISpeechInterface.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 语音合成，语音识别相关
 * 语音听写-语音搜素
 *****************************************************************
 */
public interface JiuyiISpeechInterface {
	
	/**
	 * @param context 上下文
	 * @param msg 注册ip
	 * 初始化引擎
	 */
    void initSpeech(Context context, String msg);

    /**
     * 初始化语音听写对象 - 客户语音搜索
     * @param context                上下文
     * @param mSpeech2TextListener 回调接口
     */
    void initmSpeech2Text(Context context, JiuyiSpeech2TextListener mSpeech2TextListener);
	
	/**
	 * 销毁语音听写引擎
	 */
    void destroySpeech2Text();


    /**
     * 语音听写-是否正在听
     * @return 语音听写-是否正在听
     */
    boolean isListeningSpeech2Text();
	
	/**
	 * 语音听写-开始识别
	 */
    void recognizeSpeech2Text();
	
	/**
	 * 语音听写-停止识别
	 */
    void stopSpeech2Text();

	/**
	 * 销毁语音合成引擎
	 */
    void destroyText2Speech();

    /**
     * 语音合成-是否正在读
     * @return  语音合成-是否正在读
     */
    boolean isSpeakingText2Speech();
	
	/**
	 * 语音合成-打开语音合成界面
	 * @param context Activity
	 * @param msg 合成内容
	 */
    void startIflytekActivity(Context context, String msg);
}
