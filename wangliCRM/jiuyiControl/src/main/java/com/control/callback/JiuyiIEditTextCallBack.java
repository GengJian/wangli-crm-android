package com.control.callback;

import android.app.Activity;

import com.control.widget.keyboard.JiuyiKeyBoardView;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.JiuyiEditText;
import com.control.widget.webview.JiuyiWebView;

/**
 * ****************************************************************
 * 文件名称:JiuyiIEditTextCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:EditText的回调
 * ****************************************************************
 */

public interface JiuyiIEditTextCallBack {
    public JiuyiKeyBoardView getKeyBoardView();
    public JiuyiRelativeLayout getRelativeLayout();
    public Activity getActivity();
    public JiuyiWebView getWebView();
    public void setValueByKeyBoard(JiuyiEditText edit, int keycode);
    public void keyBoardShowCallBack(int height);
    public void keyBoardHideCallBack(int height);


}
