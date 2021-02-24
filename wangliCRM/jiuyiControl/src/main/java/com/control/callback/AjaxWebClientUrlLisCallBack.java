package com.control.callback;

import android.app.Activity;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.utils.AjaxWebClientUrlLis;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;

/**
 * ****************************************************************
 * 文件名称:AjaxWebClientUrlLisCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:AjaxWebClientUrlLis的回调
 * ****************************************************************
 */

public interface AjaxWebClientUrlLisCallBack {
    public JiuyiICanvasInterface getCanvasInterface();

    public Activity getActivity();


    public AjaxWebClientUrlLis getAjaxWebClientUrlLis();

    public JiuyiRelativeLayout getRelativeLayout();
}
