package com.control.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.control.utils.Res;

/**
 * ****************************************************************
 * 文件名称:jiuyiSwipeRefreshLayout.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:SwipeRefreshLayout的基类
 * ****************************************************************
 */

public class JiuyiSwipeRefreshLayout extends SwipeRefreshLayout{
    /** 上下文 */
    protected Context context;
    private float mStartX = 0;

    public JiuyiSwipeRefreshLayout(Context context) {
        super(context);
        this.context = context;
        initSetting();
    }

    public JiuyiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initSetting();
    }

    /**
     * 设置SwipeRefreshLayout属性
     */
    private void initSetting(){
        //改变加载显示的颜色
        setColorSchemeColors(Res.getColor(null, "jiuyi_blue"), Res.getColor(null, "jiuyi_blue"));
        //设置初始时的大小
        setSize(SwipeRefreshLayout.DEFAULT);
        //设置向下拉多少出现刷新
        setDistanceToTriggerSync(Res.Dip2Pix(50));
        //设置刷新出现的位置
        setProgressViewEndTarget(false, 200);
    }

    /**
     * ViewGroup事件分发  纵向滑动的自己处理，横向滑动的交给子view
     * 如果以后要加入ViewPage 这里需要再重新处理判断子view类型
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) Math.abs(ev.getX() - mStartX);
                if (offsetX > 30) {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
