package com.control.widget.webview;

import java.util.Map;
/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewCERRequestListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:证书的申请
 * ****************************************************************
 */
public interface JiuyiWebViewCERRequestListener
{
	void onCreateP10(int nAction, String strUrl, Map<String, String> ayUrlParam);
	void onRequestCER(int nAction, String strUrl, Map<String, String> ayUrlParam);
}
