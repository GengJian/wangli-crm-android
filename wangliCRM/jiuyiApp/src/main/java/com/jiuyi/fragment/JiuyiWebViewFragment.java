package com.jiuyi.fragment;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.shared.JiuyiWebTextSizeShared;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.widget.webview.JiuyiWebView;
import com.control.widget.webview.JiuyiWebViewDealUrlData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanglicrm.android.R;

import java.util.HashMap;
import java.util.Map;


/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewFragment.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/26 14:43
 * 文件描述:10061网页的Fragment
 * 注意事项:标题栏的问题
 *          1、通过jiuyiWebview10061Activity打开的网页，不管是否是10061，都是使用Fragment里的标题栏
 *          2、如果不是10061且非强制使用的，不使用Fragment里的标题栏，在viewppage和时间选择的不使用
 *          3、使用jiuyiBundleKey.PARAM_SHOWCLIENTTITLE进行控制

 * ****************************************************************
 */

public class JiuyiWebViewFragment extends JiuyiWebviewFragmentBase {
    /** 标题栏 */
    private RelativeLayout mTitleBar;
    /** 搜索按钮 */
    public com.control.widget.JiuyiButton mRightView;//
    /** 返回按钮 */
    public com.control.widget.JiuyiButton mLeftView;//
    /** 标题栏 中间标题TextView */
    public TextView mCenterView;//

    /** 标题栏 左侧图片*/
    protected ImageView m_vTitleLeftImageView;
    /** 标题栏 右侧图片*/
    protected ImageView m_vTitleRightImageView;

    /** 标题栏 左侧的按钮类型*/
    protected String m_sFirstType = "0";
    /**
     * 标题栏 左侧的按钮的功能号或者是URL或者是jsfuncname
     */
    protected String m_sFirstAction = "";
    /**
     * 标题栏 左侧的按钮的文字，firsttype=99时有效，显示对应文本或firsttype=98时有效，显示对应图片
     */
    protected String m_sfirsttext = "";

