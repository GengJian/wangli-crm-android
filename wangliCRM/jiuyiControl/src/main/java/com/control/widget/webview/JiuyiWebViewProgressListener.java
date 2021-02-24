package com.control.widget.webview;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewProgressListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:WebView进度条监听
 * ****************************************************************
 */
public interface JiuyiWebViewProgressListener
{
	void StartProgress();
	void StopProgress();
	void StartPageProgress();
	void StopPageProgress();
}
