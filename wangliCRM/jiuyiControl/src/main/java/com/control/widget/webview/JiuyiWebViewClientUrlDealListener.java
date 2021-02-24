package com.control.widget.webview;

import java.util.Map;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewClientUrlDealListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:Webview对URL的处理后，根据参数进行操作
 * ****************************************************************
 */
public interface JiuyiWebViewClientUrlDealListener
{
    /** 网页调用界面返回 */
	boolean OnReturenBack();
    /** 网页调用的正在加载url？ */
    public boolean OnLoadingUrl(String url, Map<String, String> urlParamMap);
    /** 网页调用拨打电话 */
	boolean OnActionCall(String url, Map<String, String> urlParamMap);
    /** 网页调用界面跳转 */
	boolean OnActionPageType(int nAction, JiuyiWebView jiuyiWebView, String urlParam, Map<String, String> urlParamMap, boolean bTradeAction);
    /** 用于在页面渲染完成后修改页面高度 */
	boolean OnResetContentView(Map<String, String> urlParamMap);//
}
