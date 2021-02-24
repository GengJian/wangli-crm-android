package com.control.Interface;

import android.content.Intent;
import android.widget.LinearLayout;

/**
 * ****************************************************************
 * 文件名称:JiuyiIUiIntentInterface.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:处理网页请求的Dialog、对调按钮位置、第三方activity等，一般用于config下
 * ****************************************************************
 */
public interface JiuyiIUiIntentInterface
{
    /**
     * 与Intent相关或者调用第三方控件的操作
     * @param canvasInterface   当前界面
     * @param nPageType 当前界面号
     * @param object   附加参数
     * @return  返回要操作或打开的Activity
     */
    Intent getIntentTo(JiuyiICanvasInterface canvasInterface, int nPageType, Object object);

    /**
     * 功能有TAB页的配置key
     * @param nPageType 当前界面号
     * @return 交易功能有TAB页的配置key
     */
    String getTabChildSystem(int nPageType);

    /**
     * 弹窗口的确定和返回的位置是否切换
     * @return 弹窗口的确定和返回的位置是否切换
     */
    boolean changeDialogBtnPos();

    /**
     * 界面显示的tip
     * @param vParentLayout
     * @param nPageType 当前界面号
     */
    void setSoftUpdateTipView(LinearLayout vParentLayout, int nPageType);
}
