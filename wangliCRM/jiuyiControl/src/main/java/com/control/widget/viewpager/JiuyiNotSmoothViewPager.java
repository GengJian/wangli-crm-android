package com.control.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * ****************************************************************
 * 文件名称:jiuyiotSmoothViewPager.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:可以禁止滑动效果的ViewPager
 * ****************************************************************
 */

public class JiuyiNotSmoothViewPager extends ViewPager {

    private boolean allowScroll = true;

    public JiuyiNotSmoothViewPager(Context context) {
        super(context);
    }

    public JiuyiNotSmoothViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //禁用页面切换时的滑动效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }

    //禁用页面切换
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (allowScroll)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (allowScroll)
            return super.onTouchEvent(arg0);
        else
            return false;
    }

    public boolean isAllowScroll() {
        return allowScroll;
    }

    public void setAllowScroll(boolean allowScroll) {
        this.allowScroll = allowScroll;
    }
}
