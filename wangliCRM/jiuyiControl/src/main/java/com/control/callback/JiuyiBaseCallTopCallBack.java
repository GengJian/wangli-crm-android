package com.control.callback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.control.widget.relativeLayout.JiuyiRelativeLayout;


/**
 * ****************************************************************
 * 文件名称:JiuyiBaseCallTopCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:底层调用上层的值或者方法的回调
 * ****************************************************************
 */

public interface JiuyiBaseCallTopCallBack {
    /**
     * 软件退出
     */
    void doAppExit();
    /**
     * 软件升级
     */
    void gotoUpVersion(Activity activity, String url, int updateType);
    /**
     * 获取推送的唯一号
     */
    String getPushMsgUniqueFlag();
    /**
     * 获取是否开启推送
     */
    boolean getIsAutoPushMsg();
    /**
     * 设置是否开启推送
     */
    void setIsAutoPushMsg(boolean isAutoPush);









    /**
     * 获得当前栈顶的Activity
     */
    Activity getCurrentActivity();

    /**
     * 获得当前栈顶的Activity的RelativeLayout
     */
    JiuyiRelativeLayout getRelativeLayout();


    /**
     * 跳转到指定界面，一般是退回到RootActivity
     * 如果bstartPageTypeActivity为flase，则只是吧nPageType之前的Activity都finish掉，否则就是要退到这个Activity
     * @nPageType 	要退到的activity的pagetype，如果要返回到首页，则参数为Pub.mStartHomePage即可
     * @bstartPageTypeActivity	是否要打开要退到的activity的pagetype的Activity
     * @return 是否成功跳转
     */
    public void popBackToActivity(int nPageType, boolean bstartPageTypeActivity);

    /**
     *
     * @param activity 要移除的activity
     * @param bTransition 是否带动画
     */
    void popActivity(Activity activity, boolean bTransition);
    /**
     * 是否需要密码锁屏
     */
    public boolean isNeedPasswordLockPage();

    /**
     * 当前显示activity是否被后置
     * @return
     */
    public boolean isCurrActivityStop();

    /**
     * 切换界面
     *
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     */
    public void changePage(Bundle mBundle, int nPageType, boolean bSavePage);

    /**
     * 重新设置首页标题头
     */
    public void resetRootActivityTitle();

    /**
     * 请求登录
     */
    public void createReqPushMsgTradeLogin(String account, String khbranch, Context context);

    void startLoginPage();



}
