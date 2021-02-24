package com.control.widget.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.control.utils.Res;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * ****************************************************************
 * 文件名称 : jiuyiWebView.java
 * 作 者 :   zhengss
 * 创建时间 : 2018-4-15 上午11:21:27
 * 文件描述 : 自定义的jiuyiWebView控件
 *
 * 透明WebView的实现
 * 		1、网页本身要求透明的，不能是有背景色+透明度的，否则实际上可能会显示出不透明的背景
 * 		2、WebView所在的布局使用xml书写，需要加属性android:layerType="software"；WebView外部的布局背景可以设置有透明度的背景色，android:background="#4f000000"
 * 		3、在代码钟WebView要设置：xinguweb.setBackgroundColor(0);xinguweb.getBackground().setAlpha(0);
 * 		4、如果是在Popwindow里弹出来此网页，则需要设置透明的png做背景，m_pPopupWindow.setBackgroundDrawable(pLayoutBase.getResources().getDrawable(Pub.getDrawabelID(pLayoutBase.Rc.getApplication(), "tzt_blank")));
 * 			如果不设置背景或设置为空setBackgroundDrawable(null),则点击返回键就不能关闭Popwindow
 * 自定义JS文件的写法
 * 		1、GtjaWebViewJavaScript jsclass = new GtjaWebViewJavaScript(pCallBack, m_vCenterWebView);
 * 		   m_vCenterWebView.setJavaScriptClass(jsclass);
 * 		2、在初始化webview之后，loadurl之前设置是自定义的JS文件都是有效的
 *****************************************************************
 */
@SuppressLint("SetJavaScriptEnabled")
public class JiuyiWebView extends RelativeLayout
{
	private JiuyiWebveiwKeyboardStruct pKeyboardStruct = new JiuyiWebveiwKeyboardStruct();//我那个也调用客户端键盘的相关操作
	
	private ArrayList<String> m_AyWebViewWithUrl = new ArrayList<String>();
	private Map<String, JiuyiWebViewEx> m_AyWebViewList = new HashMap<String, JiuyiWebViewEx>();
	
	private JiuyiWebViewDealUrlData pJiuyiWebViewDealUrlData;
	
	private JiuyiWebViewProgressListener pJiuyiWebViewProgressListener;
	private JiuyiWebViewClientUrlDealListener pJiuyiWebViewClientUrlDealListener;
	private JiuyiWebViewCERInfoListener pJiuyiWebViewRequestListener;
	private JiuyiWebViewJavaScriptCallBack pJiuyiWebViewJavaScriptCallBack;
	private JiuyiWebViewAudioListener pJiuyiWebViewAudioListener;
	private JiuyiWebViewCERRequestListener pJiuyiWebViewCERRequestListener;

	private Context pContext;
	private String _DefGoBackOnLoadMethod = "GoBackOnLoad();";
	private boolean m_bIsScrollToTop = true;
	private boolean _bIsDeleteView = true;
	private boolean _bIsReload = false;
	private JiuyiWebViewJavaScript mJiuyiWebViewJavaScript;
    //包含WebView的容器与WebView之间的回调
    private JiuyiWebViewContainerCallBack mJiuyiWebViewContainerCallBack;
    //webview的背景色,页面加载前显示
    public int m_nSkinType = 1;

