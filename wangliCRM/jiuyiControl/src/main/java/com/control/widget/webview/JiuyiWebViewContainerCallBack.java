package com.control.widget.webview;

import android.app.Activity;
import android.net.Uri;
import android.webkit.ValueCallback;

import com.control.widget.keyboard.JiuyiKeyBoardView;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewContainerCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:包含jiuyiWebView的容器与WebView之间的回调
 * ****************************************************************
 */

public interface JiuyiWebViewContainerCallBack {
    Activity getActivityBase();
    JiuyiKeyBoardView getKeyBoard();
    int getPageType();
    void startDialog(int nAction, String sTitle, String sContent, int nBtnType);

    ValueCallback<Uri> getWebViewValueCallback();
    void setWebViewValueCallback(ValueCallback<Uri> mUploadMessage);
    int getFILECHOOSER_RESULTCODE();
}
