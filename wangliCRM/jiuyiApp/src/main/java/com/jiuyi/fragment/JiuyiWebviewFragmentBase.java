package com.jiuyi.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.keyboard.JiuyiKeyBoardView;
import com.control.widget.webview.JiuyiWebView;
import com.control.widget.webview.JiuyiWebViewClientUrlDealListener;
import com.control.widget.webview.JiuyiWebViewProgressListener;
import com.control.widget.webview.JiuyiWebViewContainerCallBack;
import com.tencent.qcloud.sdk.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jzvd.JzvdStd;
import customer.entity.Media;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebviewFragmentBase.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/26 14:43
 * ****************************************************************
 */

public class JiuyiWebviewFragmentBase extends JiuyiFragmentBase {
    /** 当前的WebView */
    public JiuyiWebView mWebView;
    /** webview要加载的url */
    public String mWebUrl;
    /** 带选择日期的开始时间 */
    public String mBeginDate;
    /** 带选择日期的结束时间 */
    public String mEndData;

    public boolean canSetTitle = true;



    public String mObjectForTitleType;//

    /** 第一级功能号（主要用于登录后退） */
    protected int m_nCurActionPageType = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mBundle != null){
            mBeginDate = mBundle.getString(JiuyiBundleKey.PARAM_DATE_BEGIN);
            mEndData = mBundle.getString(JiuyiBundleKey.PARAM_DATE_END);
        }
    }

    /**
     * 当Fragment 从隐藏切换至显示的时候，不会调用onResume(),
     * Fragment 的onResume()是Fragment 和它的宿主在切换时才会调用的，
     * 而Fragment 之间切换时，不会调用onResume()方法，
     * Fragment 之间切换，Fragment 从隐藏切换至显示，会调用onHiddenChanged(boolean hidden)方法
     * @param hidden
     */
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(getCurrWebView() != null) {
            if (hidden) {// 不在最前端界面显示
                //不处理
            } else {// 重新显示到最前端中
                //如果bCssFaildReload>1表示自动reload之后还是出现了bug，所以每次webview切换到显示都在onHiddenChanged里进行reload
                if (getCurrWebView().getCurWebView() != null) {
                    if(getCurrWebView().getCurWebView().getCssFaildHideChangeReload()){
                        getCurrWebView().getCurWebView().setCssFaildHideChangeReload();
                    }
                }
            }
        }
    }
    /**
     * 获取当前的webview
     */
    public JiuyiWebView getCurrWebView(){
        return mWebView;
    }
    /**
     * 重新加载
     */
    public void reload(){
        if(mWebView != null) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.reload();
                }
            });
        }
    }
    /**
     * 获取当前的url
     */
    public String getUrl(){
        if(mWebView==null || mWebView.getCurWebView()==null || Func.IsStringEmpty(mWebView.getCurWebView().getUrl()))
            return mWebUrl;
        else
            return mWebView.getCurWebView().getUrl();
    }
    /**
     * 加载网页url
     */
    public void loadUrl(final String url){
        if(mWebView != null) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(url);
                }
            });
        }
    }



    /**
     * 切换皮肤
     */
    @Override
    public void changeSkinType(){
        if(mWebView != null) {
            if (mWebView.getCurWebView() != null)
                mWebView.getCurWebView().setBackgroundColor(Res.getColor(getContext(), "tzt_v23_background_color"));

            mWebView.changeSkinType();
            //由切换皮肤引起的setTitle不可用
            canSetTitle = false;
        }
    }

    /**
     * 设置一些WebView回调
     */
    public void onSetWebViewCallBack() {
        if (mWebView != null)
        {
            mWebView.setWebViewClientUrlDealListener(new JiuyiWebViewClientUrlDealListener()
            {
                @Override
                public boolean OnReturenBack()
                {
                    onReturnBack();
                    return false;
                }

                /**
                 * 网页调用的正在加载url？
                 *
                 * @param url
                 * @param urlParamMap
                 */
                @Override
                public boolean OnLoadingUrl(String url, Map<String, String> urlParamMap) {
                    return false;
                }



                @Override
                public boolean OnActionPageType(int nAction, JiuyiWebView jiuyiWebView, String urlParam, Map<String, String> urlParamMap, boolean bTradeAction)
                {
                    boolean bReturn = JiuyiWebviewFragmentBase.this.OnActionPageType(nAction, jiuyiWebView, urlParam, urlParamMap, bTradeAction);
                    if(bReturn)
                        return true;

                    if (mCallBack != null && mCallBack.getAjaxWebClientUrlLis() != null)
                    {
                        mCallBack.getAjaxWebClientUrlLis().ActionPageType(JiuyiWebviewFragmentBase.this, mWebView, nAction, urlParam, bTradeAction);
                    }
                    return false;
                }

                @Override
                public boolean OnActionCall(String url, Map<String, String> urlParamMap)
                {
                    if (mCallBack != null && mCallBack.getAjaxWebClientUrlLis() != null)
                    {
                        return mCallBack.getAjaxWebClientUrlLis().ActionCall(url);
                    }
                    return false;
                }

                @Override
                public boolean OnResetContentView(Map<String, String> urlParamMap) {
                    return false;
                }
            });

            mWebView.setWebViewProgressListener(new JiuyiWebViewProgressListener()
            {

                @Override
                public void StopProgress()
                {
                    if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null) {
                        mCallBack.getActivityCanvasInterface().showProcessBar(100);
                    }
                }

                @Override
                public void StartProgress()
                {
                    if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
                        mCallBack.getActivityCanvasInterface().showProcessBar(0);
                    }
                }

                @Override
                public void StartPageProgress()
                {
                    if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
                        mCallBack.getActivityCanvasInterface().showProcessBar(0);
                    }
                }

                @Override
                public void StopPageProgress()
                {
                    if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
                        mCallBack.getActivityCanvasInterface().showProcessBar(100);
                    }
                }
            });
        }
        // (修复mWebViewContainerCallBack为null导致网页无法点击)
        mWebView.setWebViewContainerCallBack(new JiuyiWebViewContainerCallBack() {
            @Override
            public Activity getActivityBase() {
                return getActivity();
            }

            @Override
            public JiuyiKeyBoardView getKeyBoard() {
                return null;
            }

            @Override
            public int getPageType() {
                return 0;
            }

            @Override
            public void startDialog( int nAction, String sTitle, String sContent, int nBtnType) {
                if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null)
                    mCallBack.getActivityCanvasInterface().startDialog(nAction, sTitle, sContent, nBtnType, null);
            }

            @Override
            public ValueCallback<Uri> getWebViewValueCallback() {
                return null;
            }

            @Override
            public void setWebViewValueCallback(ValueCallback<Uri> mUploadMessage) {

            }

            @Override
            public int getFILECHOOSER_RESULTCODE() {
                return 0;
            }
        });
    }

    /**
     * 网页调用返回
     */
    public void onReturnBack()
    {
        super.backPage();
    }


    /**
     * 网页如果调用自定义键盘，设置inputId的
     * @return
     */
    public void setWebviewInputValue(String inputId, String jsfunname, String primaryCode, String keyvalue, String inputvalue){
        try {
            JSONObject json = new JSONObject();
            json.put("inputid", inputId);
            json.put("key", primaryCode);
            json.put("value", keyvalue);
            json.put("totalvalue", inputvalue);

            String strUrl = "javascript:"+jsfunname+"('"+json.toString()+"')";
            getCurrWebView().loadUrl(strUrl);
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
    }



    /**
     * 界面回退
     */
    @Override
    public void backPage()
    {
        //网页界面关闭系统键盘
        //window对象为null时崩溃
        if(getActivity().getCurrentFocus() != null){
            JiuyiKeyBoardManager.getIns().hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        super.backPage();
    }

    /**
     * 切换界面
     * @param bundle
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     */
    public void changePage(Bundle bundle, int nPageType, boolean bSavePage) {
        super.changePage(bundle, nPageType, bSavePage);
    }

    /**
     * 设置标题
     */
    public void setTitle(){
        //由切换皮肤引起的setTitle不可用
        if(!canSetTitle)
            return;

        if (mWebView != null)
        {
            String title = mWebView.getTitle();
            if(!Func.IsStringEmpty(title) ){
                mTitle = title;
            }
        }
        setWebTextSize();
        /**
         * 设置中间标题栏文字
         * 如果不知道执行通用的标题规则，请子类重写
         * @param title
         */
        setTitle(mTitle);
    }

    public void setWebTextSize() {

    }


    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
    }


    /**
     * 网页回退到当前界面的处理
     * @param IsBg
     */
    public void createBackReq(boolean IsBg) {

    }



    /**
     * 此方法只是处理功能号与Weblayoputbase相关的部分，具体的跳转还是在ActionPageType里面
     * @param nAction
     * @param jiuyiWebView
     * @param urlParam
     * @param urlParamMap
     * @return
     */
    public boolean OnActionPageType(int nAction, JiuyiWebView jiuyiWebView, String urlParam, Map<String, String> urlParamMap, boolean bTradeAction){
        m_nCurActionPageType = nAction;
        switch(nAction){
            case Pub.MENU_SYS_SetStartPage:
            {
                Bundle bundle = new Bundle();
                changePage(bundle,nAction,true);
                return true;
            }
            case Pub.Customer_main:
            {
                long[] customerID=new long[1];
                if(urlParamMap!=null){
                    for (Map.Entry<String, String> entry : urlParamMap.entrySet()) {
                        if(entry.getKey().equals("customreid")){
                            if(Func.getValueByUrl(urlParam,"customreid")!=null) {
                                customerID[0]=Long.parseLong(Func.getValueByUrl(urlParam,"customreid"));
                            }
                        }
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerID);
                changePage(bundle,nAction,true);
                return true;
            }
            case Pub.JY_MENU_OpenVideo:
                String url="";
                String filename="";
                if(urlParamMap!=null){
                    for (Map.Entry<String, String> entry : urlParamMap.entrySet()) {
                        if(entry.getKey().equals("url")){
                            if(Func.getValueByUrl(urlParam,"url")!=null) {
                                url=Func.getValueByUrl(urlParam,"url");
                                url= Constant.VideoUrl+url;
                            }
                        }
                        if(entry.getKey().equals("filename")){
                            if(Func.getValueByUrl(urlParam,"filename")!=null) {
                                filename=Func.getValueByUrl(urlParam,"filename");
                            }
                        }
                    }
                    if(!Func.IsStringEmpty(url)){
                        JzvdStd.startFullscreen(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JzvdStd.class, url,filename);
                    }
                return true;
                }
            case Pub.JY_MENU_OpenVoice:
                String voiceurl="";
                String extData="";
                if(urlParamMap!=null){
                    for (Map.Entry<String, String> entry : urlParamMap.entrySet()) {
                        if(entry.getKey().toUpperCase().equals("URL")){
                            if(Func.getValueByUrl(urlParam,"url")!=null) {
                                voiceurl=Func.getValueByUrl(urlParam,"url");
                                voiceurl= Constant.VideoUrl+voiceurl;
                            }
                        }
                        if(entry.getKey().toUpperCase().equals("EXTDATA")){
                            if(Func.getValueByUrl(urlParam,"extdata")!=null) {
                                extData=Func.getValueByUrl(urlParam,"extdata");
                            }
                        }
                    }
                    if(!Func.IsStringEmpty(voiceurl)){
                        Media media=new Media();
                        media.setFileTime(extData);
                        media.setUrl(voiceurl);
                        mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,media);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_RecordPlay,true);
                    }
                }

                return true;
        }
        return false;
    }


    @Override
    public JiuyiWebView getWebView() {
        return mWebView;
    }
}
