package com.control.widget.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ****************************************************************
 * 文件名称:jiuyiSpinner.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 11:24
 * 文件描述:jiuyiSpinner，几乎不用了
 * ****************************************************************
 */
public class JiuyiSpinner extends android.support.v7.widget.AppCompatSpinner
{
	public JiuyiSpinner(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		//setGravity(Gravity.CENTER_VERTICAL);
	}

	public JiuyiSpinner(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//setGravity(Gravity.CENTER_VERTICAL);
	}

	public JiuyiSpinner(Context context)
	{
		super(context);
		//setGravity(Gravity.CENTER_VERTICAL);
	}
	
	@Override
	public void setSelection(int position) {
		super.setSelection(position);
		setSelection(position, true);
		this.invalidate();
	}
		
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		if (this.getAdapter() != null && this.getAdapter().getCount() > 0)
		{
			return super.onTouchEvent(event);
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public void setSelection(int position, boolean animate) 
	{
		super.setSelection(position, animate);
	}
	
	@Override
	public Object getSelectedItem() 
	{
		return super.getSelectedItem();
	}
	

}
