
package com.control.widget.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;
import com.control.utils.MarketUtils;
import com.control.utils.OrderUtils;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.ReceiveUtils;
import com.control.utils.SDKVersion;

import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFastToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;




/**
 * 这个类解析了Android 4.0以下的WebView注入Javascript对象引发的安全漏洞。
 * 
 * @author zhengss
 * @since 2018-9-30
 */
public class JiuyiWebViewEx extends WebView {
    private static final boolean DEBUG = false;
    private static final String VAR_ARG_PREFIX = "arg";
    private static final String MSG_PROMPT_HEADER = "MyApp:";
    private static final String KEY_INTERFACE_NAME = "obj";
    private static final String KEY_FUNCTION_NAME = "func";
    private static final String KEY_ARG_ARRAY = "args";
    private static final String[] mFilterMethods = {
        "getClass",
        "hashCode",
        "notify",
        "notifyAll",
        "equals",
        "toString",
        "wait",
    };

    private HashMap<String, Object> mJsInterfaceMap = new HashMap<String, Object>();
    private String mJsStringCache = null;
    private JiuyiWebViewJavaScript mJiuyiWebViewJavaScript;

    //包含WebView的容器与WebView之间的回调
    private JiuyiWebViewContainerCallBack mJiuyiWebViewContainerCallBack;
    private JiuyiWebView m_vCurWebView;
	public int contentHeight = 0;//网页内容的高度
    private long nInitBodyTime = -1;//mBodyLayout初始化的时间

    public JiuyiWebViewEx(Context context, JiuyiWebView pJiuyiWebView, JiuyiWebViewContainerCallBack callBack) {
        super(context);
        m_vCurWebView = pJiuyiWebView;
        mJiuyiWebViewContainerCallBack = callBack;
        //mBodyLayout初始化的时间
        nInitBodyTime = System.currentTimeMillis();

        if(pJiuyiWebView != null)
        	mJiuyiWebViewJavaScript = pJiuyiWebView.getJavaScriptObject();

        //设置webview背景
        if(pJiuyiWebView.m_nSkinType != 1)
            setBackgroundColor(pJiuyiWebView.m_nSkinType == 1 ? Color.WHITE : Color.BLACK);

        init(context);
    }

