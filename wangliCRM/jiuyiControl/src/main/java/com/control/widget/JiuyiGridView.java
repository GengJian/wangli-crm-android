package com.control.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.control.utils.Res;

/**
 * ****************************************************************
 * 文件名称 : jiuyiGridView
 * 作   者 : Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述 : GridView自定义控件
 * ****************************************************************
 */
public class JiuyiGridView extends GridView {

    private int mHeight=0;

    public JiuyiGridView(Context context) {
        super(context);
    }

    public JiuyiGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiuyiGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写该方法，达到使 GridView 适应 ExpandableListView 的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }
}
