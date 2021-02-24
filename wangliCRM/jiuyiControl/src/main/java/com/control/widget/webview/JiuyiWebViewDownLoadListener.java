package com.control.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewDownLoadListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:WebView访问网页中下载事件捕捉并处理
 * ****************************************************************
 */
public class JiuyiWebViewDownLoadListener implements DownloadListener
{
	private Context m_pContext;
	
	public JiuyiWebViewDownLoadListener(Context context)
	{
		m_pContext = context;
	}

	@Override
	public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) 
	{
		Uri uri = Uri.parse(url);  
        Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
    	//Content的startActivity方法，需要开启一个新的task否则会crash
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (m_pContext != null)
        {
        	m_pContext.startActivity(intent); 
        }
	}
	
}
