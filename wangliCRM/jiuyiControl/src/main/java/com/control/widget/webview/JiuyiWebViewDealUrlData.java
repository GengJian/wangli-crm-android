package com.control.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.control.utils.AjaxWebClientUrlLis;
import com.control.utils.Func;
import com.control.utils.JiuyiLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewDealUrlData.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:Webview对URL的处理类
 * ****************************************************************
 */
public class JiuyiWebViewDealUrlData
{
	private Context context;
	private JiuyiWebView mJiuyiWebView;

	public JiuyiWebViewDealUrlData(Context context, JiuyiWebView vWebView)
	{
		setContext(context);
		mJiuyiWebView = vWebView;
	}
	
	public void setContext(Context context) 
	{
		this.context = context;
	}
	
	public Context getContext() 
	{
		return context;
	}
	
	public void onWebClientUrlLis(String strUrl)
	{
		JiuyiLog.e("onWebClientUrlListen" , strUrl);
		if (strUrl == null || strUrl.length() <= 0)
		{
			return;
		}

		if (!strUrl.startsWith("http://") && strUrl.startsWith("/"))
		{
			if(strUrl.startsWith("//"))
				strUrl = strUrl.substring(1, strUrl.length());
		}
		
		String tempUrl = strUrl.toLowerCase();
		
		int nWenHaoPos = strUrl.indexOf("?");
		Map<String, String> valueMap = new HashMap<String, String>();
		
		if (nWenHaoPos > 0)
		{
			nWenHaoPos = nWenHaoPos + 1;
			String urlParam = strUrl;
			if (urlParam.endsWith("/"))
			{
				urlParam = urlParam.substring(nWenHaoPos, urlParam.length() - 1);
			}
			else
			{
				urlParam = urlParam.substring(nWenHaoPos, urlParam.length());
			}
			
			Func.getMapValue(urlParam, null, valueMap, "&", false);
		}
		else if (IsActionCall(strUrl, valueMap))
		{
			return;
		}
		if (IsActionPageType(strUrl, valueMap))
		{
			return;
		}


		else if (IsActionVideo(strUrl, valueMap))
		{
			return;
		}
		else if (IsActionBrowserOpen(strUrl, valueMap))
		{
			return;
		}

		else if(mJiuyiWebView != null)
		{
			if (!tempUrl.trim().equals("about:blank")) 
			{
				if (mJiuyiWebView.getCurWebView() != null)
				{
					mJiuyiWebView.loadUrl(strUrl);
				}
			}
		}
	}

	public boolean IsActionCall(String strUrl, Map<String, String> urlParamMap)
	{
		if (mJiuyiWebView != null && mJiuyiWebView.getWebViewClientUrlDealListener() != null)
		{
			boolean IsRet = mJiuyiWebView.getWebViewClientUrlDealListener().OnActionCall(strUrl, urlParamMap);
			if (IsRet)
			{
				return IsRet;
			}
		}else{
            new AjaxWebClientUrlLis(null).ActionCall(strUrl);
        }
		return false;
	}
	
