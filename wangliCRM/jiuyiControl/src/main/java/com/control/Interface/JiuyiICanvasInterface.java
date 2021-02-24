package com.control.Interface;

/**
 * ****************************************************************
 * 文件名称 : JiuyiICanvasInterface.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 每个界面都实现了这些接口；
			这两个接口包含了客户端界面大部分操作，包括：
			A）工具栏设置、
			B）界面跳转、界面回退、
			C）按键事件、触摸事件、
			D）弹出处理对话框、
 *****************************************************************
 */
public interface JiuyiICanvasInterface extends JiuyiISendReqInterface {
    /**
     * 界面初始化view等
     * @return
     */
    void onInit();

    /**
     * 调用设置标题栏事件
     * @param title 中间标题内容
     */
    void setTitle(String title, String titleType);

    /**
     * 页面回退事件
     */
    void backPage();


    /**
     * 根据功能号加界面统计信息
     * @param nPrePageType	上一界面
     * @param nPageType	    本次要跳转的界面
     * @param bPageStart	    页面开始还是页面结束
     * @return                 是否成功的处理此请求
     */
    boolean actionWithTCAgentPage(int nPrePageType, int nPageType, boolean bPageStart);//

    /**
     * 切换皮肤
     */
    void changeSkinType();

    /**
     * 消息推送过来需要客户端具体界面处理的事物，调用此方法
     * @param type      消息代码
     * @param subtype   消息子代码
     * @param title     消息的标题
     * @param message   消息的信息
     * @return           是否成功的处理此请求
     */
    boolean loadJsByMsgPush(int type, int subtype, String title, String message);

    /**
     * 当前的Activity是否关闭了
     * @return
     */
    boolean currActivityStop();

    /**
     * 键盘按钮或标题栏按钮点击的回调
     */
    void onKeyboardClick(int primaryCode);

    /**
     * 跳转到指定界面，一般是退回到RootActivity
     * 如果bstartPageTypeActivity为flase，则只是吧nPageType之前的Activity都finish掉，否则就是要退到这个Activity
     * @nPageType 	要退到的activity的pagetype，如果要返回到首页，则参数为Pub.mStartHomePage即可
     * @bstartPageTypeActivity	是否要打开要退到的activity的pagetype的Activity
     * @return 是否成功跳转
     */
    boolean popBackToActivity(int nPageType, boolean bstartPageTypeActivity);

    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange);

}
