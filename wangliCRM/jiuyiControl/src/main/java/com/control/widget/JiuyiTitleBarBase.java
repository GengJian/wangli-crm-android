package com.control.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;

/**
 * ****************************************************************
 * 文件名称 : jiuyiTitleBarBase.java
 * 作 者 :   zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述 : 新title的基类
 *****************************************************************
 */
public class JiuyiTitleBarBase extends RelativeLayout{


    /** 上下文 */
	protected Context context;
    /** 外层布局 */
	public JiuyiRelativeLayout mParentLayout;
    /** 返回按钮 */
	public View mLeftView;//
    /** 搜索按钮 */
	public View mRightView;//
    /** 标题文字 */
	public View mCenterView;//

    public int mViewWidth = Res.Dip2Pix(60);

	public JiuyiTitleBarBase(Context context) {
		super(context);
		this.context = context;
	}

	public JiuyiTitleBarBase(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public JiuyiTitleBarBase(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
	}
	
	/**
	 * 切换皮肤
	 */
	public void changeSkinType(){
        setBackgroundColor(Res.getColor(null, "title_background_color"));
	}

    /**
     * 设置外层布局
     * @param layout
     */
	public void setRelativeLayoutNew(JiuyiRelativeLayout layout){
		mParentLayout = layout;
	}

    /**
     * 标题栏中间m_vCenterView的赋值
     * @param sTitleMid 中间标题内容
     * @param sTitleLeft 左侧标题内容
     * @param sTitleRight 右侧标题内容
     */
	public void setTitle(View mCenterView, String sTitleMid, String sTitleLeft, String sTitleRight, String titleType){
        if(mCenterView == null){
            mCenterView = this.mCenterView;
        }
		if(mCenterView != null && mCenterView instanceof TextView && sTitleMid != null){
			((TextView) mCenterView).setText(sTitleMid);
		}

	}
	
	/**
	 * 初始化view
	 */
	public void onInit(){
        //后退
        mLeftView = findViewById(Res.getViewID(context, "jiuyi_titlebar_navbarbackbg"));
        //更多
        mRightView = findViewById(Res.getViewID(context, "jiuyi_titlebar_more"));
        //标题
        mCenterView = findViewById(Res.getViewID(context, "jiuyi_titlebar_textview"));

        //是为了解决两行的标题的显示竖直空间不足问题
        if(mCenterView.getPaddingBottom() > 0){
            mCenterView.setPadding(0, 0, 0, 0);
        }

		setOnClickListener();
	}

    /**
     * 设置左右按钮是否显示
     * 只有是VISIBLE、GONE、INVISIBLE才能处理
     * @param leftVisibility
     * @param rightVisibility
     */
	public void setViewVisiable(int leftVisibility, int rightVisibility){
        if(rightVisibility >= 0){
            if(mRightView != null){
                if(rightVisibility == View.VISIBLE || rightVisibility == View.GONE || rightVisibility == View.INVISIBLE)
                    mRightView.setVisibility(rightVisibility);
            }
        }
    }
	
	/**
	 * 设置点击事件
	 * 如果标题栏的事件不是这样的，则在子类中实现
	 */
	public void setOnClickListener(){
		if(mLeftView != null){
			mLeftView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(mParentLayout !=null && mParentLayout.getCanvasInterface()!=null){
						mParentLayout.getActivity().onKeyUp(KeyEvent.KEYCODE_BACK, null);
					}
				}
			});
		}
		
	}







}