    public JiuyiWebView getWebView(){
    	return m_vCurWebView;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void init(Context context) {

        setTag("WebViewEx");
    	WebSettings webSettings = getSettings();
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		//（开启缓存）
		webSettings.setAppCacheEnabled(true);
		requestFocusFromTouch();
		requestFocus();

		webSettings.setJavaScriptEnabled(true);
		addJavascriptInterface(mJiuyiWebViewJavaScript, "MyWebView");
		//设置js打开新窗口
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); 
		//设置不保存密码
		webSettings.setSavePassword(false);
        // 允许访问本地文件
		webSettings.setAllowFileAccess(true);
		webSettings.setDatabaseEnabled(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setGeolocationEnabled(true);

		webSettings.setUseWideViewPort(true);  
		// 设置显示缩放按钮
		webSettings.setBuiltInZoomControls(false);
		// 支持缩放
		webSettings.setSupportZoom(false);
		//设置WebView使用广泛的视窗
        webSettings.setUseWideViewPort(true);//关键点
		//设置WebView 可以加载更多格式页面
		webSettings.setLoadWithOverviewMode(true);//Tell the WebView to load image resources automatically.

        String dir = Rc.getApplication().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(dir);

        /**
         * 网页使用硬件加速，会导致刷新网页时界面呈现块状刷新
         * 首页的和有tab的界面的几个网页不需要硬件加速
         */
        if(mJiuyiWebViewContainerCallBack != null && isHardwareAccelerated()){
            switch(mJiuyiWebViewContainerCallBack.getPageType()){
                //RootLayout的界面，所以要执行popAllActivity();
                case Pub.MENU_Message:
                case Pub.MENU_Normal:
                case Pub.MENU_Mine://
                case Pub.MENU_SYS_SetStartPage:
                case Pub.MENU_Customer:
                    setLayerType(LAYER_TYPE_SOFTWARE, null);
                    break;
                default:
                    break;
            }
        }

        Activity activity = mJiuyiWebViewContainerCallBack ==null?null: mJiuyiWebViewContainerCallBack.getActivityBase();
		setWebViewClient(new WebViewClientListener());
		setWebChromeClient(new WebViewChromeListener(activity));
        // 网页中下载事件的捕获
        setDownloadListener(new JiuyiWebViewDownLoadListener(context));
        // 删除掉Android默认注册的JS接口
        removeSearchBoxImpl();
    }
    /**
     * Reloads the current URL.
     */
    public void reload() {
        super.reload();
    }
    /**
     * WebViewJavaScript类
     * @param jsclass
     */
    public void setJavaScriptClass(JiuyiWebViewJavaScript jsclass){
    	mJiuyiWebViewJavaScript = jsclass;
    	addJavascriptInterface(mJiuyiWebViewJavaScript, "MyWebView");
    }

    public JiuyiWebViewJavaScript getJavaScriptObject(){
    	return mJiuyiWebViewJavaScript;
    }

    @Override
    public void addJavascriptInterface(Object obj, String interfaceName) {
//        if (TextUtils.isEmpty(interfaceName)) {
//            return;
//        }
//
//        // 如果在4.2以上，直接调用基类的方法来注册
//        if (hasJellyBeanMR1()) {
//            super.addJavascriptInterface(obj, interfaceName);
//        } else {
            mJsInterfaceMap.put(interfaceName, obj);
//        }
    }

    @Override
    public void removeJavascriptInterface(String interfaceName) {
        if (hasJellyBeanMR1()) {
            super.removeJavascriptInterface(interfaceName);
        } else {
            mJsInterfaceMap.remove(interfaceName);
            mJsStringCache = null;
            injectJavascriptInterfaces();
        }
    }

    private boolean removeSearchBoxImpl() {
        if (hasHoneycomb() && !hasJellyBeanMR1()) {
            super.removeJavascriptInterface("searchBoxJavaBridge_");
            super.removeJavascriptInterface("accessibility");
            super.removeJavascriptInterface("accessibilityTraversal");
            return true;
        }

        return false;
    }

    private void injectJavascriptInterfaces() {
        if (!TextUtils.isEmpty(mJsStringCache)) {
            loadJavascriptInterfaces();
            return;
        }

        String jsString = genJavascriptInterfacesString();
        mJsStringCache = jsString;
        loadJavascriptInterfaces();
    }

    public void injectJavascriptInterfaces(WebView webView) {
        if (webView instanceof JiuyiWebViewEx) {
            injectJavascriptInterfaces();
        }
    }

    private void loadJavascriptInterfaces() {
        //（添加空指针判断）
        if(!Func.IsStringEmpty(mJsStringCache)){
            this.loadUrl(mJsStringCache);
        }

    }

    private String genJavascriptInterfacesString() {
        if (mJsInterfaceMap.size() == 0) {
            mJsStringCache = null;
            return null;
        }

        /*
         * 要注入的JS的格式，其中XXX为注入的对象的方法名，例如注入的对象中有一个方法A，那么这个XXX就是A
         * 如果这个对象中有多个方法，则会注册多个window.XXX_js_interface_name块，我们是用反射的方法遍历
         * 注入对象中的所有带有@JavaScripterInterface标注的方法
         *
         * javascript:(function JsAddJavascriptInterface_(){
         *   if(typeof(window.XXX_js_interface_name)!='undefined'){
         *       console.log('window.XXX_js_interface_name is exist!!');
         *   }else{
         *       window.XXX_js_interface_name={
         *           XXX:function(arg0,arg1){
         *               return prompt('MyApp:'+JSON.stringify({obj:'XXX_js_interface_name',func:'XXX_',args:[arg0,arg1]}));
         *           },
         *       };
         *   }
         * })()
         */

        Iterator<Entry<String, Object>> iterator = mJsInterfaceMap.entrySet().iterator();
        // Head
        StringBuilder script = new StringBuilder();
        script.append("javascript:(function JsAddJavascriptInterface_(){");

        // Add methods
        try {
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                String interfaceName = entry.getKey();
                Object obj = entry.getValue();

                createJsMethod(interfaceName, obj, script);
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }

        // End
        script.append("})()");

        return script.toString();
    }

    private void createJsMethod(String interfaceName, Object obj, StringBuilder script) {
        if (TextUtils.isEmpty(interfaceName) || (null == obj) || (null == script)) {
            return;
        }

        Class<? extends Object> objClass = obj.getClass();

        script.append("if(typeof(window.").append(interfaceName).append(")!='undefined'){");
        if (DEBUG) {
            script.append("    console.log('window." + interfaceName + "_js_interface_name is exist!!');");
        }

        script.append("}else {");
        script.append("    window.").append(interfaceName).append("={");

        // Add methods
        Method[] methods = objClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            // 过滤掉Object类的方法，包括getClass()方法，因为在Js中就是通过getClass()方法来得到Runtime实例
            if (filterMethods(methodName)) {
                continue;
            }

            script.append("        ").append(methodName).append(":function(");
            // 添加方法的参数
            int argCount = method.getParameterTypes().length;
            if (argCount > 0) {
                int maxCount = argCount - 1;
                for (int i = 0; i < maxCount; ++i) {
                    script.append(VAR_ARG_PREFIX).append(i).append(",");
                }
                script.append(VAR_ARG_PREFIX).append(argCount - 1);
            }

            script.append(") {");

            // Add implementation
            if (method.getReturnType() != void.class) {
                script.append("            return ").append("prompt('").append(MSG_PROMPT_HEADER).append("'+");
            } else {
                script.append("            prompt('").append(MSG_PROMPT_HEADER).append("'+");
            }

            // Begin JSON
            script.append("JSON.stringify({");
            script.append(KEY_INTERFACE_NAME).append(":'").append(interfaceName).append("',");
            script.append(KEY_FUNCTION_NAME).append(":'").append(methodName).append("',");
            script.append(KEY_ARG_ARRAY).append(":[");
            //  添加参数到JSON串中
            if (argCount > 0) {
                int max = argCount - 1;
                for (int i = 0; i < max; i++) {
                    script.append(VAR_ARG_PREFIX).append(i).append(",");
                }
                script.append(VAR_ARG_PREFIX).append(max);
            }

            // End JSON
            script.append("]})");
            // End prompt
            script.append(");");
            // End function
            script.append("        }, ");
        }

        // End of obj
        script.append("    };");
        // End of if or else
        script.append("}");
    }

