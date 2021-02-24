package com.control.callback;

import android.app.Activity;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.utils.AjaxWebClientUrlLis;

/**
 * ****************************************************************
 * 文件名称:JiuyiICallActivityCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:调用Activity的回调
 * 注意事项:当初始化一个Fragment后，如果该Fragment需要调用Activity的内容，则需要设置此回调
 *          不同的Activity可以继承此回调接口，达到多样化的目的
 * ****************************************************************
 */

public interface JiuyiICallActivityCallBack {
    /**
     * 获取当前的ActivityBase里的CanvasInterface
     */
    public JiuyiICanvasInterface getActivityCanvasInterface();
    /**
     * 获取处理网页url的类
     */
    public AjaxWebClientUrlLis getAjaxWebClientUrlLis();

    /**
     * 设置后退activity传参（有时候传参的数据是在activity层）
     */
    public void setBackBundle();

    /**
     * 获取Activity
     */
    public Activity getActivity();

}
