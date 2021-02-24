package com.control.widget.webview;

import java.util.Map;
/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewAudioListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * ****************************************************************
 */
public interface JiuyiWebViewAudioListener
{
	boolean onPhotograph(int nAction, String strUrlParam, Map<String, String> ayurlParam);
	
	boolean onOpenVideo(int nAction, String strUrlParam, Map<String, String> ayUrlParam);
	
	Map<String, String> getUpLoadImageAddParam(byte[] bData);
	//分享
    boolean onShare(int nAction, String strUrlParam, Map<String, String> ayUrlParam);
}
