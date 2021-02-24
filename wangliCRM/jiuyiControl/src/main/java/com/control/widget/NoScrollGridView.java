package com.control.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 屏蔽掉GridView的滚动，防止嵌入ScrollView时出现冲突
 * by:Hankkin at：2015-08-05 11:31:06
 *
 */
public class NoScrollGridView extends GridView {
	public boolean isOnMeasure;

	public NoScrollGridView(Context context) {
		super(context);
	}
	
	public NoScrollGridView(Context context, AttributeSet attrs) {   
        super(context, attrs);   
    }
	
	public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {   
        super(context, attrs, defStyle);   
    }   
    
	//该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override   
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		isOnMeasure = true;
        int expandSpec = MeasureSpec.makeMeasureSpec(   
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);   
        super.onMeasure(widthMeasureSpec, expandSpec);   
    }

/*	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		isOnMeasure = true;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}*/

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		isOnMeasure = false;
		super.onLayout(changed, l, t, r, b);
	}

}
