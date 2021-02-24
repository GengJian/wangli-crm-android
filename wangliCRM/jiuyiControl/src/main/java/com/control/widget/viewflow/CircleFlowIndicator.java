/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.control.widget.viewflow;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.control.utils.Pub;
import com.control.utils.Res;


/**
 * A FlowIndicator which draws circles (one for each view). The current view
 * position is filled and others are only striked.<br/><br/>
 * Availables attributes are:<br/>
 * <ul>fillColor: Define the color used to fill a circle (default to white)</ul>
 * <ul>strokeColor: Define the color used to stroke a circle (default to white)</ul>
 * <ul>radius: Define the circle radius (default to 4)</ul>
 */
public class CircleFlowIndicator extends View implements FlowIndicator {
	private int radius = Res.Dip2Pix(4);
	/** 空心的圆点 **/
	private final Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
	/** 实心的圆点 **/
	private final Paint mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint mPaintImg = new Paint(Paint.ANTI_ALIAS_FLAG);
	private View viewFlow;
	private float currentScroll = 0f;
	private int flowWidth = 0;

	private int[] _nImgIDList = null;
	private int _nCurrentSelIndex = -1;
	
	/**
	 * Default constructor
	 * 
	 * @param context
	 */
	public CircleFlowIndicator(Context context) {
		super(context);
		int color = Res.getColor(getContext(), "tzt_CircleFlowIndicator_color");
		initColors(color, color);
	}
	public CircleFlowIndicator(Context context, Object o) {
		this(context);
		viewFlow = (View) o;
	}
	
	
	public int getViewsCount(){
		if(viewFlow != null){
			if(viewFlow instanceof ViewPager)
				return ((ViewPager)viewFlow).getAdapter().getCount();
			else
				return 0;
		}else{
			return 0;
		}
	}
	
	public int getViewsWidth(){
		if(viewFlow != null){
			return  viewFlow.getWidth();
		}else{
			return 0;
		}
	}

	/**
	 * The contructor used with an inflater
	 * 
	 * @param context
	 * @param attrs
	 */
	public CircleFlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		int fillColor = Color.WHITE;//a.getColor(R.styleable.tztCircleFlowIndicator_tztfillColor,0xFFFFFFFF);
		int strokeColor = Color.WHITE;//a.getColor(R.styleable.tztCircleFlowIndicator_tztstrokeColor, 0xFFFFFFFF);

		radius = (int)(Res.Dip2Pix(4));// (int)(a.getInt(R.styleable.tztCircleFlowIndicator_tztradius, 4)*mDensity);
		initColors(fillColor, strokeColor);
	}

	private void initColors(int fillColor, int strokeColor) {
		mPaintStroke.setStyle(Style.STROKE);
		mPaintStroke.setColor(strokeColor);
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(fillColor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int count = 2;
		if (viewFlow != null) {
			count = getViewsCount();
		}
		
//		mPaintFill.setColor(Pub.BgColor);
//		canvas.drawRect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + getWidth(), getPaddingTop() + getHeight(), mPaintFill);
		
		int y = getPaddingTop() + (getHeight()) / 2;
		int x = (getWidth() - radius * 2 * count) / 2;
		
		if (_nImgIDList != null)
		{
			int nDecimal = 8;
			int nDrawX = (getWidth() - radius * nDecimal * count) / 2;
			y = getPaddingTop();
			for (int iLoop = 0; iLoop < count; iLoop++) 
			{
				Drawable drawable = getResources().getDrawable(Res.getDrawabelID(getContext(), "tzt_hqviewflow_" + _nImgIDList[iLoop]));
				BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
				Bitmap bitmap = bitmapDrawable.getBitmap();
				canvas.drawBitmap(bitmap, nDrawX + getPaddingLeft() + radius + (iLoop * (nDecimal * radius + radius)), y, mPaintImg);
			}
			
			if (_nCurrentSelIndex >= 0 && _nCurrentSelIndex < _nImgIDList.length)
			{
				Drawable drawable = getResources().getDrawable(Res.getDrawabelID(getContext(), "tzt_hqviewflow_" + _nImgIDList[_nCurrentSelIndex] + "_sel"));
				BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
				Bitmap bitmap = bitmapDrawable.getBitmap();
				canvas.drawBitmap(bitmap, nDrawX + getPaddingLeft() + radius + (_nCurrentSelIndex * (nDecimal * radius + radius)), y, mPaintImg);
			}
		}
		else
		{
			for (int iLoop = 0; iLoop < count; iLoop++) {
				canvas.drawCircle(x + getPaddingLeft() + radius
						+ (iLoop * (2 * radius + radius)),
						y, radius, mPaintStroke);
			}
			
			mPaintFill.setColor(Pub.fontColor);
			int cx = 0;
			if (flowWidth != 0) {
//				if(viewFlow != null && viewFlow instanceof ViewFlow){
//					cx = (int) ((currentScroll * (2 * radius + radius)) / flowWidth);
//				}else{
//					cx = (int) (currentScroll * (2 * radius + radius));
//				}
                if(viewFlow != null){
                    cx = (int) (currentScroll * (2 * radius + radius));
                }
            }
		    canvas.drawCircle(x + getPaddingLeft() + radius + cx, y, radius, mPaintFill);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.taptwo.android.widget.ViewFlow.ViewSwitchListener#onSwitched(android
	 * .view.View, int)
	 */
	public void onSwitched(View view, int position) {
		_nCurrentSelIndex = position;
		currentScroll = position;
		if(viewFlow != null)
			flowWidth = getViewsWidth();
		invalidate();
	}

	/* (non-Javadoc)
	 * @see org.taptwo.android.widget.FlowIndicator#setViewFlow(org.taptwo.android.widget.ViewFlow)
	 */
//	public void setViewFlow(ViewFlow view) {
//		viewFlow = view;
//		if(viewFlow != null)
//			flowWidth = getViewsWidth();
//		invalidate();
//	}

	public void SetFlowWidth(int w) {
		flowWidth = w;
		invalidate();
	}
	
	/* (non-Javadoc)
	 * @see org.taptwo.android.widget.FlowIndicator#onScrolled(int, int, int, int)
	 */
	public void onScrolled(int h, int v, int oldh, int oldv) {
		currentScroll = h;
		if(viewFlow != null)
			flowWidth = getViewsWidth();
		invalidate();
	}
	public void onScrolled(float h, int v, int oldh, int oldv) {
		currentScroll = h;
		if(viewFlow != null)
			flowWidth = getViewsWidth();
		invalidate();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	/**
	 * Determines the width of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Calculate the width according the views count
		else {
			int count = 2;
			if (viewFlow != null) {
				count = getViewsCount();
			}
			result = getPaddingLeft() + getPaddingRight()
					+ (count * 2 * radius) + (count - 1) * radius + 1;
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * Determines the height of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The height of the view, honoring constraints from measureSpec
	 */
	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Measure the height
		else {
			result = 2 * radius + getPaddingTop() + getPaddingBottom() + 1;
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * Sets the fill color
	 * 
	 * @param color
	 *            ARGB value for the text
	 */
	public void setFillColor(int color) {
		mPaintFill.setColor(color);
		invalidate();
	}

	/**
	 * Sets the stroke color
	 * 
	 * @param color
	 *            ARGB value for the text
	 */
	public void setStrokeColor(int color) {
		mPaintStroke.setColor(color);
		invalidate();
	}

	@Override
	public void setViewFlowIndicatorImg(int[] nImgID) 
	{
		_nImgIDList = nImgID;
	}
}