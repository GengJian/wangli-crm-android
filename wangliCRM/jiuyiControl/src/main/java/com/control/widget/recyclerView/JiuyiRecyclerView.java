package com.control.widget.recyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;
import com.control.utils.Res;


/**
 * ****************************************************************
 * 文件名称:jiuyiRecyclerView.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:jiuyiRecyclerView
 * ****************************************************************
 */

public class JiuyiRecyclerView extends android.support.v7.widget.RecyclerView{
	protected Context context;
	public LinearLayoutManager layoutManager;
	private OnBottomCallback mOnBottomCallback;

	/** 布局管理，主要用于获取可视起始位置，可视结束位置 */
	private RecyclerView.LayoutManager mLinearLayoutManager;
	/** 回调 滚动状态回调到上层*/
	private RecyclerViewCallBack mRecyclerViewCallBack;
	/** 可视起始位置 */
	private int mFirstVisibleItemIndex;
	/** 可视结束位置 */
	private int mLastVisibleItemIndex;


	public JiuyiRecyclerView(Context context) {
		super(context);
		this.context = context;
		setOrientation(LinearLayoutManager.VERTICAL);
	}

	public JiuyiRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		setOrientation(LinearLayoutManager.VERTICAL);
	}

	public JiuyiRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		setOrientation(LinearLayoutManager.VERTICAL);
	}

	/**
	 * 设置回调
	 * @param recyclerViewCallBack
	 */
	public void setCallBack(RecyclerViewCallBack recyclerViewCallBack){
		mRecyclerViewCallBack = recyclerViewCallBack;
	}

	public void setOrientation(int orientation){
        // 创建一个线性布局管理器
         layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(orientation);
        // 设置布局管理器
        setLayoutManager(layoutManager);
	}
	/**
	 * RecyclerView 移动到当前位置，
	 *
	 * @param n  要跳转的位置
	 */
	public  void MoveToPosition( int n) {
		layoutManager.scrollToPositionWithOffset(n, 0);
		layoutManager.setStackFromEnd(true);
	}


	/**
	 * 滑动到底部回调接口
	 */
	public interface OnBottomCallback {
		void onBottom();
	}

	public void setOnBottomCallback(OnBottomCallback onBottomCallback) {
		this.mOnBottomCallback = onBottomCallback;
	}
//	@Override
//	public void onScrolled(int dx, int dy) {
//		if (isSlideToBottom() && mOnBottomCallback != null && dy>0) {//向下滑动才触发
//			mOnBottomCallback.onBottom();
//		}
//	}
	/**
	 * 判断recycleview是否滑动到底部
	 * computeVerticalScrollExtent()是当前屏幕显示的区域高度，computeVerticalScrollOffset() 是当前屏幕之前滑过的距离，而computeVerticalScrollRange()是整个View控件的高度。
	 */
	public boolean isSlideToBottom() {
		//+30的目的是为了提前触发回调防止上拉加载卡顿
		return this.computeVerticalScrollExtent() + this.computeVerticalScrollOffset() + Res.Dip2Pix(30)>= this.computeVerticalScrollRange();
	}

	/**
	 * 检测是否翻页及是否滚动
	 */
	public void checkTurnPage() {
		addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				if (mRecyclerViewCallBack != null) {
					// 不滚动时保存当前滚动到的位置
					if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
						mRecyclerViewCallBack.stopScrollToRequest(mFirstVisibleItemIndex);// 向下请求数据时不用刷屏

					}
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				mLinearLayoutManager = JiuyiRecyclerView.this.getLayoutManager();
				if (layoutManager instanceof LinearLayoutManager) {
					LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
					mFirstVisibleItemIndex =linearManager.findFirstVisibleItemPosition();//可见范围内的第一项的位置
					mLastVisibleItemIndex = linearManager.findLastVisibleItemPosition();
				}
				//滚动回调
				if (mRecyclerViewCallBack != null) {
					mRecyclerViewCallBack.onScrolledToDo(dy);
				}

				if (isSlideToBottom() && mOnBottomCallback != null && dy>0) {//向下滑动才触发
					mOnBottomCallback.onBottom();
				}
			}
		});
	}

	/**
	 * 获取可视起始位置
	 * @return
	 */
	public int getFirstVisibleItemIndex(){
		if(mLinearLayoutManager != null){
			mLinearLayoutManager = JiuyiRecyclerView.this.getLayoutManager();
		}
		if(mFirstVisibleItemIndex ==0 && mLinearLayoutManager != null){
			if (layoutManager instanceof LinearLayoutManager) {
				LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
				mFirstVisibleItemIndex = linearManager.findFirstVisibleItemPosition();
			}
		}
		return mFirstVisibleItemIndex;
	}

	/**
	 * 获取可视最后位置
	 * @return
	 */
	public int getLastVisibleItemIndex(){
		if(mLinearLayoutManager != null){
			mLinearLayoutManager = JiuyiRecyclerView.this.getLayoutManager();
		}
		if(mLastVisibleItemIndex ==0 && mLinearLayoutManager != null){
			if (layoutManager instanceof LinearLayoutManager) {
				LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
				mLastVisibleItemIndex = linearManager.findLastVisibleItemPosition();
			}
		}
		return mLastVisibleItemIndex;
	}


	public interface RecyclerViewCallBack {
		/**
		 * 停止滚动
		 * @param firstVisibleItem
		 */
		public void stopScrollToRequest(int firstVisibleItem);

		/**
		 * 滚动中
		 * @param y
		 */
		public void onScrolledToDo(int y);
	}

}