    private boolean handleJsInterface(WebView view, String url, String message, String defaultValue,
            JsPromptResult result) {
        String prefix = MSG_PROMPT_HEADER;
        if (!message.startsWith(prefix)) {
            return false;
        }

        String jsonStr = message.substring(prefix.length());
        try {
            JSONObject jsonObj = new JiuyiJSONObject(jsonStr);
            String interfaceName = jsonObj.getString(KEY_INTERFACE_NAME);
            String methodName = jsonObj.getString(KEY_FUNCTION_NAME);
            JSONArray argsArray = jsonObj.getJSONArray(KEY_ARG_ARRAY);
            Object[] args = null;
            if (null != argsArray) {
                int count = argsArray.length();
                if (count > 0) {
                    args = new Object[count];

                    for (int i = 0; i < count; ++i) {
                        args[i] = argsArray.get(i);
                    }
                }
            }

            if (invokeJSInterfaceMethod(result, interfaceName, methodName, args)) {
                return true;
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }

        result.cancel();
        return false;
    }

    private boolean invokeJSInterfaceMethod(JsPromptResult result,
            String interfaceName, String methodName, Object[] args) {

        boolean succeed = false;
        final Object obj = mJsInterfaceMap.get(interfaceName);
        if (null == obj) {
            result.cancel();
            return false;
        }

        Class<?>[] parameterTypes = null;
        int count = 0;
        if (args != null) {
            count = args.length;
        }

        if (count > 0) {
            parameterTypes = new Class[count];
            for (int i = 0; i < count; ++i) {
                parameterTypes[i] = getClassFromJsonObject(args[i]);
            }
        }

        try {
            Method method = obj.getClass().getMethod(methodName, parameterTypes);
            Object returnObj = method.invoke(obj, args); // 执行接口调用
            boolean isVoid = returnObj == null || returnObj.getClass() == void.class;
            String returnValue = isVoid ? "" : returnObj.toString();
            result.confirm(returnValue); // 通过prompt返回调用结果
            succeed = true;
        } catch (NoSuchMethodException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }

        result.cancel();
        return succeed;
    }

    private Class<?> getClassFromJsonObject(Object obj) {
        Class<?> cls = obj.getClass();

        // js对象只支持int boolean string三种类型
        if (cls == Integer.class) {
            cls = Integer.TYPE;
        } else if (cls == Boolean.class) {
            cls = Boolean.TYPE;
        } else {
            cls = String.class;
        }

        return cls;
    }

    private boolean filterMethods(String methodName) {
        for (String method : mFilterMethods) {
            if (method.equals(methodName)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= SDKVersion.SDK_MINI;
    }

    private boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= SDKVersion.SDK_VERSION17;
    }


    /**
     * WebView监听处理
     * @author ToumServer
     *
     */
    class WebViewClientListener extends WebViewClient
    {
    	@Override
    	@JavascriptInterface 
    	public boolean shouldOverrideUrlLoading(WebView view, String url)
    	{
            if (m_vCurWebView != null && url != null && (url.startsWith("http:") || url.startsWith("https:")|| url.startsWith("action"))  && m_vCurWebView.getWebViewDealUrlData() != null)
            {

                m_vCurWebView.getWebViewDealUrlData().onWebClientUrlLis(url);
            }
    		return true;
    	}
    	
    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) 
    	{
    		injectJavascriptInterfaces(view);
    		if (m_vCurWebView != null && m_vCurWebView.getWebViewDealUrlData() != null)
    		{
	    		if (m_vCurWebView.getWebViewDealUrlData().IsActionCall(url, null))
	    		{
	    			return;
	    		}
	    		else if (m_vCurWebView.getWebViewDealUrlData().IsActionPageType(url, null))
	    		{
	    			return;
	    		}
	    		else{
	    			if (m_vCurWebView != null && m_vCurWebView.getWebViewProgressListener() != null)
	    			{
	    				m_vCurWebView.getWebViewProgressListener().StartPageProgress();
	    			}
	    			super.onPageStarted(view, url, favicon);
	    		}
    		}else{
    			super.onPageStarted(view, url, favicon);
    		}
    	}
    	
    	@Override
    	public void onPageFinished(WebView view, String url) 
    	{
    		injectJavascriptInterfaces(view);

            if (m_vCurWebView != null && m_vCurWebView.isNeedScrollToTop())
            {
                m_vCurWebView.scrollTo(0, 0);
            }

            if (m_vCurWebView != null && m_vCurWebView.getWebViewProgressListener() != null)
            {
                m_vCurWebView.getWebViewProgressListener().StopPageProgress();
            }
            super.onPageFinished(view, url);
    	}


    	@Override
    	public void onLoadResource(WebView view, String url)
    	{
    		injectJavascriptInterfaces(view);

    		super.onLoadResource(view, url);
    	}

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            injectJavascriptInterfaces(view);
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    	
    	@Override
    	public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) 
    	{
    		super.onReceivedHttpAuthRequest(view, handler, host, realm);
    	}

    	@Override
    	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
    	{
    		super.onReceivedError(view, errorCode, description, failingUrl);
    	}


    	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
    		//handler.cancel(); 默认的处理方式，WebView变成空白页
    		handler.proceed();//如果只是简单的接受所有证书的话，就直接调process()方法就行了
    		shouldOverrideUrlLoading(view, view.getUrl());
    		//handleMessage(Message msg); 其他处理
//    		super.onReceivedSslError(view, handler, error);
    	}

    }
    
    public class WebViewChromeListener extends WebChromeClient
    {
    	protected Activity m_pActivity;

    	public WebViewChromeListener(Activity activity)
    	{
    		m_pActivity = activity;
    	}
    	/**
    	 * 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
    	 */
    	private android.content.DialogInterface.OnKeyListener m_pOnKeyListener = new android.content.DialogInterface.OnKeyListener() 
    	{
    		@Override
    		public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) 
    		{
    			return true;
    		}
    	};

    	@Override
    	public void onCloseWindow(WebView window) 
    	{
    		super.onCloseWindow(window);
    	}

    	@Override
    	public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) 
    	{
    		return super.onCreateWindow(view, dialog, userGesture, resultMsg);
    	}
    	
    	/**
    	 * @param message
    	 * @return
    	 */
    	public boolean IsNetworkError(String message){
    		if(message == null)
    			return false;
            return false;
    	}

    	/**
    	 * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”
    	 * ?message=内容&type=1&button=启用,|取消,|&id=&
    	 */
    	@Override
    	public boolean onJsAlert(WebView view, String url, String message, JsResult result) 
    	{
            // 首页预加载，导致m_pActivity为空，转到首页就在首页弹出dialog
            if(m_pActivity == null && mJiuyiWebViewContainerCallBack != null) {
                m_pActivity = mJiuyiWebViewContainerCallBack.getActivityBase();
            }
            if(m_pActivity == null) {
                result.cancel();
                return false;
            }
    		if(IsNetworkError(message)){
                JiuyiFastToast.show(m_pActivity, message);
    			result.cancel();
    			return true;
    		}
    		final String belongtoactivity = (m_pActivity == null) ? "" : (m_pActivity.hashCode()+"");
    		if (Rc.cfg.getTztUIIntentInterface() != null){
                if(m_pActivity != null && !m_pActivity.isFinishing()) {

                }else{
                    result.cancel();
                }
    		}else{
    			final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
    			// builder.setTitle("测试alert对话框")
    			builder.setTitle(Rc.cfg.getDefDialogTitle()).setMessage(message).setPositiveButton("确定", null);
    			// 不需要绑定按键事件
    			// 屏蔽keycode等于84之类的按键
    			builder.setOnKeyListener(m_pOnKeyListener);
    			// 禁止响应按back键的事件
    			builder.setCancelable(false);
    			AlertDialog dialog = builder.create();
    			dialog.show();
    		}
    		result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
    		return true;
    	}

    	@Override
    	public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) 
    	{
    		return super.onJsBeforeUnload(view, url, message, result);
    	}

    	/**
    	 * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
    	 * message约定：温馨提示、操作类型1华西启用栏目、栏目ID ?message=内容&type=1&button=启用&id=&
    	 */
    	@Override
    	public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) 
    	{
            //首页预加载，导致m_pActivity为空，转到首页就在首页弹出dialog
            if(m_pActivity == null && mJiuyiWebViewContainerCallBack != null) {
                m_pActivity = mJiuyiWebViewContainerCallBack.getActivityBase();
            }
            if(m_pActivity == null) {
                result.cancel();
                return false;
            }
    		if(IsNetworkError(message)){
                JiuyiFastToast.show(m_pActivity, message);
    			result.cancel();
    			return true;
    		}
    		if (Rc.cfg.getTztUIIntentInterface() != null){
                if(m_pActivity != null && !m_pActivity.isFinishing()) {
                    JiuyiWebJsDialogLayout dlg = new JiuyiWebJsDialogLayout(mJiuyiWebViewContainerCallBack, null, message);
                    dlg.setJsResult(result);

                    JiuyiDialogBase dialog = new JiuyiDialogBase();
                    dialog.startDialog(m_pActivity, dlg, DialogID.DialogOnJsConfirm, "", message, JiuyiDialogBase.Dialog_Type_YesNo, null);
                    dialog.show();
                }else {
                    result.cancel();
                }
    		}else{
    			final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
    			builder.setTitle(Rc.cfg.getDefDialogTitle()).setMessage(message)
    			.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener()
    			{
    				@Override
    				public void onClick(DialogInterface dialog, int which)
    				{
    					result.confirm();
    				}
    			})
    			.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener()
    			{
    				@Override
    				public void onClick(DialogInterface dialog, int which)
    				{
    					result.cancel();
    				}
    			});
    			builder.setOnCancelListener(new AlertDialog.OnCancelListener()
    			{

    				@Override
    				public void onCancel(DialogInterface dialog)
    				{

    				}

    			});
    			// 屏蔽keycode的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
    			builder.setOnKeyListener(m_pOnKeyListener);
    			// 禁止响应按back键的事件
    			AlertDialog dialog = builder.create();
    			dialog.show();
    		}
    		return true;
    	}

    	/**
    	 * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////”
    	 * window.prompt('请输入您的域名地址', '618119.com');
    	 */
    	@Override
    	public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) 
    	{
            if (view instanceof JiuyiWebViewEx) {
                if (handleJsInterface(view, url, message, defaultValue, result)) {
                    return true;
                }
            }

            return super.onJsPrompt(view, url, message, defaultValue, result);
    	}

    	// For Android 3.0+
    	public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mJiuyiWebViewContainerCallBack.setWebViewValueCallback(uploadMsg);
    		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
    		i.addCategory(Intent.CATEGORY_OPENABLE);
    		i.setType("image/*");
    		if(m_pActivity != null)
    		m_pActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), mJiuyiWebViewContainerCallBack.getFILECHOOSER_RESULTCODE());
    	}

    	// For Android 3.0+
    	public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mJiuyiWebViewContainerCallBack.setWebViewValueCallback(uploadMsg);
    		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
    		i.addCategory(Intent.CATEGORY_OPENABLE);
    		i.setType("*/*");
    		if(m_pActivity != null)
    		m_pActivity.startActivityForResult(Intent.createChooser(i, "File Browser"), mJiuyiWebViewContainerCallBack.getFILECHOOSER_RESULTCODE());
    	}

    	// For Android 4.1
    	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mJiuyiWebViewContainerCallBack.setWebViewValueCallback(uploadMsg);
    		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
    		i.addCategory(Intent.CATEGORY_OPENABLE);
    		i.setType("image/*");
    		if(m_pActivity != null)
    			m_pActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), mJiuyiWebViewContainerCallBack.getFILECHOOSER_RESULTCODE());
    	} 
    	
    	@Override
    	public void onProgressChanged(WebView view, int newProgress) 
    	{
    		injectJavascriptInterfaces(view);

    		super.onProgressChanged(view, newProgress);
    		if (m_pActivity != null) 
    		{
    			m_pActivity.setProgress(newProgress * 1000);
    		}
    	}

    	@Override
    	public void onReceivedIcon(WebView view, Bitmap icon) 
    	{
    		super.onReceivedIcon(view, icon);
    	}

    	@Override
    	public void onReceivedTitle(WebView view, String title)
    	{
    		injectJavascriptInterfaces(view);

    		super.onReceivedTitle(view, title);
    	}

    	@Override
    	public void onRequestFocus(WebView view) 
    	{
    		super.onRequestFocus(view);
    	}
    	
    	
    }

    /**
     * 当 ScrollView 中的view滚动导致View不可见了，会调用 onWindowVisibilityChanged 方法，
     * 注意是完全不可见才会调用 onWindowVisibilityChanged，当滚到导致View部分可见的时候也会调用onWindowVisibilityChanged方法，
     * 注意是部分可见也会调用，这样就可以监听滚动控件中View的可见性。
     * @param visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE){
            JiuyiLog.d("onWindowVisibilityChanged" ,"可见");
            //每次显示该webview加载一次
            if(bCssFaildReload > 1){
                bCssFaildReload--;
                reload();
            }
        } else if(visibility == INVISIBLE || visibility == GONE){
            JiuyiLog.d("onWindowVisibilityChanged" ,"不可见");
        }
    }

    /**
     * 检测当前view是否被遮住显示不全
     * @return
     */
    protected boolean isCover() {
        boolean cover = false;
        Rect rect = new Rect();
        //getGlobalVisibleRect检测View是部分可见或者完全可见
        cover = getGlobalVisibleRect(rect);
        if (cover) {
            if (rect.width() >= getMeasuredWidth() && rect.height() >= getMeasuredHeight()) {
                return !cover;
            }
        }
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        JiuyiLog.d("onWindowVisibilityChanged" ,"onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        JiuyiLog.d("onWindowVisibilityChanged" ,"onDetachedFromWindow");
    }
    /**
     * 是否进行过css请求失败的reload，进行过则不能再自动reload，但是可以在onAttachedToWindow里reload
     */
    private int bCssFaildReload = 0;//

    /**
     * 获取CSS加载错误是否可以进行reload，一个webview只能这样reload一次，如果再出现则弹出刷新按钮
     */
    public boolean getCssFaildReload(){
        return bCssFaildReload <= 1;
    }
    /**
     * webview从隐藏到显示，如果>1表示自动reload之后还是出现了bug，所以每次webview切换到显示都在onHiddenChanged里进行reload
     */
    public boolean getCssFaildHideChangeReload() {
        return bCssFaildReload > 1;
    }
    /**
     * 每次webview切换到显示都在onHiddenChanged里进行reload
     * reload后--，防止无限加载
     */
    public void setCssFaildHideChangeReload(){
        //设置为1，防止一个界面多个元素加载失败，导致的需要多执行该方法
        this.bCssFaildReload = 1;
        reload();
    }
    /**
     * 设置CSS加载错误Reload后的已经reload的状态
     * 只能加不能减，刷新1次弹出2次对话框之后就不能再刷新了，否则可能会出现死循环
     * @param bCssFaildReload
     */
    public void setCssFaildReload(boolean bCssFaildReload){
        if(bCssFaildReload)
            this.bCssFaildReload++;
    }
    /**
     * 判断是否超时，就是说CSSFAILED_EXCEPTION_SOCKETERR要在很短的时间内才能执行doWebReloadDelayCycle
     * 否则可能已经加载成功了但是底层自己报此异常
     */
    public long getInitTime(){
        return nInitBodyTime;
    }


}


