package com.control.widget.keyboard;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.JiuyiLog;
import com.control.utils.Res;

/**
 * ****************************************************************
 * 文件名称:JiuyiDealKeyBoardTopBar.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:处理KeyBoard顶部的横条
 * ****************************************************************
 */

public class JiuyiDealKeyBoardTopBar {
    private DealKeyBoardTopLineCallBack mCallBack;
    /**
     * 键盘上方的切换布局
     */
    private RelativeLayout mSwitchKeyboardLayout;
    private TextView mStockkeyboard;
    private TextView mChinesekeyboard;
    /**
     * 键盘上方的切换布局
     */
    private int mNaviDelatY = 0;
    public JiuyiDealKeyBoardTopBar(DealKeyBoardTopLineCallBack callBack){
        mCallBack = callBack;
        keyBoardTitleBar();
        ListenerSoftKeyBoard();
    }
    /**
     * 监听SoftKeyBoard的显示和隐藏
     */
    private void ListenerSoftKeyBoard(){
        JiuyiSoftKeyBoardListener.setListener(mCallBack.getActivity(), new JiuyiSoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                keyBoardShowCallBack(height);
            }

            @Override
            public void keyBoardHide(int height) {
                keyBoardHideCallBack(height);
            }
        });
    }

    public void keyBoardShowCallBack(int height) {
        JiuyiLog.i("JiuyiDealKeyBoardTopBar", "keyBoardShowCallBack.height="+height);
        if(mSwitchKeyboardLayout == null)
            return;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)mSwitchKeyboardLayout.getLayoutParams();
        lp.bottomMargin = height;
//        lp.topMargin = Res.getHeightPixels() + mNaviDelatY - height - Rc.getIns().getTitleHeight() - Rc.getIns().getTopStatusBarHeight(mCallBack.getActivity());
        mSwitchKeyboardLayout.setLayoutParams(lp);
        mSwitchKeyboardLayout.setVisibility(View.VISIBLE);
    }

    public void keyBoardHideCallBack(int height) {
        JiuyiLog.i("JiuyiDealKeyBoardTopBar", "keyBoardHideCallBack.height="+height);
        if(mSwitchKeyboardLayout == null)
            return;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)mSwitchKeyboardLayout.getLayoutParams();
        lp.bottomMargin = 0;//Res.getHeightPixels();
        mSwitchKeyboardLayout.setLayoutParams(lp);
        mSwitchKeyboardLayout.setVisibility(View.GONE);

        if(!mCallBack.getKeyBoardView().isShowing()){
            showStockKeyBoardColor();
        }
    }

    public void keyBoardTitleBar() {
        //键盘上方的切换文字
        mSwitchKeyboardLayout = (RelativeLayout) mCallBack.getRootView().findViewById(Res.getViewID(null, "tzt_switchkeyboard_Layout"));
        mStockkeyboard = (TextView) mCallBack.getRootView().findViewById(Res.getViewID(null, "tzt_switchkeyboard_stockkeyboard_textview"));
        mChinesekeyboard = (TextView) mCallBack.getRootView().findViewById(Res.getViewID(null, "tzt_switchkeyboard_chinesekeyboard_textview"));
        mStockkeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChineseKeyBoardColor();
                mCallBack.getKeyBoardView().onShowSystemKeyBoard();
            }
        });
        mChinesekeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showStockKeyBoardColor();
                    JiuyiKeyBoardManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mSwitchKeyboardLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mCallBack.showDefaultKeyboard();
                        }
                    }, 1);
            }
        });
        ImageView closekeyboard  = (ImageView) mCallBack.getRootView().findViewById(Res.getViewID(null, "tzt_switchkeyboard_close_imageview"));
        closekeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack.getKeyBoardView().isShowing()){
                    JiuyiKeyBoardManager.getIns().closeKeyBoard();
                }else{
                    showStockKeyBoardColor();
                    JiuyiKeyBoardManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        //监听SoftKeyBoard的显示和隐藏
        ListenerSoftKeyBoard();
    }

    private void showStockKeyBoardColor(){
        mStockkeyboard.setVisibility(View.VISIBLE);
        mChinesekeyboard.setVisibility(View.GONE);
    }
    private void showChineseKeyBoardColor(){
        mStockkeyboard.setVisibility(View.GONE);
        mChinesekeyboard.setVisibility(View.VISIBLE);
    }

    public interface DealKeyBoardTopLineCallBack {
        public JiuyiKeyBoardView getKeyBoardView();
        public View getRootView();
        public Activity getActivity();
        public void showDefaultKeyboard();
    }

}
