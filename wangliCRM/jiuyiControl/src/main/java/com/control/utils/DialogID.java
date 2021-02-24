package com.control.utils;

/**
 * ****************************************************************
 * 文件名称:DialogID.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:Dialog的ID相关
 * 特别说明:没有使用枚举是因为对dianlog的标识可能是其他的数字，不一定都是枚举型
 * ****************************************************************
 */

public class DialogID {
    /*全局，1XXX*/
    private static final int DialogStart 				= 1900; // Dialog号的开始
    public static final int DialogDoNothing 			= 1901; // Dialog的什么都不做
    public static final int DialogTrue 				= 1902; // Dialog的通用成功提示信息
    public static final int DialogFalse 				= 1903; // Dialog的通用失败提示信息
    public static final int DialogDeleteTrue 				= 1904; // Dialog的通用成功提示信息
    /*升级，2XXX*/
    public static final int DialogwarningMsg 			= 2904; // hint的警告信息
    public static final int DialogForceUpdate 			= 2905; // Dialog的强制升级
    public static final int DialogAdviceUpdate 			= 2906; // Dialog的建议升级
    public static final int DialogForbiddenUse 			= 2907; // Dialog的签名不正确，禁止使用


    /*登陆退出相关，4XXX*/
    public static final int DialogLoginFiald 			= 4914; // Dialog的登录失败
    public static final int DialogQuitTradeState 		= 4915; // Dialog的退出软件状态(用户看了才能执行回退动作)
    public static final int DialogSystemQiut		 	= 4916; // Dialog的退出软件
    public static final int DialogQuitTrade 			= 4917; // Dialog的退出登录

    /*打开和切换界面，5XXX*/
    public static final int DialogSelectChangePage 		= 5920; // Dialog的切换界面
    public static final int DialogOpenPage 				= 5921; // Dialog打开界面
    /*弹出框输入数字、时间等、电话短信，6XXX*/
    public static final int DialogInputValue 			= 6922; // Dialog的部分设置界面，点击弹出对话框要求输入值
    public static final int DialogInputInTime 			= 6923; // Dialog的设置推送等界面输入时间范围
    public static final int DialogInputTimeSpan 		= 6924; // Dialog的设置推送等界面输入时间间隔
    public static final int DialogSetSMSReq 			= 6925; // Dialog的发送请求短信通道
    public static final int DialogActionCall 			= 6926; // Dialog的电话界面

    /*网页弹出框，8XXX*/
    public static final int DialogOnJsAlert 			= 8934; // Dialog网页的js的alert
    public static final int DialogOnJsConfirm 			= 8935; // Dialog网页的js的confirm
    public static final int DialogRefreshWebView 		= 8936; // Dialog网页的Css加载失败刷新



}
