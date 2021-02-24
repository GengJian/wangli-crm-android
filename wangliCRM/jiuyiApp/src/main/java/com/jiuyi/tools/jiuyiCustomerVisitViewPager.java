package com.jiuyi.tools;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//import com.amap.api.maps.TextureMapView;

/**
 * ****************************************************************
 * 文件名称:jiuyiCustomerVisitViewPager.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:可以禁止滑动效果的ViewPager
 * ****************************************************************
 */

public class jiuyiCustomerVisitViewPager extends ViewPager {

    private boolean allowScroll = true;

    public jiuyiCustomerVisitViewPager(Context context) {
        super(context);
    }

    public jiuyiCustomerVisitViewPager(Context context, AttributeSet attrs) {
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
    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
//        if(v != this && v instanceof TextureMapView) {
//            return true;
//        }
        return super.canScroll(v,checkV,dx,x,y);
    }
    public boolean isAllowScroll() {
        return allowScroll;
    }

    public void setAllowScroll(boolean allowScroll) {
        this.allowScroll = allowScroll;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
