package com.control.Interface;

/**
 * ****************************************************************
 * 文件名称:JiuyiIGpsInterface.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:处理网页请求的GPS相关，一般用于config下
 * ****************************************************************
 */
public interface JiuyiIGpsInterface {
    /**
     * 开启GPS功能
     */
    void onStartGPS();
    /**
     * 关闭GPS功能
     */
    void onStopGPS();
    /**
     * 获取当前定位的x坐标
     */
    double getGPSY();
    /**
     * 获取当前定位的y坐标
     */
    double getGPSX();
}
