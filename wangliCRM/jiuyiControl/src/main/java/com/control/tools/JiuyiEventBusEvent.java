package com.control.tools;

/**
 * ****************************************************************
 * 文件名称:JiuyiEventBusEvent.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:EventBus的事件定义
 * ****************************************************************
 */

public class JiuyiEventBusEvent {
    public enum EventType {
        EventType_ChangeSkinType,//切换皮肤风格
        EventType_SetBaseCallTopCallBack,//底层调用上层的值或者方法的回调
        EventType_CloseSysKeyBoard,//关闭键盘
        EventType_Refresh,//刷新当前界面
    }

    /** 事件类型*/
    private EventType mEventType;
    /** 事件信息*/
    private String mMessage;
    /** 要加载的url */
    private String mUrl;



    public JiuyiEventBusEvent(EventType mEventType, String mMessage, String mUrl) {
        this.mEventType = mEventType;
        this.mMessage = mMessage;
        this.mUrl = mUrl;
    }


    public EventType getEventType() {
        return mEventType;
    }

    public void setEventType(EventType mEventType) {
        this.mEventType = mEventType;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }



}
