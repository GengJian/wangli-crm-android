package com.control.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.callback.AjaxWebClientUrlLisCallBack;
import com.control.callback.JiuyiIEditTextCallBack;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiWindowFlagParamShared;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardView;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.JiuyiEditText;
import com.control.widget.webview.JiuyiPopWndModifyWebTextSize;
import com.control.widget.webview.JiuyiWebView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.control.utils.Res.Dip2Pix;

/**
 * ****************************************************************
 * 文件名称:AjaxWebClientUrlLis.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * ****************************************************************
 */

public class AjaxWebClientUrlLis {
    private AjaxWebClientUrlLisCallBack mCallBack;
    private boolean m_bIsSavePage = false;//筛选使用

    public AjaxWebClientUrlLis(AjaxWebClientUrlLisCallBack ajaxWebClientUrlLisCallBack) {
        this.mCallBack = ajaxWebClientUrlLisCallBack;
    }

    public AjaxWebClientUrlLisCallBack getAjaxWebClientUrlLisCallBack() {
        return mCallBack;
    }

    /**
     * 对网页URL进行处理的类
     * @param o
     */
    public AjaxWebClientUrlLis(Object o) {

    }

    /**
     */
    public void ActionNotification() {
        if (getActivity() == null)
            return;
    }


    /**
     * 转向相应的功能
     * 例如：http://action:1234/?
     * @param c
     * @param jiuyiWebView
     * @param nAction
     * @param url
     * @return 是否处理成功
     */
    public boolean ActionPageType(JiuyiICanvasInterface c, final JiuyiWebView jiuyiWebView, int nAction, String url, boolean bTradeAction) {
        String originUrl = url;
        if (nAction <= 0) {
            String strUrlStart = "";
            if (!Func.IsStringEmpty(strUrlStart)) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                url = url.substring(strUrlStart.length(), url.length());//http://action:10050?urlfalse=/zllcajax/kh/kh_phototip.htm&&urltrue=/zllcajax/kh/kh_digitinstall.htm&&filename=videomsg

                int nWenHaoPos = url.indexOf("?");
                String strAction = "";

                if (nWenHaoPos > 0) {
                    strAction = url.substring(0, nWenHaoPos);
                    url = url.substring(nWenHaoPos + 1, url.length());
                } else {
                    strAction = url;
                }

                if (strAction.indexOf("/") > 0) {
                    strAction = strAction.substring(0, strAction.indexOf("/"));
                }
                nAction = Func.parseInt(strAction);
            } else {
                nAction = 0;
            }
        }
        Bundle mBundle = new Bundle();
        if (bTradeAction) {
            mBundle.putString(JiuyiBundleKey.PARAM_TRADEACTION, "1");
        }
        //功能号和软件的功能号对应
        nAction = JiuyiCastAjaxPage.getAjaxPage(nAction);
        if (nAction > 0) {
            if(nAction==Pub.JY_MENU_OpenReqFile){
                if (!Func.IsStringEmpty(url)) {
                    if(url.indexOf("=")>=0){
                        url=url.substring(url.indexOf("=")+1,url.length());
                        url=Func.URLDecoder(url);
                    }
                    String fileType="";
                    fileType=getValueByUrl(url, "fileType");
                    String titleName="";
                    titleName=getValueByUrl(url, "fileName");
                    if(!Func.IsStringEmpty(fileType)){
                        mBundle.putString(JiuyiBundleKey.PARAM_FILETYPE, fileType);
                    }
                    if(!Func.IsStringEmpty(titleName)){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, titleName);
                    }
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPPDF, url);
                    c.changePage(mBundle, Pub.JY_MENU_OpenReqFile, true);
                    return true;
                }
            }
            if(nAction==2017){
                if (!Func.IsStringEmpty(url)) {
                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.clear();
                    if (!Func.IsStringEmpty(url))
                        Func.getMapValue(url, null, reqMap, "&", true);
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Long.parseLong(reqMap.get("CUSTOMERID")));
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, "ADD");
                    c.changePage(mBundle, 2017, true);
                    return true;
                }
            }
            if(nAction==2021){
                if (!Func.IsStringEmpty(url)) {
                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.clear();
                    if (!Func.IsStringEmpty(url))
                        Func.getMapValue(url, null, reqMap, "&", true);
                    mBundle.putLong(JiuyiBundleKey.PARAM_NEEDPLANID,Long.parseLong(reqMap.get("ID")));
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, reqMap.get("VIEWTYPE").toUpperCase());
                    c.changePage(mBundle, 2021, true);
                    return true;
                }
            }
            if(nAction==Pub.Customer_InformationNew){
                if (!Func.IsStringEmpty(url)) {
                    url=Func.URLDecoder(url);
                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.clear();
                    Func.getMapValue(url, null, reqMap, "&", true);
                    mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,Long.parseLong(reqMap.get("ID")));
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, reqMap.get("VIEWTYPE").toUpperCase());
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,reqMap.get("TITLE"));
                    c.changePage(mBundle, Pub.Customer_InformationNew, true);
                    return true;
                }
            }
            if(nAction==Pub.JY_MENU_OpenPicture){
                if (!Func.IsStringEmpty(url)) {
                    if(url.indexOf("=")>=0){
                        url=url.substring(url.indexOf("=")+1,url.length());
                    }
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                    c.changePage(mBundle, Pub.JY_MENU_OpenPicture, true);
                    return true;
                }
            }
            if(nAction==Pub.JY_MENU_OpenPictureGroup){
                if (!Func.IsStringEmpty(url)) {
                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.clear();
                    if (!Func.IsStringEmpty(url))
                        Func.getMapValue(url, null, reqMap, "&", true);
                    String imageUrl="";
                    String[] urls;
                    imageUrl=reqMap.get("URL");
                    urls=Func.split(imageUrl,"|");
                    ArrayList<String> imageList=new ArrayList<>();
                    if(urls!=null && urls.length>0){
                        for(int i=0;i<urls.length;i++){
                            imageList.add(urls[i]);
                        }
                        mBundle.putInt("param_flag_enum",0);
                        mBundle.putInt("param_position",Integer.parseInt(reqMap.get("POSITION")));
                        mBundle.putStringArrayList("image_url_group", imageList);
                        c.changePage(mBundle, Pub.JY_MENU_OpenPictureGroup, true);
                    }
                    return true;
                }
            }
            if(nAction==10061){
                if (!Func.IsStringEmpty(url)) {

                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.clear();
                    if (!Func.IsStringEmpty(url))
                        Func.getMapValue(url, null, reqMap, "&&", true);
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, reqMap.get("URL"));//config里去掉开头的tztBundleKey.PARAM_HTTPServer
                    if (!Func.IsStringEmpty(reqMap.get("TITLE")))
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, reqMap.get("TITLE"));
                    if (!Func.IsStringEmpty(reqMap.get("HIDDENTITLE")))
                        mBundle.putString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, reqMap.get("HIDDENTITLE"));
                    c.changePage(mBundle, 10061, true);
                    return true;
                }
            }
            if(nAction==Pub.MENU_Trip){
                mBundle.putInt(JiuyiBundleKey.PARAM_MENU, Pub.MENU_Trip);
                c.changePage(mBundle, Pub.MENU_Trip, false);
                return true;
            }
            if (nAction != Pub.JY_MENU_OpenReqFile &&nAction != Pub.JY_MENU_OpenWebInfoContent && nAction != Pub.JY_MENU_ResetToolBarImage && nAction != Pub.JY_MENU_ToHotToolBarImage && nAction != Pub.JY_MENU_WebLogin)
                url = Func.URLDecoder(url);//防止Ajax参数乱码


            //
            Map<String, String> reqMap = new HashMap<String, String>();
            reqMap.clear();
            if (!Func.IsStringEmpty(url))
                Func.getMapValue(url, null, reqMap, "&&", true);

            //需要这个url作为打开webview的url
            if (bTradeAction) {
                if (!Func.IsStringEmpty(reqMap.get("URL")))
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, reqMap.get("URL"));
            } else {
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
            }
            if (!Func.IsStringEmpty(reqMap.get("DATETYPE")))
                mBundle.putString(JiuyiBundleKey.PARAM_DATE_TYPR, reqMap.get("DATETYPE"));
            if (!Func.IsStringEmpty(reqMap.get("INCLUDETODAY")))
                mBundle.putString(JiuyiBundleKey.PARAM_DATE_TYPR_INCLUDETODAY, reqMap.get("INCLUDETODAY"));
            if (!Func.IsStringEmpty(reqMap.get("TITLE")))
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, reqMap.get("TITLE"));
            if (!Func.IsStringEmpty(reqMap.get("TZTTITLETYPE"))) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLETYPE, reqMap.get("TZTTITLETYPE"));
            }

            //是否使用客户端title)
            //hiddentitle	string	控制客户端标题栏是否显示，0-不隐藏 1-隐藏  ，默认0，不隐藏	N
            if (!Func.IsStringEmpty(reqMap.get("HIDDENTITLE")))
                mBundle.putString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, reqMap.get("HIDDENTITLE"));



            //t\弹窗口的标题内容
            String content = "";
            String title = "";
            switch (nAction) {
                case 10050: {
                    if (jiuyiWebView.getWebViewAudioListener() != null) {
                        boolean bol = jiuyiWebView.getWebViewAudioListener().onOpenVideo(nAction, originUrl, reqMap);
                        if (bol) {
                            return bol;
                        }
                    }
                }
                break;
                case 10051: {
                    if (jiuyiWebView.getWebViewAudioListener() != null) {
                        boolean bol = jiuyiWebView.getWebViewAudioListener().onPhotograph(nAction, originUrl, reqMap);
                        if (bol) {
                            return bol;
                        }
                    }
                }
                break;
                case 10052: {
                    if (jiuyiWebView.getWebViewCERRequestListener() != null) {
                        jiuyiWebView.getWebViewCERRequestListener().onCreateP10(nAction, originUrl, reqMap);
                        return true;
                    }
                }
                break;
                case 10053: {
                    if (jiuyiWebView.getWebViewCERRequestListener() != null) {
                        jiuyiWebView.getWebViewCERRequestListener().onRequestCER(nAction, originUrl, reqMap);
                        return true;
                    }
                }
                break;
                case Pub.JY_MENU_Return://10002返回
                {
                    if (jiuyiWebView.canGoBack()) {
                        jiuyiWebView.goBack();
                    } else if (jiuyiWebView.getWebViewClientUrlDealListener() != null) {
                        jiuyiWebView.getWebViewClientUrlDealListener().OnReturenBack();
                    }
                    if (jiuyiWebView.getWebViewProgressListener() != null) {
                        jiuyiWebView.getWebViewProgressListener().StopPageProgress();
                    }
                }
                return true;
                case 1964: {
                    jiuyiWebView.closeCurrWebView();
                    //（是否需要执行3413，需要下一级url有action，没有的话不能执行back，因为要用到本页的webview打开地址）
                    //(key是大写)
                    String strTempUrl = reqMap.get("URL");
                    String strTempUrlStart="";
                    //判断是否还有堆栈,没有堆栈)
                    if (!jiuyiWebView.canGoBack() && !Func.IsStringEmpty(strTempUrlStart)) {
                        //没有堆栈,限制性一个后退 3413=返回，闭当前的WebView的组
                        c.backPage();//IsActionPageType("http://action:3413/", null);
                    }
                    if (!Func.IsStringEmpty(strTempUrl)/*!IsActionPageType(strTempUrl, null)*/) {
                        jiuyiWebView.loadUrl(strTempUrl);
                    }
                }
                return true;
                case Pub.MENU_QS_CutScreen: {
                    int cutX = Dip2Pix((int) Func.parseFloat(reqMap.get("CUTX")));
                    int cutY = Dip2Pix((int) Func.parseFloat(reqMap.get("CUTY")));
                    int picWidth = Dip2Pix((int) Func.parseFloat(reqMap.get("PICWIDTH")));
                    int picHeight = Dip2Pix((int) Func.parseFloat(reqMap.get("PICHEIGHT")));
                    if (jiuyiWebView != null && jiuyiWebView.getCurWebView() != null && cutX >= 0 && cutY >= 0 && picWidth > 0 && picHeight > 0) {
                        try {
                            Bitmap bmp = Func.shotPic(getActivity(), cutX, cutY, picWidth, picHeight);
                            ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
                            bmp.compress(Bitmap.CompressFormat.PNG, 100, output);//把bitmap100%高质量压缩 到 output对象里
                            bmp.recycle();//自由选择是否进行回收
                            byte[] result = output.toByteArray();//转换成功了
                            output.close();

                            String Base64encodeToString = Base64.encodeToString(result, 0);
                            Base64encodeToString = Base64encodeToString.replace("\r\n", "");
                            Base64encodeToString = Base64encodeToString.replace("\n", "");
                            Base64encodeToString = Base64encodeToString.replace(" ", "");

                            jiuyiWebView.getCurWebView().loadUrl("javascript:cutVideoPic('" + Base64encodeToString + "');");
                        } catch (Exception e) {
                            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                            c.startDialog(DialogID.DialogDoNothing, "", "截图异常\r\n" + e.getMessage(), JiuyiDialogBase.Dialog_Type_Cancle, null);
                        }
                    } else {
                        c.startDialog(DialogID.DialogDoNothing, "", "参数错误：url=" + url, JiuyiDialogBase.Dialog_Type_Cancle, null);
                    }
                    return true;
                }
                case Pub.JY_MENU_CloseAndReturn://title不传客户端默认系统提示；内容不传，则不弹出提示对话框，直接关闭；
                {
                    content = getValueByUrl(url, "content");//urlParams.get("content");
                    title = getValueByUrl(url, "title");//urlParams.get("title");
                    if (Func.IsStringEmpty(content)) {
                        c.dealDialogAction(Pub.JY_MENU_CloseAndReturn, KeyEvent.KEYCODE_ENTER, "", null);
                    } else {
                        c.startDialog(Pub.JY_MENU_CloseAndReturn, title, content, JiuyiDialogBase.Dialog_Type_YesNo, null);
                    }
                    return true;
                }
                case Pub.JY_MENU_UIActivity://调用客户端进度条显示;1-显示进度条 其他－关闭;
                    if ("1".equals(getValueByUrl(url, "show"))) {
                        c.showProcessBar(0);
                    } else {
                        c.showProcessBar(100);
                    }
                    return true;
                case Pub.JY_MENU_SendMsg://调用客户端发送短信界面
                    //url会传过来短信内容
                    url = url.toLowerCase();
                    String smscode = getValueByUrl(url, "mobilecode");
                    content = getValueByUrl(url, "content");
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:" + smscode));
                    intent.putExtra("sms_body", content);
                    getActivity().startActivity(intent);//sms
                    return true;
                case Pub.JY_MENU_ClearWebPageCache:
                    int clearall = Func.parseInt(reqMap.get("CLEARALL"));
                    CacheManager.cleanAjaxFiles(getActivity());
                    if (jiuyiWebView != null)
                        jiuyiWebView.loadUrl("javascript:" + reqMap.get("JSFUNCNAME"));
                    return true;
                case Pub.JY_MENU_WebOpenLocaleyboard://10081网页调用系统键盘
                    if (mCallBack==null || mCallBack.getRelativeLayout().getKeyBoardView() == null) {
                        return true;
                    } else {
                        jiuyiWebView.getKeyboardStruct().setHideKeyBoardState(false);
                    }
                    String inputId = reqMap.get("INPUTID");
                    String jsfunname = reqMap.get("JSFUCNAME");
                    String defaultvalue = reqMap.get("DEFAULTVALUE");
                    int inputback = Func.parseInt(reqMap.get("KEYBOARDTYPE"));
                    JiuyiEditText ins = new JiuyiEditText(getActivity(), new JiuyiIEditTextCallBack() {
                        @Override
                        public JiuyiKeyBoardView getKeyBoardView() {
                            return getRelativeLayout() != null ? getRelativeLayout().getKeyBoardView() : null;
                        }

                        @Override
                        public JiuyiRelativeLayout getRelativeLayout() {
                            return mCallBack != null ? mCallBack.getRelativeLayout() : null;
                        }

                        @Override
                        public Activity getActivity() {
                            return mCallBack != null ? mCallBack.getActivity() : null;
                        }

                        @Override
                        public JiuyiWebView getWebView() {
                            return jiuyiWebView;
                        }

                        @Override
                        public void setValueByKeyBoard(JiuyiEditText edit, int keycode) {
                        }

                        @Override
                        public void keyBoardShowCallBack(int height) {

                        }

                        @Override
                        public void keyBoardHideCallBack(int height) {

                        }
                    });
                    if (!Func.IsStringEmpty(defaultvalue))
                        ins.setText(defaultvalue);
                    ins.toShowWebViewKeyboard(inputback, inputId, jsfunname);
                    return true;



                case Pub.JY_MENU_OpenReqFile://下载文件并打开文件
                {
                    if (!Func.IsStringEmpty(reqMap.get("URL"))  ) {
                        String fileType="",fileName="";
                        fileType=getValueByUrl(reqMap.get("URL"), "fileType");
                        if(!Func.IsStringEmpty(fileType)){
                            mBundle.putString(JiuyiBundleKey.PARAM_FILETYPE, fileType);
                        }
                        fileName=getValueByUrl(reqMap.get("URL"), "fileName");
                        if(!Func.IsStringEmpty(fileName)){
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, fileName);
                        }
                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPPDF, url);
                        String name = getValueByUrl(reqMap.get("URL"), "name");
                        c.changePage(mBundle, Pub.JY_MENU_OpenReqFile, false);
                    }

                    return true;
                }
                case Pub.JY_MENU_OpenPicture://下载文件并打开文件
                {
                    if (!Func.IsStringEmpty(reqMap.get("URL"))  ) {
                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPPDF, url);
                        c.changePage(mBundle, Pub.JY_MENU_OpenPicture, true);                    }

                    return true;
                }
                case Pub.JY_MENU_GetGPSLocation: {
                    //（添加兼容逻辑：现在页面传参后面是url=）
                    String newurl = reqMap.get("URL");
                    if (!Func.IsStringEmpty(newurl))
                        url = newurl;
                    else {
                        if (url.toLowerCase().startsWith("url="))
                            url = url.substring(4, url.length());
                    }
                    url = url.replace("($tztgpsx)", Rc.cfg.getGPSX() + "");
                    url = url.replace("($tztgpsy)", Rc.cfg.getGPSY() + "");
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                }
                break;
                case Pub.ModifyWebTextSizePopWnd: {
                    View v = new JiuyiPopWndModifyWebTextSize(jiuyiWebView.getContext(), jiuyiWebView, Rc.getIns().getTitleHeight());
                    PopupWindow m_pPopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                    // 防止没有地板工具栏
                    int yPos = Rc.getIns().getTitleHeight() + Rc.getIns().getTopStatusBarHeight(null);
                    m_pPopupWindow.showAtLocation(v, Gravity.TOP, 0, yPos);
                    ((JiuyiPopWndModifyWebTextSize) v).SetPopWnd(m_pPopupWindow);
                }
                return true;

                case Pub.ResetLogin://