    /** 标题栏 右侧的按钮类型*/
    protected String m_sSecondType = "0";
    /**
     * 标题栏 右侧的按钮的功能号或者是URL或者是jsfuncname
     */
    protected String m_sSecondAction = "";
    /**
     * 标题栏 右侧的按钮的文字，secondtype或type=99时有效，显示对应文本或secondtype或type=98时有效，显示对应图片
     */
    protected String m_sSecondtext = "";
    /**
     * 右侧复选框
     */
    protected CheckBox m_pCheckBox;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mPageType <= 0){
            mPageType = Pub.JY_MENU_OpenWebInfoContent;
        }
        if(mBundle != null && mBundle.containsKey(JiuyiBundleKey.PARAM_SKINTYPE)) {
            int defSkinType = mBundle.getInt(JiuyiBundleKey.PARAM_SKINTYPE);
            if (defSkinType >= 0)
                mSkinType = defSkinType;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(getActivity(), "jiuyi_fragment_webview10061_layout"), null);
            //保证mBundle不为null，因为下面有使用到的地方
            if(mBundle == null)
                mBundle = new Bundle();

            setOnRefreshListener();

            onInitViewParam();

            onInit();

            onInitLoadUrl();
        }else{
            checkRootViewParent();
        }

        return mRootView;
    }

    /**
     * 切换皮肤
     */
    @Override
    public void changeSkinType(){
        super.changeSkinType();
        if(mTitleBar != null){
            mTitleBar.setBackgroundColor(Res.getColor(null, "title_background_color"));
        }
    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiWebViewFragment newInstance(int nPageType, String url) {
        JiuyiWebViewFragment f = new JiuyiWebViewFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        args.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
        f.setArguments(args);
        return f;
    }
    public static JiuyiWebViewFragment newInstance(int nPageType, int forceSkinType) {
        JiuyiWebViewFragment f = new JiuyiWebViewFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        args.putInt(JiuyiBundleKey.PARAM_SKINTYPE, forceSkinType);
        f.setArguments(args);
        return f;
    }
    /**
     * 初始化UI
     */
    public void onInit(){
        mTitleBar = (RelativeLayout)mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_layout"));
        mWebView = (JiuyiWebView)mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_webview"));
//        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
////                onInitLoadUrl();
//                if(mWebView != null && mWebView.getUrl()!=null) {
//                    loadUrl(mWebView.getUrl());
//                }
//                refreshlayout.finishRefresh(0);
//                refreshlayout.setLoadmoreFinished(false);
//            }
//        });
//        refreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //如果不是10061且非强制使用的，不使用Fragment里的标题栏，在viewppage和时间选择的不使用
        if(!"0".equals(mBundle.getString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, "-1"))){
            mTitleBar.setVisibility(View.GONE);
        }
        setWebTextSize();

        mLeftView = (com.control.widget.JiuyiButton)mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_navbarbackbg"));
        mRightView = (com.control.widget.JiuyiButton)mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_more"));
        mCenterView = (TextView)mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_textview"));

        m_vTitleLeftImageView = (ImageView) mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_navbarbackbg_imageview"));
        m_vTitleRightImageView = (ImageView) mRootView.findViewById(Res.getViewID(getActivity(), "jiuyi_titlebar_more_imageview"));
        m_pCheckBox = (CheckBox) mRootView.findViewById(Res.getViewID(getActivity(), "tzt_titlebar_checkbox"));

        if(mLeftView != null){
            mLeftView.setOnClickListener(mViewClkListener);
        }

        if(mRightView != null){
            mRightView.setOnClickListener(mViewClkListener);
        }

        if(m_vTitleLeftImageView != null){
            m_vTitleLeftImageView.setOnClickListener(mViewClkListener);
        }

        if(m_vTitleRightImageView != null){
            m_vTitleRightImageView.setOnClickListener(mViewClkListener);
        }

        if(m_pCheckBox != null){
            m_pCheckBox.setOnCheckedChangeListener(m_pCheckedChangeListener);
        }

        onSetWebViewCallBack();
        onSetTitleLeftView();
        onSetTitleRightView();

        // 设置webview背景
        mWebView.m_nSkinType = mSkinType;
    }

    /**
     * 初始化加载url
     */
    public void onInitLoadUrl(){
        if(mWebUrl != null) {
            loadUrl(mWebUrl);
        }
    }
    /**
     * 解析左边按钮的参数
     */
    private void onInitViewParam()
    {
        Map<String, String> ayParamMap = new HashMap<String, String>();
        ayParamMap.clear();
        switch (mPageType)
        {
            case Pub.JY_MENU_OpenWebInfoContent:
            {
                String strParam = mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer);
                if (Func.IsStringEmpty(strParam)){
                    break;
                }
                ayParamMap = JiuyiWebViewDealUrlData.onDealParamsWithAction(mPageType,strParam);
                if (ayParamMap.size() > 0)
                {
                    Map<String, String> tempMap = new HashMap<String, String>();
                    tempMap.clear();
                    for (Map.Entry<String, String> entry: ayParamMap.entrySet())
                    {
                        tempMap.put(entry.getKey().toUpperCase(), /*Pub.URLDecoder(entry.getValue())*/Uri.decode(entry.getValue()));
                    }
                    ayParamMap.clear();
                    ayParamMap = tempMap;
                }


                String strType = ayParamMap.get("FULLSCREEN");
                int nFullScreenType = 0;
                if (!Func.IsStringEmpty(strType))
                {
                    nFullScreenType = Func.parseInt(strType);
                    if (nFullScreenType < 0)
                    {
                        nFullScreenType = 1;
                    }
                }

                //10083主题切换
                String strSkinType = ayParamMap.get("SKINTYPE");
                if (!Func.IsStringEmpty(strSkinType))
                {
                    mSkinType = Func.parseInt(strSkinType) % 2;
                    if (mSkinType < 0){
                        mSkinType = 0;
                    }
                }

                strType = ayParamMap.get("URL");
                if (!Func.IsStringEmpty(strType))
                {
                    if(!strType.startsWith("/")){
                        strType = "/" + strType;
                    }
                    mWebUrl = strType;
                }

                //（每次获取页面的值，都需要重新获取）
                mTitleType = ayParamMap.get("tzttitletype".toUpperCase());

                strType = ayParamMap.get("SECONDURL");
                if (!Func.IsStringEmpty(strType))
                {
                    mObjectForTitleType = strType;
                }

                mTitle = ayParamMap.get("TITLE");
                mWebUrl= strParam;
//                mWebUrl="http://192.168.0.32:8080/jiuyi/";
//                mWebUrl="http://192.168.0.32:8080/text/javascript.html";
//                mWebUrl="file:///android_asset/javascript.html";
//                mWebUrl="https//www.baidu.com/";
//                mWebUrl="http://192.168.1.200:9095/#/contract-details";
//                  mWebUrl="https://www.tianyancha.com/lawsuit/7e33b29dabd311e8a8b47cd30ae00894";

            }
                break;
            default: {
                mWebUrl = mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer);
                if(Func.IsStringEmpty(mWebUrl) || mPageType != Pub.HttpServer){
                    switch (mPageType) {
                        case Pub.HttpServer:
                            HttpServerUrl("tztindexurl");
                            break;
                        default:
                            HttpServerUrl("");
                            break;
                    }
                }

                //获取当前的标题上TitleType
                if(Func.IsStringEmpty(mTitleType)){
                    Map<String, String> ayParamMap2 = new HashMap<String, String>();
                    ayParamMap2.clear();

                    ayParamMap2 = JiuyiWebViewDealUrlData.onDealParamsWithAction(mPageType, mWebUrl);
                    if (ayParamMap2.size() > 0)
                    {
                        Map<String, String> tempMap = new HashMap<String, String>();
                        tempMap.clear();
                        for (Map.Entry<String, String> entry: ayParamMap2.entrySet())
                        {
                            tempMap.put(entry.getKey().toUpperCase(), /*Pub.URLDecoder(entry.getValue())*/Uri.decode(entry.getValue()));
                        }
                        ayParamMap2.clear();
                        ayParamMap2 = tempMap;
                    }
                    mTitleType = ayParamMap2.get("tzttitletype".toUpperCase());
                }
            }
                break;
        }


        onDealTitleLeftViewParam(ayParamMap);
        onDealTitleRightViewParam(ayParamMap);


        // 如果url为空则说明参数有错误，直接返回，否则会出现标题栏左右两个搜索按钮情况
        if(Func.IsStringEmpty(mWebUrl)){
            backPage();
        }
    }
    /**
     * 根据配置兼容获取url，优先使用intent传过来的url
     * @param url url地址或者url的配置的key
     * @return url地址
     */
    private String HttpServerUrl(String url) {
        JiuyiLog.e("initViewPager", "HttpServerUrl");
        mWebUrl = mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer);
        if (Func.IsStringEmpty(mWebUrl)) {
            mWebUrl = Res.getString(null, url);
        }
        return mWebUrl;
    }
    /**
     * 网页执行返回
     */
    public void onReturnBack()
    {
        super.backPage();
    }

    @Override
    public void createReq(boolean IsBg)
    {
        super.createReq(IsBg);
        if(IsBg){//刷新,执行js
            if(mWebView!=null)
                mWebView.refreshJsTimer();
        }
    }

    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if (mWebView != null)
        {
            onSetTitleLeftView();
            onSetTitleRightView();
        }
    }

    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
        if (nKeyCode == KeyEvent.KEYCODE_ENTER)
        {
            if(nAction == Pub.JY_MENU_CloseAndReturn){
                super.backPage();
            }else if(nAction == Pub.JY_MENU_Return || nAction == Pub.Doback){
                backPage();
            }else{
                super.dealDialogAction(nAction, nKeyCode, url, pDialog);
            }
        }else{
            super.dealDialogAction(nAction, nKeyCode, url, pDialog);
        }
    }



    private CompoundButton.OnCheckedChangeListener m_pCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Object objTag = buttonView.getTag();
            String strParam = (String) objTag;
            if (!Func.IsStringEmpty(strParam))
            {
                Map<String, String> ayParam = new HashMap<String, String>();
                ayParam.clear();
                Func.getMapValue(strParam, null, ayParam, "&&", true);

                if (strParam.startsWith("javascript:"))
                {
                    mWebView.loadUrl(m_sSecondAction);
                }
                else if (mCallBack.getAjaxWebClientUrlLis().ActionPageType(JiuyiWebViewFragment.this, mWebView, 0, strParam, false))
                {

                }
                else if (ayParam.get("ANDROIDPAGETYPE") != null)
                {
                    mWebView.loadUrl(m_sSecondAction);
                }
            }

        }
    };


    /**
     * 处理左边按钮的参数
     * @param paramMap
     */
    public void onDealTitleLeftViewParam(Map<String, String> paramMap)
    {
        String strType = paramMap.get("FIRSTTYPE");
        if (strType == null || strType.length() <= 0)
        {
            strType = "10";
        }

        m_sFirstType = strType;

        String strParam = paramMap.get("FIRSTURL");
        if (strParam == null || strParam.length() <= 0)
        {
            strParam = paramMap.get("FIRSTJSFUNCNAME");
            if (strParam == null || strParam.length() <= 0)
            {
                strParam = paramMap.get("FIRSTSYSFUNCTION");
                if (strParam != null && strParam.length() > 0 )
                {
                    strParam = "http://action:" + strParam;
                }
            }
            else
            {
                strParam = "javascript:" + strParam;
            }
        }
        m_sFirstAction = strParam;

        m_sfirsttext = paramMap.get("firsttext".toUpperCase());
    }
    /**
     * 处理右边按钮的参数
     * @param paramMap
     */
    public void onDealTitleRightViewParam(Map<String, String> paramMap)
    {
        String strType = paramMap.get("SECONDTYPE");
        if (strType == null || strType.length() <= 0)
        {
            strType = paramMap.get("TYPE");
            if (strType == null || strType.length() <= 0)
            {
                strType = "0";
            }
        }

        m_sSecondType = strType;

        String strParam = paramMap.get("SECONDURL");
        if (strParam == null || strParam.length() <= 0)
        {
            strParam = paramMap.get("SECONDJSFUNCNAME");
            if (strParam == null || strParam.length() <= 0)
            {
                strParam = paramMap.get("SECONDSYSFUNCTION");
            }
            else
            {
                strParam = "javascript:" + strParam;
            }
        }
        m_sSecondAction = strParam;

        m_sSecondtext = paramMap.get("secondtext".toUpperCase());
        if(!Func.IsStringEmpty(m_sSecondtext) && m_sSecondtext.toUpperCase().indexOf(".PNG") > 0){
            m_sSecondtext = m_sSecondtext.substring(0, m_sSecondtext.toUpperCase().indexOf(".PNG"));
        }
    }

    /**
     * 根据nTitleType设置左侧按钮的属性、背景等参数
     */
    public void onSetTitleLeftView()
    {
        int nResID = 0;
        int nChangePageType = 0;
        String strText = "";
        int nTitleType = Func.parseInt(m_sFirstType);
        switch (nTitleType)
        {
            case 0:
                nResID = Res.getDrawabelID(getContext(), "tzttitlewebquerystock");
                nChangePageType = 1107;
                break;
            case 1://修改字体
                nResID = Res.getDrawabelID(getContext(), "tzttitlewebtextsize");
                nChangePageType = Pub.ModifyWebTextSizePopWnd;
                break;
            case 2://
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 10061;
                strText = "";
                break;
            case 3://
                nChangePageType = 10061;
                strText = "编辑";
                //nResID = Res.getDrawabelID(getContext(), "tztnavbarmodify");
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 4:
                nChangePageType = 10061;
                strText = "";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 5://
                nChangePageType = 10320;
                strText = "";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 6://筛选 （文字）
                nChangePageType = 10061;
                strText = "筛选";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 9://右侧没有按钮
                nResID = Res.getDrawabelID(getContext(), "tzt_navbargonebg");
                nChangePageType = 0;
                break;
            case 10:
                nResID = Res.getDrawabelID(getContext(), "jiuyi_navbarbackbg");
                nChangePageType = 0;
                strText = "";
                break;
            case 11:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = "退出";
                break;
            case 15:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 10061;
                strText = "";
                break;
            case 16:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = "";
                break;
            case 98://自定义(显示对应图片)
                nResID = Res.getDrawabelID(getContext(), m_sfirsttext);
                nChangePageType = 0;
                strText = "";
                break;
            case 99://自定义(设置为默认底图，显示对应文本)
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = m_sfirsttext;
                break;
        }


        if (!Func.IsStringEmpty(strText)/*strText.length() > 0*/)
        {
            m_vTitleLeftImageView.setVisibility(View.GONE);
            mLeftView.setVisibility(View.VISIBLE);

            mLeftView.setBackgroundResource(nResID);


            //(字数显示多了 会显示不全，需要特殊磁力)
            if(strText.length()>=3)
            {
                mRightView.setTextSize(Rc.getIns().getCanvasMainFont()/2);
            }
            else
            {
                mRightView.setTextSize(Rc.getIns().getCanvasMainFont()-2);
            }
            mLeftView.setTextColor(Res.getColor(null,"tzt_white"));
            mLeftView.setText(strText);
        }
        else
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mLeftView.getLayoutParams();
            mLeftView.setText("");
            m_vTitleLeftImageView.setImageResource(nResID);
            m_vTitleLeftImageView.setVisibility(View.VISIBLE);
            mLeftView.setVisibility(View.GONE);
        }

        mLeftView.setTag(m_sFirstAction);
        m_vTitleLeftImageView.setTag(m_sFirstAction);
    }
    /**
     * 根据nTitleType设置右侧按钮的属性、背景等参数
     */
    public void onSetTitleRightView()
    {
        int nResID = Res.getDrawabelID(getContext(), "tzt_navbargonebg");
        int nChangePageType = 0;
        String strText = "";
        int nTitleType = Func.parseInt(m_sSecondType);
        switch (nTitleType)
        {
            case 0://查询
                nResID = Res.getDrawabelID(getContext(), "tzttitlewebquerystock");
                nChangePageType = 1107;
                break;
            case 1://修改字体
                nResID = Res.getDrawabelID(getContext(), "tzttitlewebtextsize");
                nChangePageType = Pub.ModifyWebTextSizePopWnd;
                break;
            case 2://
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 10061;
                strText = "";
                break;
            case 3://修改
                nChangePageType = 10061;
                strText = "";
                //nResID = Res.getDrawabelID(getContext(), "tztnavbarmodify");
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 4://
                nChangePageType = 10061;
                strText = "";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 5://
                nChangePageType = 10320;
                strText = "";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 6://筛选
                nChangePageType = 10061;
                strText = "筛选";
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                break;
            case 9://右侧没有按钮
                nResID = Res.getDrawabelID(getContext(), "tzt_navbargonebg");
                nChangePageType = 0;
                break;
            case 10:
                nResID = Res.getDrawabelID(getContext(), "jiuyi_navbarbackbg");
                nChangePageType = 0;
                strText = "";
                break;
            case 11:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = "退出";
                break;
            case 15:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 10061;
                strText = "查看";
                break;
            case 16:
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = "";
                break;
            case 98://自定义(显示对应图片)
                nResID = Res.getDrawabelID(getContext(), m_sSecondtext);
                nChangePageType = 0;
                strText = "";
                break;
            case 99://自定义(设置为默认底图，显示对应文本)
                nResID = Res.getDrawabelID(getContext(), "tzt_titlerightbtnbg");
                nChangePageType = 0;
                strText = m_sSecondtext;
                break;
        }





        if(nTitleType==17){
            if(m_pCheckBox==null){
                return;
            }
            m_vTitleRightImageView.setVisibility(View.GONE);
            mRightView.setVisibility(View.GONE);
            m_pCheckBox.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(m_sSecondtext)){
                m_pCheckBox.setText(m_sSecondtext);
            }
            m_pCheckBox.setTag(m_sSecondAction);
            return;
        }

        if (!Func.IsStringEmpty(strText)/*strText.length() > 0*/)
        {
            m_vTitleRightImageView.setVisibility(View.GONE);
            mRightView.setVisibility(View.VISIBLE);
            mRightView.setBackgroundResource(nResID);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRightView.getLayoutParams();
            //(字数显示多了 会显示不全，需要特殊处理)
            if(strText.length()>=3)
            {
                mRightView.setTextSize(Rc.getIns().getCanvasMainFont()/2);
            }
            else
            {
                mRightView.setTextSize(Rc.getIns().getCanvasMainFont()-2);
            }
            mRightView.setTextColor(Res.getColor(null,"tzt_white"));
            mRightView.setText(strText);
        }
        else
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRightView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            m_vTitleRightImageView.setImageResource(nResID);
            m_vTitleRightImageView.setVisibility(View.INVISIBLE);

            mRightView.setVisibility(View.GONE);
        }


        mRightView.setTag(m_sSecondAction);
        m_vTitleRightImageView.setTag(m_sSecondAction);

        //（有些页面没有传参文本，是null,会提示空指针）
        if(Func.IsStringEmpty(strText)){
            mRightView.setTextSize(Rc.getIns().getCanvasMainFont());
        }else{
            mRightView.setTextSize(Rc.getIns().getCanvasMainFont() - strText.length());
        }
    }
    /**
     * 设置标题内容
     * 并可以设置字号大小
     */
    public void setWebTextSize()
    {
        if ((Func.parseInt(m_sSecondType) == 1 || Func.parseInt(m_sFirstType) == 1) && mPageType == Pub.JY_MENU_OpenWebInfoContent)
        {
            int nTextSize = JiuyiWebTextSizeShared.getIns().getTextSize();
            if (nTextSize < 0)
            {
                nTextSize = 100;
            }

            WebSettings.TextSize oTextSize = WebSettings.TextSize.NORMAL;
            switch (nTextSize)
            {
                case 75:
                    oTextSize = WebSettings.TextSize.SMALLER;
                    break;
                case 100:
                    oTextSize = WebSettings.TextSize.NORMAL;
                    break;
                case 125:
                    oTextSize = WebSettings.TextSize.LARGER;
                    break;
            }
            if(mWebView != null)
                mWebView.setWebTextSize(oTextSize);
        }
    }

    /**
     * 设置中间标题栏文字
     * 如果不知道执行通用的标题规则，请子类重写
     * @param strText
     */
    public void setTitle(String strText, String titleType) {
        if (strText == null || strText.length() <= 0)
        {
            return;
        }
        if(strText.startsWith("127.0.0.1") || strText.startsWith("http://127.0.0.1"))
        {
            return;
        }
        //根据页面传参判断底部是否显示文本
        String bottomTitle="";
        if(!Func.IsStringEmpty(bottomTitle)){
            mTitle = mTitle + "\r\n" + bottomTitle;
        }
        super.setTitle(mTitle, mTitleType);
    }

    /**
     * 获取当前的中间标题控件
     * @return
     */
    public TextView getTitleCenterView(){
        return mCenterView;
    }

    public View.OnClickListener mViewClkListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (v == null)
            {
                return;
            }

            onChangeViewWithAction(v);
        }
    };
    /**
     * 标题上按钮的事件处理
     * @param v
     */
    public void onChangeViewWithAction(View v)
    {
        if(mCallBack == null)
            return;

        String strType = "10";
        int nLeftRight = 0;
        if (v == mLeftView || v == m_vTitleLeftImageView)
        {
            strType = m_sFirstType;
            nLeftRight = 1;
        }
        else if (v == mRightView || v == m_vTitleRightImageView)
        {
            strType = m_sSecondType;
            nLeftRight = 2;
        }

        if (strType.equals("10"))
        {
            backPage();
        }
        else if (strType.equals("11"))
        {
            onReturnBack();
        }
        else if (strType.equals("16"))
        {
            mWebView.loadUrl("javascript:tztDelAllMsg();");
        }
        else
        {
            Object objTag = v.getTag();
            String strParam = (String) objTag;
            if (!Func.IsStringEmpty(strParam))
            {
                Map<String, String> ayParam = new HashMap<String, String>();
                ayParam.clear();

                Func.getMapValue(strParam, null, ayParam, "&&", true);

                if (strParam.startsWith("javascript:"))
                {
                    if(nLeftRight == 1){
                        loadUrl(m_sFirstAction);
                    }else if(nLeftRight == 2){
                        loadUrl(m_sSecondAction);
                    }
                }
                else if (mCallBack.getAjaxWebClientUrlLis().ActionPageType(this, mWebView, 0, strParam, false))
                {

                }
                else if (ayParam.get("ANDROIDPAGETYPE") != null)
                {
                    int nAction = Func.parseInt(ayParam.get("ANDROIDPAGETYPE"));
                    switch (nAction)
                    {
                        case Pub.JY_MENU_OpenWebInfoContent:
                        {
                            strParam = strParam.substring(("androidpagetype=" + Pub.JY_MENU_OpenWebInfoContent + "&&").length());
                            Bundle bundle = new Bundle();
                            bundle.putString(JiuyiBundleKey.PARAM_HTTPServer, strParam);
                            changePage(bundle, nAction, true);
                        }
                        break;
                        case 0://如果是或js
                            if(nLeftRight == 1){
                                loadUrl(m_sFirstAction);
                            }else if(nLeftRight == 2){
                                loadUrl(m_sSecondAction);
                            }
                            break;
                    }
                }
            }
        }
    }
}
