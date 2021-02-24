package com.control.Interface;

/**
 * ****************************************************************
 * 文件名称 : JiuyiSpeech2TextListener
 * 作    者 : zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 语言听写的回调
 * ****************************************************************
 */
public interface JiuyiSpeech2TextListener {
    //语音识别成功
    void onResult(String results);
    //语音识别失败
    void onError(int errorCode, String errorMsg);
}
