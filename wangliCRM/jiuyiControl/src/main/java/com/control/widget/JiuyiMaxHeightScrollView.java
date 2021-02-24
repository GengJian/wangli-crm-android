package com.control.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * ****************************************************************
 * 文件名称:jiuyiMaxHeightScrollView.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:设置最大高度的ScrollView
 * 注意事项:宽度高度不超过宽度
 * ****************************************************************
 */

public class JiuyiMaxHeightScrollView extends ScrollView {
    private int mMaxHeight = 0;

    public JiuyiMaxHeightScrollView(Context context) {
        super(context);
    }

    public JiuyiMaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiuyiMaxHeightScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMaxHeight(int maxHeight){
        mMaxHeight = maxHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(mMaxHeight > 0) {
            if (heightMode == MeasureSpec.EXACTLY) {
                heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
            }

            if (heightMode == MeasureSpec.UNSPECIFIED) {
                heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
            }
            if (heightMode == MeasureSpec.AT_MOST) {
                heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
            }
        }
        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);

        super.onMeasure(widthMeasureSpec, maxHeightMeasureSpec);
    }
}
