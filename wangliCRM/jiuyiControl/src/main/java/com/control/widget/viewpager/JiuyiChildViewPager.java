package com.control.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * ****************************************************************
 * 文件名称 : jiuyiChildViewPager
 * 作       者 : zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : 自定义滑动控件，解决滑动冲突问题
 *****************************************************************
 */
public class JiuyiChildViewPager extends ViewPager {
	float x = 0;
	float y = 0;
	private int slop;
	private boolean mInArea;

	public JiuyiChildViewPager(Context context) {
		this(context, null);
	}

	public JiuyiChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		slop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = ev.getX();
			y = ev.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(ev.getX() - x) > Math.abs(ev.getY() - y)
					&& Math.abs(ev.getX() - x) > slop) {
				getParent().requestDisallowInterceptTouchEvent(true);
			} else if (Math.abs(ev.getX() - x) < Math.abs(ev.getY() - y)
					&& Math.abs(ev.getY() - y) > slop) {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 当拦截触摸事件到达此位置的时候，返回true，
		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
		// return true;
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		float distance = 0;
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mInArea = true;
			x = ev.getX();
			break;

		case MotionEvent.ACTION_MOVE:
			distance = ev.getX() - x;
			if (Math.abs(distance) > slop) {
				mInArea = false;
            }
			break;
		case MotionEvent.ACTION_UP:
			if(mInArea){
				performClick();
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			mInArea = false;
		default:
			break;
		}
		super.onTouchEvent(ev);
		return true;

	}

	@Override
	public boolean performClick() {
		super.performClick();
		return true;
	}

	private GestureDetector gd = new GestureDetector(getContext(),
			new OnGestureListener() {

				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					performClick();
					return true;
				}

				@Override
				public void onShowPress(MotionEvent e) {
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					return true;
				}

				@Override
				public void onLongPress(MotionEvent e) {
				}

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					return true;
				}

				@Override
				public boolean onDown(MotionEvent e) {
					return true;
				}
			});
}
