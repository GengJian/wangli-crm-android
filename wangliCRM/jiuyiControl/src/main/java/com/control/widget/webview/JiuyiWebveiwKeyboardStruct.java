package com.control.widget.webview;

import android.view.View;

import com.control.widget.keyboard.JiuyiKeyBoardView;
/**
 * ****************************************************************
 * 文件名称:JiuyiWebveiwKeyboardStruct.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:网页调用客户端自定义键盘的处理类
 * ****************************************************************
 */
public class JiuyiWebveiwKeyboardStruct {
	/** 在webview中触摸时候的x坐标值 **/
	private int mWebViewTouchX = 0;
	/** 在webview中触摸时候的Y坐标值 **/
    private int mWebViewTouchY = 0;
    /** 是否滑动过 **/
    private boolean mIsActionMove = false;
    /** 是否可以关闭键盘 **/
    private boolean mIsHideKeyBoardState = true;

    /**
     * 设置点击的x坐标
     * @param x x坐标
     */
	public void setWebViewTouchX(int x){
		mWebViewTouchX = x;
	}
    /**
     * 设置点击的y坐标
     * @param y y坐标
     */
	public void setWebViewTouchY(int y){
		mWebViewTouchY = y;
	}

    /**
     * 设置是否可以关闭键盘
     * @param bHide  是否关闭键盘
     */
	public void setHideKeyBoardState(boolean bHide){
		mIsHideKeyBoardState = bHide;
	}

    /**
     * 设置是否滑动过
     * @param bMove 是否滑动过
     */
	public void setActionMove(boolean bMove){
		mIsActionMove = bMove;
	}

    /**
     * 延时关闭键盘
     * @param view
     * @param delay
     * @param keyboard
     */
	public void setPostDelayHideKeyBoard(View view, int delay, final JiuyiKeyBoardView keyboard){
		if(view != null && keyboard!=null && !mIsActionMove){
			mIsHideKeyBoardState = true;
			view.postDelayed(new Runnable() {
				@Override
				public void run() {
					if(mIsHideKeyBoardState)
						keyboard.hideKeyboard();
				}
			}, delay);
		}
	}

    /**
     * 点击的x坐标
     * @return x坐标
     */
	public int getWebViewTouchX(){
		return mWebViewTouchX;
	}

    /**
     * 点击的y坐标
     * @return y坐标
     */
	public int getWebViewTouchY(){
		return mWebViewTouchY;
	}
}
