package com.control.widget.viewpager.lazyviewpager;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.control.R;


/**
 * ViewPager that add items lazily in the two following situation:
 * <ul>
 *     <li>its adapter inherits from {@link LazyViewPagerAdapter}</li>
 *     <li>its adapter inherits from {@link LazyFragmentPagerAdapter} and Fragment implements {@link LazyFragmentPagerAdapter.Laziable} </li>
 * </ul>
 */
public class DispatchTouchLazyViewPager extends ViewPager {
	float x = 0;
	float y = 0;
	private int slop;
	private static final float DEFAULT_OFFSET = 0.5f;

	private LazyPagerAdapter mLazyPagerAdapter;
	private float mInitLazyItemOffset = DEFAULT_OFFSET;

	public DispatchTouchLazyViewPager(Context context) {
		super(context);
	}

	public DispatchTouchLazyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LazyViewPager /* Res.getStyleableArray(context, "LazyViewPager")*/);
		setInitLazyItemOffset(a.getFloat(R.styleable.LazyViewPager_init_lazy_item_offset /*Res.getStyleableID(context, "init_lazy_item_offset")*/, DEFAULT_OFFSET));
		a.recycle();
		slop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

    /**
     * change the initLazyItemOffset
     * @param initLazyItemOffset set mInitLazyItemOffset if {@code 0 < initLazyItemOffset <= 1}
     */
	public void setInitLazyItemOffset(float initLazyItemOffset) {
		if (initLazyItemOffset > 0 && initLazyItemOffset <= 1) {
		    mInitLazyItemOffset = initLazyItemOffset;
        }
	}

	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
        mLazyPagerAdapter = adapter != null && adapter instanceof LazyPagerAdapter ? (LazyPagerAdapter) adapter : null;
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		if (mLazyPagerAdapter != null) {
			if (getCurrentItem() == position) {
				int lazyPosition = position + 1;
				if (offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
				}
			} else if (getCurrentItem() > position) {
				int lazyPosition = position;
				if (1 - offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
				}
			}
		}
		super.onPageScrolled(position, offset, offsetPixels);
	}
	
//	@Override
//	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
//		// TODO Auto-generated method stub
//		if(this.getCurrentItem()== 3
//        		&& v instanceof DispatchTouchLazyViewPager
//        		&& mLazyPagerAdapter.getCurrentItem() instanceof JiuyiWebviewFragmentBase){
////        	TztLog.e("scroll test", "dx:"+dx+" x:"+x+" y:"+y);
//        	return y<350;
////			return ((WebLayoutBase) mLazyPagerAdapter.getCurrentItem()).canScrollHor(-dx);
//		}else {
//            return super.canScroll(v, checkV, dx, x, y);
//        }
//	}
	
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
}