	public JiuyiWebView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		pContext = context;
		m_AyWebViewList.clear();
		m_AyWebViewWithUrl.clear();
		pJiuyiWebViewDealUrlData = new JiuyiWebViewDealUrlData(getContext(), this);
	}

	public JiuyiWebView(Context arg0)
	{
		super(arg0);

		pContext = arg0;
		m_AyWebViewList.clear();
		m_AyWebViewWithUrl.clear();
		
		pJiuyiWebViewDealUrlData = new JiuyiWebViewDealUrlData(getContext(), this);

	}
	
    /**
     * 有的WebViewJavaScript类
     * @param jsclass
     */
    public void setJavaScriptClass(JiuyiWebViewJavaScript jsclass){
    	mJiuyiWebViewJavaScript = jsclass;
    }
	
    public JiuyiWebViewJavaScript getJavaScriptObject(){
    	return mJiuyiWebViewJavaScript;
    }


    /**
     * 包含WebView的容器与WebView之间的回调
     * @param callBack
     */
    public void setWebViewContainerCallBack(JiuyiWebViewContainerCallBack callBack){
        mJiuyiWebViewContainerCallBack = callBack;
    }

    /**
     * 获取当前界面的PageType
     * @return
     */
    private int getPageType(){
        return mJiuyiWebViewContainerCallBack !=null ? mJiuyiWebViewContainerCallBack.getPageType() : 0;
    }


	public JiuyiWebveiwKeyboardStruct getKeyboardStruct(){
		return pKeyboardStruct;
		
	}
	@SuppressLint("NewApi")
	public WebView newWebView(final String strUrl)
	{
		if(mJiuyiWebViewJavaScript == null){
			mJiuyiWebViewJavaScript = new JiuyiWebViewJavaScript(this);
		}

		final JiuyiWebViewEx vCurWebView = new JiuyiWebViewEx(getContext(), this, mJiuyiWebViewContainerCallBack);

		vCurWebView.getSettings().setSavePassword(false);
		LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		vCurWebView.setLayoutParams(lParams);

        vCurWebView.setScrollbarFadingEnabled(false);
        vCurWebView.setVerticalScrollBarEnabled(false);
        vCurWebView.setHorizontalScrollBarEnabled(false);
		//（所有版本都需要释放，页面有用到）
		vCurWebView.getSettings().setUserAgentString("http://www.jiuyisoft.com/ " + vCurWebView.getSettings().getUserAgentString());
		
		//支持chrome 调试
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
		

 
		vCurWebView.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				//获取y轴坐标
				float y = event.getRawY();
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
					{
	                    if (!v.hasFocus())
	                    {
	                    	v.setFocusable(true);
	                    	v.setFocusableInTouchMode(true);
	                    	v.requestFocus();
	                    	v.requestFocusFromTouch();

	                    	setWebLayout(strUrl);
	                    }

	                    pKeyboardStruct.setWebViewTouchX(0);
	                    pKeyboardStruct.setWebViewTouchY(0);
	                    pKeyboardStruct.setActionMove(false);

						int top = Res.Dip2Pix(50);
						int bottom = Res.Dip2Pix(350);
						if(vCurWebView.getUrl()!=null && vCurWebView.getUrl().contains("transactionTracking?")){
							if (y > top && y < bottom) {
								vCurWebView.requestDisallowInterceptTouchEvent(true);
							} else {
								vCurWebView.requestDisallowInterceptTouchEvent(false);
							}
						}
					}
						break;
	                case MotionEvent.ACTION_UP:
	                {
	                    if (!v.hasFocus())
	                    {
	                    	v.setFocusable(true);
	                    	v.setFocusableInTouchMode(true);
	                    	v.requestFocus();
	                    	v.requestFocusFromTouch();

	                    	setWebLayout(strUrl);
                        }

	                    pKeyboardStruct.setWebViewTouchX((int) event.getRawX());
	                    pKeyboardStruct.setWebViewTouchY((int) event.getRawY());

                        //网页调用自定义键盘
	                    if(mJiuyiWebViewContainerCallBack != null && mJiuyiWebViewContainerCallBack.getKeyBoard()!=null){
	                    	pKeyboardStruct.setWebViewTouchY(pKeyboardStruct.getWebViewTouchY() + mJiuyiWebViewContainerCallBack.getKeyBoard().getScrollToValue());
	                    	pKeyboardStruct.setPostDelayHideKeyBoard(vCurWebView, 300, mJiuyiWebViewContainerCallBack.getKeyBoard());
	                    }
	                }
					break;
	                case MotionEvent.ACTION_MOVE:
	                {
	                	pKeyboardStruct.setActionMove(true);
	                }
	                    break ;
				}

				return false;
			}

		});
		
		if (m_AyWebViewList == null)
		{
			m_AyWebViewList = new HashMap<String, JiuyiWebViewEx>();
			m_AyWebViewList.clear();
		}
		
		int nWenHaoPos = strUrl.indexOf("?");
		String tempUrl = "";
		
		if (nWenHaoPos > 0)
		{
			tempUrl = strUrl.substring(0, nWenHaoPos);
		}
		else
		{
			tempUrl = strUrl;
		}
		
		if (tempUrl != null && tempUrl.length() > 0)
		{
			if (m_AyWebViewList.get(tempUrl) != null)
			{
				m_AyWebViewList.remove(tempUrl);
			}
			m_AyWebViewList.put(tempUrl, vCurWebView);
		}
		
		JiuyiLog.i("WebViewUrl" , " newWebView:url="+strUrl+";nPageType="+ getPageType());
		vCurWebView.post(new Runnable()
			{
				@Override
				public void run()
				{
					vCurWebView.loadUrl(strUrl);
				}
			});
		setWebLayout(strUrl);
		return vCurWebView;
	}
	

	public void setWebLayout(String strUrl){

	}

	@SuppressLint("NewApi")
	public void loadUrl(String strUrl)
	{
		if (strUrl == null || strUrl.length() <= 0)
		{
			return;
		}
		//（不需要对整个url小写，只需要对key进行小写就可以）
//		String tempUrl = strUrl.toLowerCase();
		String tempUrl = strUrl;
		
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
			
			Func.getMapValueEx(urlParam, null, valueMap, "&&", true);
		}

        //防止url为未decode的乱码
        strUrl = Func.URLDecoder(strUrl);


		if (strUrl.startsWith("http://") || strUrl.startsWith("https://") || strUrl.startsWith("file:///android_asset/"))
		{
			this.removeAllViews();

			if (addRecentUrl(strUrl))//
			{
				addView(newWebView(strUrl), 0);
			}
			else
			{
				popWebViewWithUrl(strUrl);
			}
		}
		else if (getCurWebView() != null)
		{
			getCurWebView().loadUrl(strUrl);

		}
		
		setWebLayout(strUrl);
	}
	
	public void ClearWebViewCache()
	{
		m_AyWebViewWithUrl.clear();
	}
	
	public void setWebTextSize(TextSize nTextSize)
	{
		if (getCurWebView() != null)
		{
			WebSettings settings = getCurWebView().getSettings();
			settings.setSavePassword(false);
			settings.setTextSize(nTextSize);
		}
	}
	
	public void popWebViewWithUrl(String strUrl)
	{
		if (m_AyWebViewList.size() <= 0)
		{
			return;
		}
		
		if (m_AyWebViewWithUrl.size() <= 0)
		{
			return;
		}
		
		if (m_AyWebViewWithUrl.indexOf(strUrl) >= 0)
		{
			m_AyWebViewWithUrl.remove(strUrl);
			m_AyWebViewWithUrl.add(strUrl);

            JiuyiWebViewEx webView = m_AyWebViewList.get(m_AyWebViewWithUrl.get(m_AyWebViewWithUrl.size()-1));
			if (webView != null)
			{
				addView(webView, 0);
				if (!IsReplaceView())
				{
					if (getWebViewProgressListener() != null)
					{
						getWebViewProgressListener().StopPageProgress();
					}
					webView.loadUrl("javascript:OnRefreshWebView();");
				}
			}
		}
	}

	/**
	 * 关闭所有的webview
     * 有可能造成内存泄露（先不做处理）
	 */
	public void destroyAllWebViews(){
        m_AyWebViewList.clear();
	}
	
	public void closeCurrWebView()
	{
		if (m_AyWebViewWithUrl.size() > 0)
		{
			String strLastUrl = m_AyWebViewWithUrl.get(m_AyWebViewWithUrl.size()-1);

			m_AyWebViewWithUrl.remove(strLastUrl);
			//把他回收掉
			if(!Func.IsStringEmpty(strLastUrl)){
				if(m_AyWebViewList.containsKey(strLastUrl)){
                    m_AyWebViewList.remove(strLastUrl);
				}
			}


			m_AyWebViewList.remove(strLastUrl);
			
			try {
				if (m_AyWebViewList.size() > 0)
				{
					removeAllViews();
					addView(m_AyWebViewList.get(m_AyWebViewWithUrl.get(m_AyWebViewWithUrl.size()-1)), 0);
				}
			} catch (Exception e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}
	}
	
	public boolean addRecentUrl(String strUrl)
	{
		if (m_AyWebViewWithUrl == null)
		{
			m_AyWebViewWithUrl = new ArrayList<String>();
			m_AyWebViewWithUrl.clear();
		}
		
		int nWenHaoPos = strUrl.indexOf("?");
		
		if (nWenHaoPos > 0)
		{
			strUrl = strUrl.substring(0, nWenHaoPos);
		}
		
		if (m_AyWebViewWithUrl.size() <= 0)
		{
			m_AyWebViewWithUrl.add(strUrl);
		}
		else if (m_AyWebViewWithUrl.indexOf(strUrl) < 0)
		{
			m_AyWebViewWithUrl.add(strUrl);
		}
		else
		{
			if (IsReplaceView())
			{
				m_AyWebViewWithUrl.remove(strUrl);
				m_AyWebViewWithUrl.add(strUrl);
			}
			else
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean canGoBack()
	{
		if (getCurWebView() != null && getCurWebView().canGoBack())
		{
			return true;
		}

        return m_AyWebViewWithUrl.size() > 1;
    }
	
	public void reload()
	{
		if (getCurWebView() != null)
		{
			_bIsReload = true;
			getCurWebView().reload();
			JiuyiLog.i("WebViewUrl" , " reload:url="+getCurWebView().getUrl()+";nPageType="+getPageType());
			for (Entry<String, JiuyiWebViewEx> entry : m_AyWebViewList.entrySet())
			{
				View view = entry.getValue();
				if (view instanceof WebView)
				{
					((WebView) view).reload();
				}
			}
		}
	}

	/**
	 * 切换皮肤
	 */
	public void changeSkinType(){
		if (getCurWebView() != null)
		{
			if(m_AyWebViewList != null && m_AyWebViewList.size() > 0){
				for (Entry<String, JiuyiWebViewEx> entry : m_AyWebViewList.entrySet())
				{
					View view = entry.getValue();
				}
			}
		}
	}
	
	/**
	 * 10077刷新js
	 */
	public void refreshJsTimer(){
		if(getCurWebView() != null) {
			//（有异常说这里调用不在webview的线程中）
			getCurWebView().post(new Runnable() {
				@Override
				public void run() {
					getCurWebView().loadUrl("javascript:dealRefreshTimer()");
				}
			});
		}
	}
	
	public void goBack()
	{
		if (getCurWebView()!= null && getCurWebView().canGoBack())
		{
			getCurWebView().goBack();
		}
		else
		{
			removeAllViews();
			if (m_AyWebViewWithUrl.size() > 0)
			{
				String strLastUrl = m_AyWebViewWithUrl.get(m_AyWebViewWithUrl.size()-1);
				
				m_AyWebViewWithUrl.remove(strLastUrl);
				m_AyWebViewList.remove(strLastUrl);
				
				if (m_AyWebViewWithUrl.size() > 0)
				{
					strLastUrl = m_AyWebViewWithUrl.get(m_AyWebViewWithUrl.size()-1);

                    JiuyiWebViewEx webView = m_AyWebViewList.get(strLastUrl);
					if (webView != null)
					{
						if (getCurrNetState() == 1)
						{
							//webView. enablePlatformNotifications();
						}
						else
						{
							//webView.disablePlatformNotifications();
						}
						addView(webView, 0);
					}
				}
			}
		}

		if (getCurWebView() != null && getCurWebView().getUrl().startsWith("http://127.0.0.1"))
		{
			GoBackOnLoad();
		}
		
		if (m_bIsScrollToTop)
		{
			getCurWebView().scrollTo(0, 0);
		}
	}
	
	public void getContent(String strJSFunction, JiuyiWebViewJavaScriptCallBack callBack)
	{
		if (getCurWebView() != null)
		{
			pJiuyiWebViewJavaScriptCallBack = callBack;
			getCurWebView().loadUrl("javascript:window.MyWebView.getContent(" + strJSFunction + ");");
		}
	}
	
	public JiuyiWebViewJavaScriptCallBack getWebViewJavaScriptCallBack()
	{
		return pJiuyiWebViewJavaScriptCallBack;
	}
	
	public void setGoBackOnLoadMethod(String sGoBackOnLoadMethod)
	{
		if (sGoBackOnLoadMethod != null && sGoBackOnLoadMethod.length() > 0)
		{
			_DefGoBackOnLoadMethod = sGoBackOnLoadMethod;
		}
	}
	
	public String getGoBackOnLoadMethod()
	{
		return _DefGoBackOnLoadMethod;
	}
	
	public void GoBackOnLoad()
	{
		GoBackOnLoad("GoBackOnLoad();");
	}
	
	public void GoBackOnLoad(String sGoBackOnLoadMethod)
	{
		if (sGoBackOnLoadMethod == null || sGoBackOnLoadMethod.length() <= 0)
		{
			sGoBackOnLoadMethod = "GoBackOnLoad();";
		}
		
		if (getCurWebView() != null)
		{
			getCurWebView().loadUrl("javascript:" + sGoBackOnLoadMethod);
            JiuyiLog.i("WebViewUrl", "loadUrl:url="+"javascript:" + sGoBackOnLoadMethod);

			setGoBackOnLoadMethod("GoBackOnLoad();");

		}
	}
	
	public String getUrl()
	{
		if (getCurWebView() != null)
		{
			return getCurWebView().getUrl();
		}
		
		return "";
	}
	
	public JiuyiWebViewEx getCurWebView()
	{

		if (this.getChildCount() > 0)
		{
			return (JiuyiWebViewEx)this.getChildAt(0);
		}
		
		return null;
	}
	

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0){
				getWebViewDealUrlData().onWebClientUrlLis((String)msg.obj);
			}
		}
	};

	
	
	public String getTitle()
	{
		if (getCurWebView() != null)
		{
			try {
				String strRet = getCurWebView().getTitle();
                //如果strRet不正常，则不以当前strRet设置标题
				if (strRet != null && (strRet.startsWith("https://") || strRet.startsWith("http://") || strRet.startsWith("127.0.0.1") || strRet.startsWith("404"))) {
					return "";
				} else {
					if (strRet == null) {
						strRet = "";
					}else{
						if(strRet.indexOf("/") >= 0){
							String ip = strRet.substring(0, strRet.indexOf("/"));
							String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
							Pattern pat = Pattern.compile(rexp);
							Matcher mat = pat.matcher(ip);
							if (mat.find())
								strRet = "";
						}
					}
					return strRet;
				}
			} catch (Exception e) {
				return "";
			}
		}
		
		return "　";
	}

	public void setWebViewProgressListener(JiuyiWebViewProgressListener pJiuyiWebViewProgressListener)
	{
		this.pJiuyiWebViewProgressListener = pJiuyiWebViewProgressListener;
	}
	
	public JiuyiWebViewProgressListener getWebViewProgressListener()
	{
		if (_bIsReload)
		{
			_bIsReload = false;
			return null;
		}
		return pJiuyiWebViewProgressListener;
	}
	
	public void setWebViewClientUrlDealListener(JiuyiWebViewClientUrlDealListener pJiuyiWebViewClientUrlDealListener)
	{
		this.pJiuyiWebViewClientUrlDealListener = pJiuyiWebViewClientUrlDealListener;
	}
	
	public JiuyiWebViewClientUrlDealListener getWebViewClientUrlDealListener()
	{
		return pJiuyiWebViewClientUrlDealListener;
	}

	public JiuyiWebViewAudioListener getWebViewAudioListener()
	{
		return pJiuyiWebViewAudioListener;
	}
	
	public JiuyiWebViewDealUrlData getWebViewDealUrlData()
	{
		return pJiuyiWebViewDealUrlData;
	}
	

	public JiuyiWebViewCERRequestListener getWebViewCERRequestListener()
	{
		return pJiuyiWebViewCERRequestListener;
	}

	@Override
	public void setBackgroundColor(int color)
	{
		super.setBackgroundColor(color);
	}
	
	public int getCurrNetState() 
	{
		boolean bEnd = false;
		int nowConnectType = -1;//b.ConnectType;

		if (!bEnd) {
			int isWifi = android.provider.Settings.System.getInt(pContext.getContentResolver(), android.provider.Settings.System.WIFI_ON, 0);
			if (isWifi == 1) 
			{
				ConnectivityManager cm = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (info != null && info.getState() == State.CONNECTED) 
				{
					bEnd = true;
					nowConnectType = 2;// net;
				}
			}
		}
		if (!bEnd)
		{
			int isFly = android.provider.Settings.System.getInt(pContext.getContentResolver(), android.provider.Settings.System.AIRPLANE_MODE_ON, 0);
			if (isFly == 1) 
			{
				bEnd = true;
				nowConnectType = 2;// net;
			}
		}
		if (!bEnd) 
		{
			if (android.net.Proxy.getDefaultHost() != null && android.net.Proxy.getDefaultPort() != -1) 
			{
				bEnd = true;
				nowConnectType = 1;
			}
			else 
			{
				bEnd = true;
				nowConnectType = 2;// net;
			}
		}
		return nowConnectType;
	}

	public void setScrollToTop(boolean isScrollToTop)
	{
		m_bIsScrollToTop = isScrollToTop;
	}
	
	public boolean isNeedScrollToTop()
	{
		return m_bIsScrollToTop;
	}
	/**
	 * 是否需要删除并重新加载，默认是
	 * @param bDelete
	 */
	public void setReplaceView(boolean bDelete)
	{
		this._bIsDeleteView = bDelete;
	}
	
	public boolean IsReplaceView()
	{
		return _bIsDeleteView;
	}

	//刷新按钮
	ImageView btnRefresh = null;
	private void initRefreshButton(){
		int size = Res.Dip2Pix(36);
		btnRefresh = new ImageView(getContext());
		RelativeLayout.LayoutParams imagelp = new RelativeLayout.LayoutParams(size, size);
		imagelp.alignWithParent = true;
		imagelp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		imagelp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		imagelp.bottomMargin = Res.Dip2Pix(20);
		imagelp.rightMargin = Res.Dip2Pix(15);
		btnRefresh.setLayoutParams(imagelp);
		btnRefresh.setImageResource(Res.getDrawabelID(getContext(), "tzt_webview_reload_image"));
		addView(btnRefresh, getChildCount());
		
		btnRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(getCurWebView() != null){
					getCurWebView().reload();
				}
				btnRefresh.setVisibility(GONE);
			}
		});
	}

	public void addRefreshButton(){
		if(btnRefresh == null){
			initRefreshButton();
		}
		btnRefresh.setVisibility(View.VISIBLE);
	}
}
