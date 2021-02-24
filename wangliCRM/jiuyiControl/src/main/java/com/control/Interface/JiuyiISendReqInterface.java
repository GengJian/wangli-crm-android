package com.control.Interface;

import android.os.Bundle;

/**
 * ****************************************************************
 * 文件名称:JiuyiISendReqInterface.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:数据请求相关的接口
 * 注意事项:此接口只是处理Error和进度条
 * ****************************************************************
 */
public interface JiuyiISendReqInterface extends JiuyiIDialogInterface {
    /**
     * 设置进度条的状态
     * @param barProcess 进度
     */
    void showProcessBar(int barProcess);


    /**
     * 错误信息用Toast显示提示信息
     * @param msg       错误信息
     */
    void showErrorMessage(String msg);

    /**
     * @param IsBg  是否显示进度条，true不显示，false显示；
     */
    void createReq(boolean IsBg);



    /**
     * 切换界面
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     * @param mBundle 要带的参数
     */
    void changePage(Bundle mBundle, int nPageType, boolean bSavePage);
    /**
     * 获取当前的界面号
     */
    int getPageType();
}