	public boolean IsActionPageType(String strUrl, Map<String, String> urlParamMap)
	{
		if(Func.IsStringEmpty(strUrl))
			return false;

		if (strUrl != null && strUrl.length() > 0)
		{
			String strUrlStart = "http://action:";
			boolean bTradeAction = false;
			if(!Func.IsStringEmpty(strUrlStart) && strUrl.toLowerCase().startsWith(strUrlStart)){
				if (strUrl.endsWith("/"))
				{
					strUrl = strUrl.substring(0, strUrl.length() - 1);
				}

				strUrl = strUrl.substring(strUrlStart.length(), strUrl.length());
				
				int nWenHaoPos = strUrl.indexOf("?");
				String strAction = null;
				
				if (nWenHaoPos > 0)
				{
					strAction = strUrl.substring(0, nWenHaoPos);
					strUrl = strUrl.substring(nWenHaoPos + 1, strUrl.length());
				}
				else
				{
					strAction = strUrl;
				}
				
				if (strAction.indexOf("/") > 0)
				{
					strAction = strAction.substring(0, strAction.indexOf("/"));
				}
				
				int nAction = 0;
				strAction = strAction.trim();
				if (strAction != null && strAction.length() > 0)
				{
					nAction = Func.parseInt(strAction);
				}
				if (nAction > 0)
				{
					urlParamMap = onDealParamsWithAction(nAction, strUrl);
                    if (mJiuyiWebView != null && mJiuyiWebView.getWebViewClientUrlDealListener() != null)
                    {
                        boolean bIsRet = mJiuyiWebView.getWebViewClientUrlDealListener().OnActionPageType(nAction, mJiuyiWebView, strUrl, urlParamMap, bTradeAction);
                        if (bIsRet)
                        {
                            return bIsRet;
                        }
                    }else{
                        new AjaxWebClientUrlLis(null).ActionPageType(null, null, nAction, strUrl, bTradeAction);
                    }
				}
				
				return true;
			}
		}
		
		return false;
	}


	public boolean IsActionVideo(String strUrl, Map<String, String> urlParamMap)
	{
		String strUrlStart = "http://video:";
		if (strUrl != null && strUrl.length() > 0 && strUrl.startsWith(strUrlStart)) 
		{
			if (strUrl.endsWith("/"))
			{
				strUrl = strUrl.substring(0, strUrl.length() - 1);
			}
			
			strUrl = strUrl.substring(strUrlStart.length(), strUrl.length());
			String extension = MimeTypeMap.getFileExtensionFromUrl(strUrl);  
			String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);  
			Intent mediaIntent = new Intent(Intent.ACTION_VIEW);  
			mediaIntent.setDataAndType(Uri.parse(strUrl), mimeType);  
			getContext().startActivity(mediaIntent);
	        return true;
		}
		
		return false;
	}
	/**
	 * 在浏览器里打开
	 * @param strUrl
	 * @param urlParamMap
	 * @return
	 */
	public boolean IsActionBrowserOpen(String strUrl, Map<String, String> urlParamMap)
	{
		String strUrlStart =  "http://browseropen:";
		if (strUrl != null && strUrl.length() > 0 && strUrl.startsWith(strUrlStart)) 
		{
			try {
				strUrl = Func.URLDecoder(strUrl);
				//(browseropen是没有url=参数的，直接截取后面的参数就是url:安卓做一下兼容)
				String newUrl = getValueByUrl(strUrl,"url");
				if(Func.IsStringEmpty(newUrl))
					newUrl =strUrl.substring(strUrlStart.length());
				Uri uri = Uri.parse(newUrl);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				context.startActivity(intent);//actionBrowserOpen
			} catch (Exception e) {
			}
	        return true;
		}
		
		return false;
	}
	public String getValueByUrl(String url,String param){
		String value = "";
		if(url!=null && url.indexOf(param+"=") >= 0){
			url = url.substring(url.indexOf(param+"=")+(param+"=").length(), url.length());
			int splitpos = url.indexOf("&");
			if(splitpos < 0)
				splitpos = url.length();
			value = url.substring(0, splitpos);
		}
		return value;
	}

    public static Map<String, String> onDealParamsWithAction(int nAction, String strUrl)
	{
		Map<String, String> ayRetMap = new HashMap<String, String>();
		ayRetMap.clear();
		
		if (strUrl != null && strUrl.length() > 0)
		{
			switch (nAction)
			{
				default:
                    Func.getMapValue(strUrl, null, ayRetMap, "&", false);
					break;
			}
		}
		
		if (ayRetMap.size() > 0)
		{
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.clear();
			for (Entry<String, String> entry: ayRetMap.entrySet())
			{
				tempMap.put(entry.getKey(), /*Pub.URLDecoder(entry.getValue())*/Uri.decode(entry.getValue()));
			}
			ayRetMap.clear();
			ayRetMap = tempMap;
		}
		else
		{
			return ayRetMap;
		}
		
		return ayRetMap;
	}
    
}