//                    Rc.getIns().ResetLogin(getActivity());
//                    c.changePage(mBundle, Pub.HQ_Login, false);
                    return true;




                case Pub.JY_MENU_ClearPhoneState: {
//                    NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//                    nm.cancelAll();
//                    Rc.getIns().m_sNoReadMsgCount = 0;
//                    Rc.getIns().SaveConfig();
//                    ActionNotification();
                    return true;
                }
                case Pub.MENU_Normal: {
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, reqMap.get("URL"));
                }
                break;

                default: {

                }
                break;
            }
            //需要界面跳转的处理
            if (c != null) {
                if (true) {
                    switch (nAction) {
                        case Pub.Doback:
                        case Pub.JY_MENU_Return:
                            c.backPage();
                            break;
                        case Pub.MENU_SYS_UpdataVersion://升级
                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, "");
                            url = getValueByUrl(url, "url");
                            break;
                        case Pub.MENU_SYS_KeepScreenOn://保持屏幕常亮
                            String bScreenOnType = reqMap.get("SCREENONTYPE");//1常亮 0不常亮
                            if(TextUtils.isEmpty(bScreenOnType))
                                return true;
                            //修改当前界面锁屏状态
                            if(bScreenOnType.equals("1")) {
                                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                            }else {
                                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                            }
                            new JiuyiWindowFlagParamShared().onSaveData(bScreenOnType.equals("1") ? 1 : 0);// = bScreenOnType.equals("1") ? 1 : 0;
                            break;

                        case Pub.JY_MENU_OpenOtherSoft:
                        case Pub.JY_MENU_StartOpen:
                        case Pub.JY_MENU_StartQRCode:
                        case 10320:
                        case Pub.JY_MENU_OpenWebInfoContent: {
                            if (!Func.IsStringEmpty(reqMap.get("URL"))  ) {
                                ActionPageType(c, jiuyiWebView, 0, reqMap.get("URL"), false);
                            } else {
                                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                c.changePage(mBundle, nAction, true);
                            }
                        }
                        break;

                        default:
                            onActionChangePage(mBundle, bTradeAction, nAction, c, originUrl);
                            break;
                    }
                }
            }

            return true;
        }
        return false;
    }

    /**
     * ActionPageType方法里页面跳转
     * void
     */
    public void onActionChangePage(Bundle mBundle, boolean bTradeAction, int nAction, JiuyiICanvasInterface c, String originUrl) {
        int nPageType = mCallBack!=null?mCallBack.getRelativeLayout().getPageType():0;
        if (nPageType == Pub.JY_MENU_OpenWebInfoContent && m_bIsSavePage) {
            if (Rc.getIns().getBaseCallTopCallBack().getCurrentActivity() != null){
                Rc.getIns().getBaseCallTopCallBack().popActivity(getActivity(), false);

            }
        }

        boolean succ = bTradeAction;

        //（根据页面传参判断是否保存）
        if (m_bIsSavePage) {
            succ = false;
        }
        m_bIsSavePage = false;
        c.changePage(mBundle, nAction, succ);
    }





    /**
     * 通过url获取param的值
     * @param url
     * @param param
     * @return
     */
    public String getValueByUrl(String url, String param) {
        String value = "";
        if (!Func.IsStringEmpty(url) && url.indexOf(param + "=") >= 0) {
            url = url.substring(url.indexOf(param + "=") + (param + "=").length(), url.length());
            int splitpos = url.indexOf("&");
            if (splitpos < 0)
                splitpos = url.length();
            value = url.substring(0, splitpos);
        }
        return value;
    }




    /**
     * 获取当前的Activity
     * @return
     */
    private Activity getActivity(){
        if(mCallBack != null){
            return mCallBack.getActivity();
        }else{
            return Rc.getIns().getBaseCallTopCallBack().getCurrentActivity();
        }
    }
    /**
     *
     * 客服电话
     *
     * @param url  电话号码或url
     * @return
     */
    @SuppressLint("MissingPermission")
    public boolean ActionCall(String url) {
        String urlstartsWith = "http://tel:";//"http://tel:";
        String urlstartsWith2 = "tel:";
        // zhengss 网页上自动的号码是不带http://的
        if (url.startsWith(urlstartsWith2))
            url = "http://" + url;

        String phone = "\\d{2,4}-\\d{7,8}";

        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(url);
        boolean bPhoneNum = (m.matches() || Func.IsNumber(url));
        if (bPhoneNum || url.startsWith(urlstartsWith)) {
            String tmpmb = "";
            if (bPhoneNum) {
                tmpmb = url;
            } else {
                int sub = url.endsWith("/") ? 1 : 0;
                tmpmb = url.substring(urlstartsWith.length(), url.length() - sub);
            }
            final String mobile = tmpmb;
            m = p.matcher(mobile);


            String tempString = "确定拨打电话:";

            // 检测权限
            new JiuyiHiPermissionUtil(getActivity()).checkPermission(Manifest.permission.CALL_PHONE, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                @Override
                public void onGuarantee(String permission, int position) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                    //（解决拨打电话 app闪退问题）
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }
            });
            return true;
        }
        return false;
    }
}
