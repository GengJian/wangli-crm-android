package com.jiuyi.layout.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.control.utils.Res;
import com.control.widget.JiuyiTitleBarBase;

/**
 * ****************************************************************
 * 文件名称 : jiuyiCustomerCompleteTitleBar
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 客户标题栏
 *****************************************************************
 */
public class JiuyiCustomerCompleteTitleBar extends JiuyiTitleBarBase {
	private TextView mtvcomplete;
	public JiuyiCustomerCompleteTitleBar(Context context) {
		super(context);
	}

	public JiuyiCustomerCompleteTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JiuyiCustomerCompleteTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	/**
	 * 初始化view
	 */
	public void onInit(){
		mLeftView = findViewById(Res.getViewID(context, "jiuyi_titlebar_navbarbackbg"));
		mtvcomplete = (TextView) findViewById(Res.getViewID(context, "jiuyi_titlebar_complete"));
        setOnClickListener();
	}
	/**
	 * 设置点击事件
	 * 如果标题栏的事件不是这样的，则在子类中实现
	 */
	public void setOnClickListener() {
		if (mLeftView != null) {
			mLeftView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (mParentLayout != null && mParentLayout.getCanvasInterface() != null) {
						mParentLayout.getActivity().onKeyUp(KeyEvent.KEYCODE_BACK, null);
					}
				}
			});
		}
		if (mtvcomplete != null) {
			mtvcomplete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (mParentLayout != null && mParentLayout.getCanvasInterface() != null) {
						mParentLayout.getActivity().onKeyUp(KeyEvent.KEYCODE_BACK, null);
					}
				}
			});
		}
	}
}
