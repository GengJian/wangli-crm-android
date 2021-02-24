package com.control.widget.webview;

import android.os.Handler;
import android.webkit.JavascriptInterface;

import com.control.utils.Func;
import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewJavaScript.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:webview的js相关的操作
 * ****************************************************************
 */

public class JiuyiWebViewJavaScript
{
	private com.control.widget.webview.JiuyiWebView m_vCurWebView;
	private Handler handler ;
	
	public JiuyiWebViewJavaScript(com.control.widget.webview.JiuyiWebView webView)
	{
		m_vCurWebView = webView;
		//java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
		handler = new Handler(webView.getContext().getMainLooper());
	}
	@JavascriptInterface
	public void onJsOverrideUrlLoading(final String url) 
	{
		handler.post(new Runnable()
		{
			@Override
			public void run() 
			{
				if (m_vCurWebView != null)
				{
					m_vCurWebView.getWebViewDealUrlData().onWebClientUrlLis(url);
				}
			}
			
		});
	}


	
	@JavascriptInterface 
	public void getContent(String object)
	{
		if (m_vCurWebView != null && m_vCurWebView.getWebViewJavaScriptCallBack() != null)
		{
			m_vCurWebView.getWebViewJavaScriptCallBack().OnCallBack(object);
		}
	}
	/**
	 * 根据webview获取url的域名
	 * @return
	 */
	private String getDomain(){
		if (m_vCurWebView != null && m_vCurWebView.getCurWebView()!=null){
			String url = m_vCurWebView.getCurWebView().getUrl();
			if(url != null){
				//去掉前缀，留下域名部分
				if(url.contains("//")){
					url = url.substring(url.indexOf("//")+2, url.length());
				}
				if(url.startsWith("/")){
					url = url.substring(1, url.length());
				}
				if(url.indexOf("/") > 0){
					url = url.substring(0, url.indexOf("/"));
				}
				return url;
			}
		}
		return null;
	}
	/**
	 * 客户端调用web执行 js函数
	 * fuctionID	是功能号，网页根据功能号决定调用哪个js函数
	 * jsonData		调用js函数的参数
	 * 返回值		JsonString
	 */
	@JavascriptInterface 
	public String callWebFuctionFromNative(int fuctionID, String jsonData){
		if (m_vCurWebView != null){
			m_vCurWebView.getCurWebView().loadUrl("javascript:callWebFuctionFromNative("+fuctionID+", "+jsonData+");");
		}
		return "";
	}
	
	/**
	 * web调用客户端功能
	 * fuctionID
	 * jsonData		参数 
	 * 返回值		JsonString
	 */
	@JavascriptInterface 
	public String callNativeFuctionFromWeb(String webkey, String fuctionID, String jsonData){
		if(Func.IsStringEmpty(webkey) || Func.IsStringEmpty(fuctionID))
			return "";
		if(fuctionID.equals("Action")){
			try {
				JSONObject json = new JiuyiJSONObject(jsonData);
				String url = "http://action:" + json.getString("mAction") + "/?";
				Iterator<String> localIterator = json.keys();
				while (localIterator.hasNext()){
					String key = localIterator.next();
					if(!key.equals("mAction"))
						url += key+"="+json.getString(key)+"&";
				}
				onJsOverrideUrlLoading(url);
			} catch (JSONException e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
            
		}else if(fuctionID.equals("browseropen")){
			if (m_vCurWebView != null){
				try {
					JSONObject json = new JiuyiJSONObject(jsonData);
					String url = json.has("url") ? json.getString("url") : "";
					if(!Func.IsStringEmpty(url))
						m_vCurWebView.getWebViewDealUrlData().IsActionBrowserOpen("http://browseropen:"+url, null);
				} catch (JSONException e) {
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			}
		}
		JSONObject pJsonObject = new JiuyiJSONObject();
		return pJsonObject.toString();
	}
	
	//设置页面的参数
	@JavascriptInterface 
	public String setContentParams(String fuctionID, String jsonData){
		if(Func.IsStringEmpty(fuctionID))
			return "";
		if (fuctionID.equals("reqwebcontentparam")) {
			Map<String, String> valueMap = new HashMap<String, String>();
			try {
				JSONObject json = new JiuyiJSONObject(jsonData);
				Iterator<String> localIterator = json.keys();
				while (localIterator.hasNext()) {
					String key = localIterator.next();
					String value = json.getString(key);
					valueMap.put(key, value);
				}
			} catch (JSONException e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
			m_vCurWebView.getWebViewClientUrlDealListener().OnResetContentView(valueMap);
		}
		JSONObject pJsonObject = new JiuyiJSONObject();
		return pJsonObject.toString();
	}
    public String exec(String methodName, String strInJson, String callbackId) {
        if (strInJson == null || strInJson.equals("")) {
            strInJson = "{}";
        }
        System.out.println("methodName：：" + methodName);

        try {
            if (callbackId == null || callbackId.equals("")) {
                // 没有回调
                Method method = this.getClass().getMethod(methodName,
                        String.class);
                String result = (String) method.invoke(this, strInJson);
                System.out.println("result：：" + result);

                return result;

            } else {
                Class[] argsClass = new Class[2];

                for (int i = 0; i < 2; i++) {
                    argsClass[i] = String.class;
                }
                Method method = this.getClass().getMethod(methodName,
                        argsClass);
                method.invoke(this,
                        new Object[] { strInJson, callbackId });

                return null;
            }

        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // throw new RuntimeException("该js实现的java方法无法访问，请声明为public！");
            System.out.println("该js实现的java方法无法访问，请声明为public！");
            return null;
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // throw new RuntimeException("该js实现的java方法参数非标准json格式！");
            System.out.println("该js实现的java方法参数非标准json格式！");
            return null;
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // throw new RuntimeException("该js实现的java方法调用异常！");
            System.out.println("该js实现的java方法调用异常！");
            return null;
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println("没有找到该js实现的java方法2222 : " + methodName);
            // throw new RuntimeException("没有找到该js实现的java方法！");
            return null;
        }
    }
	@JavascriptInterface
	public String getSysVersion()
    {
        String result="";
        result= Rc.mSysVersion;
        return result;

    }
	@JavascriptInterface
	public String getNativeParamValue(String key)
	{
		String result="";
		if(key.equals("version")){
			result= Rc.mSysVersion;
		}else if(key.equals("token")){
			result= Rc.id_tokenparam;
		}
		return result;

	}

}
