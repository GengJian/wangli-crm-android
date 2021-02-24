package com.control.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * ****************************************************************
 * 文件名称:jiuyiTrdViewPager.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:自定义滑动控件
 * ****************************************************************
 */

public class JiuyiTrdViewPager extends ViewPager {
    private boolean m_bCanScroll;
    private ViewPager mPager;


    public JiuyiTrdViewPager(Context context, boolean bCanScroll) {
        super(context);
        m_bCanScroll = bCanScroll;
    }

    public JiuyiTrdViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPager getmPager() {
        return mPager;
    }

    public void setmPager(ViewPager mPager) {
        this.mPager = mPager;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setFocusable(false);
        if (!m_bCanScroll) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        setFocusable(false);
        if (!m_bCanScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    /**
     * 设置是否可以滑动翻屏
     * @param isCanScroll
     */
    public void setCanScroll(boolean isCanScroll){
        m_bCanScroll = isCanScroll;
    }

    /**
     * 获取是否可以滑动翻屏
     * @return
     */
    public boolean getCanScroll(){
        return m_bCanScroll;
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            if (distanceY != 0 && distanceX != 0) {
            }
            if (Math.abs(distanceY) >= Math.abs(distanceY)
                    && Math.abs(distanceY) >= 10) {
                return true;
            }
            return false;
        }

    }
}
