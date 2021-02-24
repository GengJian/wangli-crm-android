package com.jiuyi.layout.titlebar;

import android.content.Context;
import android.util.AttributeSet;

import com.control.utils.Res;
import com.control.widget.JiuyiTitleBarBase;


/**
 * ****************************************************************
 * 文件名称 : jiuyiCommTitleBar.java
 * 作 者 :   zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 新titlebar类型的通用类
 *****************************************************************
 */
public class JiuyiCommTitleBar extends JiuyiTitleBarBase {

    
	public JiuyiCommTitleBar(Context context) {
		super(context);
	}

	public JiuyiCommTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JiuyiCommTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	/**
	 * 初始化view
	 */
	public void onInit(){
		mLeftView = findViewById(Res.getViewID(context, "jiuyi_titlebar_navbarbackbg"));
		mRightView = findViewById(Res.getViewID(context, "jiuyi_titlebar_more"));
		mCenterView = findViewById(Res.getViewID(context, "jiuyi_titlebar_textview"));
		//解决两行的标题的显示竖直空间不足问题
		if(mCenterView.getPaddingBottom() > 0){
			mCenterView.setPadding(0, 0, 0, 0);
		}
        setOnClickListener();
	}

}
