package com.control.Interface;

import com.control.setting.JiuyiHqAttrSet;


/**
 * ****************************************************************
 * 文件名称:JiuyiIConfigInterface.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:私有config配置的接口
 * ****************************************************************
 */
public interface JiuyiIConfigInterface {
    /**
     * View使用ViewFlow左右滑动的功能号
     * @param nPageType  界面号
     * @return            该界面号是否是WithViewFlow的
     */
    boolean actionWithViewFlow(int nPageType);
    /**
     * @param nPageType  界面号
     * @return
     */
    boolean actionWithoutLogin(int nPageType);

    /**
     * 根据功能号获取私有的Activity，此Activity要继承jiuyiActivityBase，否则使用默认的Activity
     * @param nPageType  界面号
     * @return            该界面号对应的Activity
     */
    Class<?> getActivityByPageType(int nPageType);
    /**
     * 功能的配置项
     * @return  功能的配置项的对象
     */
    JiuyiHqAttrSet getHqAttrSet();



	
	/**
	 * 除正常界面，其他界面需要锁屏的功能号
	 * @param nPageType   界面号
	 * @return             其他界面需要锁屏的功能号
	 */
    boolean getOtherNeedPasswordLock(int nPageType);

    /**
     * 初始化第三方的控件
     * 即把在application里初始化的放在config里面
     */
    void doInitInApplication();
}
