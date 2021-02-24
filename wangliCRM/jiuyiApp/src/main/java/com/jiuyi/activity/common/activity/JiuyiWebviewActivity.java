package com.jiuyi.activity.common.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.Func;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.webview.JiuyiWebViewDealUrlData;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.HashMap;
import java.util.Map;

import com.jiuyi.fragment.JiuyiWebViewFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebviewActivity.java
 * 作    者:Created by zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述:网页界面
 * 注意事项:网页调用系统自定义键盘，
 *          1、如果是通过放入jiuyi_fragment_container里面，自定义键盘弹不出
 *          2、如果通过<fragment android:name="com.com.jiuyi.fragment.JiuyiWebViewFragment"方式，，自定义键盘可以弹出
 * ****************************************************************
 */

public class JiuyiWebviewActivity extends JiuyiActivityBase {
    public JiuyiWebViewFragment mWebViewFragment;
    private  String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void createReq(boolean IsBg) {
        if(mWebViewFragment != null ){
            mWebViewFragment.getCurrWebView().GoBackOnLoad();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 切换皮肤
     */
    public void changeSkinType(){
        //切换主题 标题栏 状态栏的皮肤
        super.changeSkinType();

        if(mWebViewFragment != null){
            mWebViewFragment.changeSkinType();
        }
    }
    /**
     * 初始化ui
     */
    public void onInit(){
        //zhengss 放在inflate之前，否则参数会丢失
        if(Func.IsStringEmpty(mBundle.getString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE))) {
            mBundle.putString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, "0");
            getIntent().putExtras(mBundle);
        }
        if(!Func.IsStringEmpty(mBundle.getString(JiuyiBundleKey.PARAM_TITLE))) {
            mTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        }
        if(!Func.IsStringEmpty(mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer))) {
            url=mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer);
        }

        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_webview10061_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少

        //获取WebView10061Fragment对象
        FragmentManager fm = getSupportFragmentManager();
        mWebViewFragment = (JiuyiWebViewFragment) fm.findFragmentById(Res.getViewID(this, "webview_fragment"));
        if(mWebViewFragment == null ) {
            mWebViewFragment = new JiuyiWebViewFragment();
            fm.beginTransaction().add(Res.getViewID(this, "webview_fragment"), mWebViewFragment).commit();
        }
        //zhengss 初始化Fragment是在布局里，所以要设置ICallActivityCallBack
        mWebViewFragment.setCallBack(mCallActivityCallBack);

        setTitle(mTitle);
        setContentView(mBodyLayout);
    }

    /**
     * 重新加载
     */
    public void reload(){
        if(mWebViewFragment != null){
            mWebViewFragment.reload();
        }
    }

    /**
     * 设置中间标题栏文字
     * 如果不是执行通用的标题规则，请子类重写
     * 注意事项：本来不是使用通用的TitleBarBase(visiblility=gone)，而是使用mWebViewFragment.getTitleCenterView()作为标题显示控件
     * mBodyLayout.getTitleBar().setTitle()使用的是TitleBarBase里的显示规则
     * @param title
     */
    public void setTitle(String title, String titleType) {
        if(mBodyLayout != null && !Func.IsStringEmpty(title)){
            if(titleType != null)
                mTitleType = titleType;
            if(!title.equals(mTitle))
                mTitle = title;
            mBodyLayout.getTitleBar().setTitle(mWebViewFragment.getTitleCenterView(), mTitle, "", "", mTitleType);
        }
    }


    /**
     * 设置activity主题
     */
    public void setActivityTheme() {
        if(mBundle != null) {
            String strParam = mBundle.getString(JiuyiBundleKey.PARAM_HTTPServer);
            if (!Func.IsStringEmpty(strParam)) {
                Map<String, String> ayParamMap = new HashMap<String, String>();
                ayParamMap = JiuyiWebViewDealUrlData.onDealParamsWithAction(mPageType, strParam);
                //主题切换
                String strSkinType = ayParamMap.get("skintype");
                if (!Func.IsStringEmpty(strSkinType)) {
                    int mSkinType = Func.parseInt(strSkinType) % 2;
                    if (mSkinType == 1) {
                        setTheme(Res.getStyleID(getApplicationContext(), "jiuyi_ThemeCompat.White"));
                        return;
                    }
                }
            }
        }
        super.setActivityTheme();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(JiuyiEventBusEvent event) {
        if (event == null)
            return;

        switch (event.getEventType()){
            default:
                super.onUserEvent(event);
                break;
        }
    }
}
