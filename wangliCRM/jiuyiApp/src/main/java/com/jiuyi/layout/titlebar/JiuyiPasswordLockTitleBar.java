package com.jiuyi.layout.titlebar;

import android.content.Context;
import android.util.AttributeSet;

import com.control.utils.Res;
import com.control.widget.JiuyiTitleBarBase;

/**
 * ****************************************************************
 * 文件名称 : jiuyiPasswordLockTitleBar
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 密码锁屏标题栏
 *****************************************************************
 */
public class JiuyiPasswordLockTitleBar extends JiuyiTitleBarBase {
	public JiuyiPasswordLockTitleBar(Context context) {
		super(context);
	}

	public JiuyiPasswordLockTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JiuyiPasswordLockTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	/**
	 * 初始化view
	 */
	public void onInit(){
		mLeftView = findViewById(Res.getViewID(context, "jiuyi_titlebar_navbarbackbg"));
		mCenterView = findViewById(Res.getViewID(context, "jiuyi_titlebar_textview"));
		if(mCenterView.getPaddingBottom() > 0){
			mCenterView.setPadding(0, 0, 0, 0);
		}
        setOnClickListener();
	}
}
