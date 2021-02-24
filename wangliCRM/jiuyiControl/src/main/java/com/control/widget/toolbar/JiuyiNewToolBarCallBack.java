package com.control.widget.toolbar;

import android.view.View;

/**
 * ****************************************************************
 * 文件名称:JiuyiNewToolBarCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:底部工具栏点击的回调
 * ****************************************************************
 */

public interface JiuyiNewToolBarCallBack {
    /**
     * 底部工具栏点击的回调
     * @param view
     * @param nCurAction
     * @return
     */
    public boolean onClickToolBarItem(View view, int nCurAction, int forceSkinType);

    /**
     *
     * @return 是否选中并执行第一个功能
     */
    public boolean isSelectFirstAction();

    /**
     * 点击按钮显示浮框
     * @param nButtonCount 按钮总个数
     * @param sButtonImageFlag 要显示浮框的标签
     * @param nButtonSelIndex 当前要显示浮框的位置索引
     */
    public void setPopWindowToolBarValue(int nButtonCount, String sButtonImageFlag, int nButtonSelIndex);

    /**
     * 获取是否为画线状态
     * @return
     */
    public boolean isDrawLine();
}
